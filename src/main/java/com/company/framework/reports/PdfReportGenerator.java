package com.company.framework.reports;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class PdfReportGenerator {

    private static final Color COLOR_PASS = new Color(0, 128, 0);
    private static final Color COLOR_FAIL = new Color(200, 0, 0);
    private static final Color COLOR_SKIP = new Color(200, 140, 0);
    private static final Color COLOR_TERMINATED = new Color(90, 90, 90);
    private static final Color COLOR_HEADER_BG = new Color(40, 53, 147);

    private PdfReportGenerator() {
    }

    public static void generate(SuiteSummary summary, String outputPath) {

        Document document = new Document(PageSize.A4, 36, 36, 54, 36);

        try {
            File outputFile = new File(outputPath);
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();

            addTitle(document);
            addSummaryBlock(document, summary);
            document.add(Chunk.NEWLINE);
            addResultsSummaryTable(document, summary);
            document.add(Chunk.NEWLINE);
            addDetailedResultsTable(document, summary);
            addFailureScreenshots(document, summary);

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF suite report", e);
        }
    }

    private static void addTitle(Document document) throws Exception {

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLACK);
        Paragraph title = new Paragraph("Suite Execution Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(16);
        document.add(title);
    }

    private static void addSummaryBlock(Document document, SuiteSummary summary) throws Exception {

        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.BLACK);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Color.BLACK);

        boolean overallPass = summary.isOverallPass();
        Font statusFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11,
                overallPass ? COLOR_PASS : COLOR_FAIL);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{35f, 65f});

        addLabelValueRow(table, "Status", overallPass ? "PASS" : "FAIL", labelFont, statusFont);
        addLabelValueRow(table, "Suite Name", nullToDash(summary.getSuiteName()), labelFont, valueFont);
        addLabelValueRow(table, "Execution Date & Time", formatDateTime(summary.getStartTime()), labelFont, valueFont);
        addLabelValueRow(table, "Total Test Cases Executed", String.valueOf(summary.getTotalScripts()), labelFont, valueFont);
        addLabelValueRow(table, "Execution Duration", formatDuration(summary.getDurationMillis()), labelFont, valueFont);
        addLabelValueRow(table, "Platform", nullToDash(summary.getPlatform()), labelFont, valueFont);
        addLabelValueRow(table, "Device", nullToDash(summary.getDevice()), labelFont, valueFont);

        document.add(table);
    }

    private static void addLabelValueRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {

        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(PdfPCell.BOTTOM);
        labelCell.setBorderColor(new Color(220, 220, 220));
        labelCell.setPadding(6);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(PdfPCell.BOTTOM);
        valueCell.setBorderColor(new Color(220, 220, 220));
        valueCell.setPadding(6);
        table.addCell(valueCell);
    }

    private static void addResultsSummaryTable(Document document, SuiteSummary summary) throws Exception {

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.WHITE);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        String[] headers = {"Total Script", "Passes", "Failed", "Skipped", "Terminated"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(COLOR_HEADER_BG);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }

        long[] values = {
                summary.getTotalScripts(),
                summary.getPassedCount(),
                summary.getFailedCount(),
                summary.getSkippedCount(),
                summary.getTerminatedCount()
        };
        for (long value : values) {
            PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(value), valueFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }

        document.add(table);
    }

    private static void addDetailedResultsTable(Document document, SuiteSummary summary) throws Exception {

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.WHITE);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);

        Paragraph heading = new Paragraph("Test Case Results",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Color.BLACK));
        heading.setSpacingAfter(8);
        document.add(heading);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{75f, 25f});

        PdfPCell nameHeader = new PdfPCell(new Phrase("Script Name", headerFont));
        nameHeader.setBackgroundColor(COLOR_HEADER_BG);
        nameHeader.setPadding(6);
        table.addCell(nameHeader);

        PdfPCell statusHeader = new PdfPCell(new Phrase("Status", headerFont));
        statusHeader.setBackgroundColor(COLOR_HEADER_BG);
        statusHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        statusHeader.setPadding(6);
        table.addCell(statusHeader);

        for (ScriptResult result : summary.getScriptResults()) {

            PdfPCell nameCell = new PdfPCell(new Phrase(result.getScriptName(), bodyFont));
            nameCell.setPadding(6);
            table.addCell(nameCell);

            Font statusFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, statusColor(result.getStatus()));
            PdfPCell statusCell = new PdfPCell(new Phrase(result.getStatus().name(), statusFont));
            statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            statusCell.setPadding(6);
            table.addCell(statusCell);
        }

        document.add(table);
    }

    private static void addFailureScreenshots(Document document, SuiteSummary summary) throws Exception {

        boolean addedHeading = false;

        for (ScriptResult result : summary.getScriptResults()) {

            boolean isFailureLike = result.getStatus() == ScriptResult.Status.FAILED
                    || result.getStatus() == ScriptResult.Status.TERMINATED;

            if (!isFailureLike || result.getScreenshotPath() == null) {
                continue;
            }

            File screenshotFile = new File(result.getScreenshotPath());
            if (!screenshotFile.exists()) {
                continue;
            }

            if (!addedHeading) {
                document.newPage();
                Paragraph heading = new Paragraph("Failure Screenshots",
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.BLACK));
                heading.setSpacingAfter(10);
                document.add(heading);
                addedHeading = true;
            }

            Font nameFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, statusColor(result.getStatus()));
            Paragraph scriptTitle = new Paragraph(result.getScriptName() + "  [" + result.getStatus() + "]", nameFont);
            scriptTitle.setSpacingBefore(10);
            scriptTitle.setSpacingAfter(4);
            document.add(scriptTitle);

            if (result.getErrorMessage() != null && !result.getErrorMessage().isBlank()) {
                Paragraph error = new Paragraph(result.getErrorMessage(),
                        FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 9, Color.DARK_GRAY));
                error.setSpacingAfter(6);
                document.add(error);
            }

            Image image = Image.getInstance(screenshotFile.getAbsolutePath());
            float maxWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            float maxHeight = 320f;
            image.scaleToFit(maxWidth, maxHeight);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
        }
    }

    private static Color statusColor(ScriptResult.Status status) {
        return switch (status) {
            case PASSED -> COLOR_PASS;
            case FAILED -> COLOR_FAIL;
            case SKIPPED -> COLOR_SKIP;
            case TERMINATED -> COLOR_TERMINATED;
        };
    }

    private static String nullToDash(String value) {
        return (value == null || value.isBlank()) ? "-" : value;
    }

    private static String formatDateTime(java.util.Date date) {

        if (date == null) {
            return "-";
        }
        String datePart = new SimpleDateFormat("d MMMM yyyy hh:mm", Locale.ENGLISH).format(date);
        String amPm = new SimpleDateFormat("a", Locale.ENGLISH).format(date).toLowerCase(Locale.ENGLISH);
        return datePart + amPm;
    }

    private static String formatDuration(long durationMillis) {

        long totalSeconds = durationMillis / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        StringBuilder builder = new StringBuilder();
        if (hours > 0) {
            builder.append(hours).append("h ");
        }
        if (hours > 0 || minutes > 0) {
            builder.append(minutes).append("m ");
        }
        builder.append(seconds).append("s");
        return builder.toString();
    }
}
