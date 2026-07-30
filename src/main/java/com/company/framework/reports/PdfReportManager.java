package com.company.framework.reports;

import com.company.framework.constants.FrameworkConstants;
import com.company.framework.utils.ConfigReader;
import com.company.framework.utils.LogUtil;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * Owns result collection (thread-safe, so Android+iOS parallel runs both feed one report) and
 * renders the final PDF via OpenPDF. TestListener only calls addResult()/generateReport() -
 * it never touches OpenPDF types directly, keeping report rendering separate from listener logic.
 */
public final class PdfReportManager {

    private static final List<TestExecutionResult> RESULTS = new CopyOnWriteArrayList<>();
    private static volatile long suiteStartTimeMillis;
    private static volatile long suiteEndTimeMillis;
    private static final int TABLE_CELL_MAX_LENGTH = 200;

    private static final Color BRAND_COLOR = new Color(0x1F, 0x3B, 0x57);
    private static final Color HEADER_ROW_BG = new Color(0xF2, 0xF4, 0xF7);
    private static final Color PASS_COLOR = new Color(0x2E, 0x7D, 0x32);
    private static final Color FAIL_COLOR = new Color(0xC6, 0x28, 0x28);
    private static final Color SKIP_COLOR = new Color(0xC7, 0x7A, 0x0A);

    private static final Font TITLE_FONT = new Font(Font.HELVETICA, 20, Font.BOLD, BRAND_COLOR);
    private static final Font HEADING_FONT = new Font(Font.HELVETICA, 13, Font.BOLD, BRAND_COLOR);
    private static final Font LABEL_FONT = new Font(Font.HELVETICA, 10, Font.BOLD, Color.DARK_GRAY);
    private static final Font VALUE_FONT = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);
    private static final Font SMALL_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.GRAY);
    private static final Font LINK_FONT = new Font(Font.HELVETICA, 9, Font.UNDERLINE, new Color(0x1A, 0x73, 0xE8));

    private PdfReportManager() {
    }

    public static void recordSuiteStart() {
        suiteStartTimeMillis = System.currentTimeMillis();
    }

    /**
     * Call BEFORE any deliberate delay ahead of generateReport() (e.g. TestListener's grace
     * period), so "Execution Time" in the summary reflects actual test duration, not the delay.
     */
    public static void recordSuiteEnd() {
        suiteEndTimeMillis = System.currentTimeMillis();
    }

    public static void addResult(TestExecutionResult result) {
        RESULTS.add(result);
    }

    /**
     * Renders and writes the PDF. Safe to call once per suite (from ISuiteListener.onFinish, NOT
     * ITestListener.onFinish - see TestListener for why that distinction matters under parallel
     * Android+iOS execution). Never throws - a reporting failure must not fail the test run.
     */
    public static synchronized void generateReport() {
        try {
            PdfReportModel model = buildModel();
            String path = renderPdf(model);
            LogUtil.log("PDF report generated: " + path);
        } catch (Exception e) {
            LogUtil.log("Failed to generate PDF report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static PdfReportModel buildModel() {

        List<TestExecutionResult> snapshot = new ArrayList<>(RESULTS);
        int total = snapshot.size();
        int passed = countByStatus(snapshot, TestExecutionResult.Status.PASSED);
        int failed = countByStatus(snapshot, TestExecutionResult.Status.FAILED);
        int skipped = countByStatus(snapshot, TestExecutionResult.Status.SKIPPED);
        double passPercentage = total == 0 ? 0.0 : (passed * 100.0) / total;
        double failPercentage = total == 0 ? 0.0 : (failed * 100.0) / total;
        long executionTimeMillis = (suiteStartTimeMillis == 0 || suiteEndTimeMillis == 0)
                ? 0 : suiteEndTimeMillis - suiteStartTimeMillis;

        ExecutionSummary summary = ExecutionSummary.builder()
                .projectName(ConfigReader.get("report.projectName"))
                .environment(ConfigReader.get("report.environment"))
                .executedBy(resolveExecutedBy())
                .executionDate(new SimpleDateFormat("dd MMM yyyy, HH:mm:ss").format(new Date()))
                .platform(distinctJoin(snapshot, TestExecutionResult::getPlatform))
                .deviceName(distinctJoin(snapshot, TestExecutionResult::getDeviceName))
                .osVersion(distinctJoin(snapshot, TestExecutionResult::getOsVersion))
                .totalTestCases(total)
                .passed(passed)
                .failed(failed)
                .skipped(skipped)
                .executionTimeMillis(executionTimeMillis)
                .passPercentage(passPercentage)
                .failPercentage(failPercentage)
                .build();

        return new PdfReportModel(summary, snapshot);
    }

    private static int countByStatus(List<TestExecutionResult> results, TestExecutionResult.Status status) {
        int count = 0;
        for (TestExecutionResult result : results) {
            if (result.getStatus() == status) {
                count++;
            }
        }
        return count;
    }

    private static String resolveExecutedBy() {
        String configured = ConfigReader.get("report.executedBy");
        if (configured != null && !configured.isBlank()) {
            return configured;
        }
        return System.getProperty("user.name", "Unknown");
    }

    private static String distinctJoin(List<TestExecutionResult> results, Function<TestExecutionResult, String> extractor) {
        Set<String> values = new LinkedHashSet<>();
        for (TestExecutionResult result : results) {
            String value = extractor.apply(result);
            if (value != null && !value.isBlank()) {
                values.add(value);
            }
        }
        return values.isEmpty() ? "N/A" : String.join(" + ", values);
    }

    private static String renderPdf(PdfReportModel model) throws DocumentException, IOException {

        File reportDir = new File(FrameworkConstants.REPORTS_DIR);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        String fileName = "Automation_Report_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";
        File reportFile = new File(reportDir, fileName);

        Document document = new Document(PageSize.A4, 40, 40, 60, 60);
        try (FileOutputStream outputStream = new FileOutputStream(reportFile)) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(new ReportPageEvents(
                    model.getExecutionSummary().getProjectName(),
                    model.getExecutionSummary().getExecutedBy(),
                    FrameworkConstants.FRAMEWORK_VERSION,
                    model.getExecutionSummary().getExecutionDate()));

            document.open();
            addSummaryPage(document, model.getExecutionSummary());
            addTestDetailsPages(document, model.getTestExecutionResults());
            document.close();
        }

        return reportFile.getAbsolutePath();
    }

    private static void addSummaryPage(Document document, ExecutionSummary summary) throws DocumentException {

        Paragraph title = new Paragraph("Automation Test Execution Report", TITLE_FONT);
        title.setSpacingAfter(4);
        document.add(title);

        // Paragraph logoPlaceholder = new Paragraph("[ COMPANY LOGO ]", SMALL_FONT);
        // logoPlaceholder.setSpacingAfter(18);
        // document.add(logoPlaceholder);

        addInfoLine(document, "Project Name", summary.getProjectName());
        addInfoLine(document, "Execution Date", summary.getExecutionDate());
        addInfoLine(document, "Environment", summary.getEnvironment());
        addInfoLine(document, "Platform", summary.getPlatform());
        addInfoLine(document, "Device Name", summary.getDeviceName());
        addInfoLine(document, "OS Version", summary.getOsVersion());
        addInfoLine(document, "Executed By", summary.getExecutedBy());

        Paragraph spacer = new Paragraph(" ");
        spacer.setSpacingAfter(10);
        document.add(spacer);

        Paragraph statsHeading = new Paragraph("Execution Summary", HEADING_FONT);
        statsHeading.setSpacingAfter(8);
        document.add(statsHeading);

        PdfPTable statsTable = new PdfPTable(2);
        statsTable.setWidthPercentage(100);
        statsTable.setWidths(new float[]{35, 65});
        addInfoRow(statsTable, "Total Test Cases", String.valueOf(summary.getTotalTestCases()));
        addInfoRow(statsTable, "Passed", String.valueOf(summary.getPassed()));
        addInfoRow(statsTable, "Failed", String.valueOf(summary.getFailed()));
        addInfoRow(statsTable, "Skipped", String.valueOf(summary.getSkipped()));
        addInfoRow(statsTable, "Execution Time", formatDuration(summary.getExecutionTimeMillis()));
        addInfoRow(statsTable, "Pass Percentage", String.format("%.2f%%", summary.getPassPercentage()));
        addInfoRow(statsTable, "Fail Percentage", String.format("%.2f%%", summary.getFailPercentage()));
        document.add(statsTable);
    }

    /** Plain-text "Label: Value" line, used for the summary page's identifying details. */
    private static void addInfoLine(Document document, String label, String value) throws DocumentException {

        Paragraph line = new Paragraph();
        line.add(new Chunk(label + ": ", LABEL_FONT));
        line.add(new Chunk((value == null || value.isBlank()) ? "N/A" : value, VALUE_FONT));
        line.setSpacingAfter(4);
        document.add(line);
    }

    private static void addInfoRow(PdfPTable table, String label, String value) {

        PdfPCell labelCell = new PdfPCell(new Phrase(label, LABEL_FONT));
        labelCell.setBorderColor(Color.LIGHT_GRAY);
        labelCell.setPadding(6);
        labelCell.setBackgroundColor(HEADER_ROW_BG);

        PdfPCell valueCell = new PdfPCell(new Phrase((value == null || value.isBlank()) ? "N/A" : value, VALUE_FONT));
        valueCell.setBorderColor(Color.LIGHT_GRAY);
        valueCell.setPadding(6);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    /** Screenshot image with a clickable file:// link beside it, so the file can be opened directly. */
    private static PdfPTable buildScreenshotBlock(String screenshotPath, String screenshotFileName) throws BadElementException, IOException {

        Image image = Image.getInstance(screenshotPath);
        image.scaleToFit(340, 480);

        PdfPCell imageCell = new PdfPCell(image);
        imageCell.setBorder(Rectangle.NO_BORDER);
        imageCell.setPadding(4);
        imageCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        imageCell.setVerticalAlignment(Element.ALIGN_TOP);

        Chunk link = new Chunk("Open Screenshot", LINK_FONT);
        link.setAnchor(fileUri(screenshotPath));

        PdfPCell linkCell = new PdfPCell();
        linkCell.setBorder(Rectangle.NO_BORDER);
        linkCell.setPadding(8);
        linkCell.setVerticalAlignment(Element.ALIGN_TOP);
        linkCell.addElement(new Paragraph("Screenshot File:", LABEL_FONT));
        linkCell.addElement(new Paragraph(link));
        linkCell.addElement(new Paragraph(screenshotFileName, SMALL_FONT));

        PdfPTable block = new PdfPTable(2);
        block.setWidthPercentage(100);
        block.setWidths(new float[]{55, 45});
        block.setSpacingBefore(6);
        block.setSpacingAfter(16);
        block.addCell(imageCell);
        block.addCell(linkCell);
        return block;
    }

    private static String fileUri(String path) {
        return new File(path).toURI().toString();
    }

    private static String formatDuration(long millis) {
        long totalSeconds = millis / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return minutes + "m " + seconds + "s";
    }

    private static void addTestDetailsPages(Document document, List<TestExecutionResult> results)
            throws DocumentException, IOException {

        document.newPage();
        Paragraph heading = new Paragraph("Test Execution Details", HEADING_FONT);
        heading.setSpacingAfter(10);
        document.add(heading);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{4, 13, 19, 7, 17, 12, 16, 12});
        table.setHeaderRows(1);

        addTableHeaderCell(table, "Sl.No");
        addTableHeaderCell(table, "Scenario Name");
        addTableHeaderCell(table, "Full Description");
        addTableHeaderCell(table, "Status");
        addTableHeaderCell(table, "Failure Reason");
        addTableHeaderCell(table, "Exception Type");
        addTableHeaderCell(table, "Root Cause");
        addTableHeaderCell(table, "Screenshot");

        int serialNumber = 1;
        for (TestExecutionResult result : results) {
            addTestResultRow(table, serialNumber++, result);
        }

        document.add(table);
        addFailureScreenshots(document, results);
    }

    private static void addTableHeaderCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 8, Font.BOLD, Color.WHITE)));
        cell.setBackgroundColor(BRAND_COLOR);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private static void addTestResultRow(PdfPTable table, int serialNumber, TestExecutionResult result) {

        boolean failed = result.getStatus() == TestExecutionResult.Status.FAILED;

        addTableCell(table, String.valueOf(serialNumber), Element.ALIGN_CENTER);
        addTableCell(table, result.getTestMethodName(), Element.ALIGN_LEFT);
        addTableCell(table, result.getDescription(), Element.ALIGN_LEFT);
        addStatusCell(table, result.getStatus());
        addTableCell(table, failed ? truncateForTable(result.getFailureReason()) : "NA", Element.ALIGN_LEFT);
        addTableCell(table, failed ? result.getExceptionType() : "NA", Element.ALIGN_LEFT);
        addTableCell(table, failed ? truncateForTable(result.getRootCause()) : "NA", Element.ALIGN_LEFT);
        addScreenshotCell(table, failed && result.hasScreenshot() ? result : null);
    }

    private static void addTableCell(PdfPTable table, String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase((text == null || text.isBlank()) ? "NA" : text, SMALL_FONT));
        cell.setPadding(5);
        cell.setBorderColor(Color.LIGHT_GRAY);
        cell.setHorizontalAlignment(alignment);
        table.addCell(cell);
    }

    /** Selenium/Appium exception text is often paragraph-length; keep table cells compact. */
    private static String truncateForTable(String text) {
        if (text == null) {
            return null;
        }
        return text.length() <= TABLE_CELL_MAX_LENGTH ? text : text.substring(0, TABLE_CELL_MAX_LENGTH) + "...";
    }

    private static void addStatusCell(PdfPTable table, TestExecutionResult.Status status) {
        PdfPCell cell = new PdfPCell(new Phrase(status.name(), new Font(Font.HELVETICA, 8, Font.BOLD, statusColor(status))));
        cell.setPadding(5);
        cell.setBorderColor(Color.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private static void addScreenshotCell(PdfPTable table, TestExecutionResult result) {

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setBorderColor(Color.LIGHT_GRAY);

        if (result != null) {
            Chunk link = new Chunk(result.getScreenshotFileName(), LINK_FONT);
            link.setAnchor(fileUri(result.getScreenshotPath()));
            cell.addElement(new Phrase(link));
        } else {
            cell.addElement(new Phrase("NA", SMALL_FONT));
        }

        table.addCell(cell);
    }

    /** Full-size embedded screenshots for failed tests, referenced by scenario name from the table above. */
    private static void addFailureScreenshots(Document document, List<TestExecutionResult> results)
            throws DocumentException, IOException {

        List<TestExecutionResult> failedWithScreenshots = new ArrayList<>();
        for (TestExecutionResult result : results) {
            if (result.getStatus() == TestExecutionResult.Status.FAILED && result.hasScreenshot()) {
                failedWithScreenshots.add(result);
            }
        }
        if (failedWithScreenshots.isEmpty()) {
            return;
        }

        document.newPage();
        Paragraph heading = new Paragraph("Failure Screenshots", HEADING_FONT);
        heading.setSpacingAfter(10);
        document.add(heading);

        for (TestExecutionResult result : failedWithScreenshots) {
            Paragraph label = new Paragraph(result.getTestMethodName(), LABEL_FONT);
            label.setSpacingBefore(8);
            document.add(label);
            try {
                document.add(buildScreenshotBlock(result.getScreenshotPath(), result.getScreenshotFileName()));
            } catch (Exception e) {
                document.add(new Paragraph("[ Screenshot could not be embedded: " + e.getMessage() + " ]", SMALL_FONT));
            }
        }
    }

    private static Color statusColor(TestExecutionResult.Status status) {
        return switch (status) {
            case PASSED -> PASS_COLOR;
            case FAILED -> FAIL_COLOR;
            case SKIPPED -> SKIP_COLOR;
        };
    }

    /**
     * Draws the header and footer on every page: header is the project name + report title,
     * footer carries Generated By / Framework Version / Page Number / Execution Timestamp.
     */
    private static final class ReportPageEvents extends PdfPageEventHelper {

        private final String projectName;
        private final String generatedBy;
        private final String frameworkVersion;
        private final String executionTimestamp;

        private ReportPageEvents(String projectName, String generatedBy, String frameworkVersion, String executionTimestamp) {
            this.projectName = (projectName == null || projectName.isBlank()) ? "Mobile Automation" : projectName;
            this.generatedBy = generatedBy;
            this.frameworkVersion = frameworkVersion;
            this.executionTimestamp = executionTimestamp;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            PdfContentByte canvas = writer.getDirectContent();

            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,
                    new Phrase(projectName + " - Automation Execution Report", SMALL_FONT),
                    document.left(), document.top() + 20, 0);
            drawRule(canvas, document.top() + 12, document);

            drawRule(canvas, document.bottom() - 6, document);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,
                    new Phrase("Generated By: " + generatedBy + "   |   Framework Version: " + frameworkVersion
                            + "   |   " + executionTimestamp, SMALL_FONT),
                    document.left(), document.bottom() - 18, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT,
                    new Phrase("Page " + writer.getPageNumber(), SMALL_FONT),
                    document.right(), document.bottom() - 18, 0);
        }

        private void drawRule(PdfContentByte canvas, float y, Document document) {
            canvas.setColorStroke(Color.LIGHT_GRAY);
            canvas.setLineWidth(0.5f);
            canvas.moveTo(document.left(), y);
            canvas.lineTo(document.right(), y);
            canvas.stroke();
        }
    }
}
