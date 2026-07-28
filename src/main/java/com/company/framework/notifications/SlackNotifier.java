package com.company.framework.notifications;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

/**
 * Sends a file to a Slack user via a bot token, using Slack's external file
 * upload flow (getUploadURLExternal -> upload -> completeUploadExternal),
 * which replaced the deprecated single-call files.upload endpoint.
 */
public final class SlackNotifier {

    private static final String SLACK_API = "https://slack.com/api/";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private SlackNotifier() {
    }

    public static void sendFileToUser(String botToken, String userId, File file, String message) {

        try {
            String channelId = openDirectMessageChannel(botToken, userId);
            uploadAndShareFile(botToken, channelId, file, message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send report to Slack", e);
        }
    }

    private static String openDirectMessageChannel(String botToken, String userId) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SLACK_API + "conversations.open"))
                .header("Authorization", "Bearer " + botToken)
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString("{\"users\":\"" + userId + "\"}"))
                .build();

        JsonNode json = send(request);
        return json.get("channel").get("id").asText();
    }

    private static void uploadAndShareFile(String botToken, String channelId, File file, String message) throws Exception {

        String uploadUrlEndpoint = SLACK_API + "files.getUploadURLExternal"
                + "?filename=" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8)
                + "&length=" + file.length();

        HttpRequest uploadUrlRequest = HttpRequest.newBuilder()
                .uri(URI.create(uploadUrlEndpoint))
                .header("Authorization", "Bearer " + botToken)
                .GET()
                .build();

        JsonNode uploadUrlJson = send(uploadUrlRequest);
        String uploadUrl = uploadUrlJson.get("upload_url").asText();
        String fileId = uploadUrlJson.get("file_id").asText();

        byte[] fileBytes = Files.readAllBytes(file.toPath());
        HttpRequest fileUploadRequest = HttpRequest.newBuilder()
                .uri(URI.create(uploadUrl))
                .POST(HttpRequest.BodyPublishers.ofByteArray(fileBytes))
                .build();

        HttpResponse<String> fileUploadResponse = HTTP_CLIENT.send(fileUploadRequest, HttpResponse.BodyHandlers.ofString());
        if (fileUploadResponse.statusCode() != 200) {
            throw new RuntimeException("Slack file upload failed with HTTP " + fileUploadResponse.statusCode());
        }

        String completePayload = MAPPER.writeValueAsString(Map.of(
                "files", List.of(Map.of("id", fileId, "title", file.getName())),
                "channel_id", channelId,
                "initial_comment", message
        ));

        HttpRequest completeRequest = HttpRequest.newBuilder()
                .uri(URI.create(SLACK_API + "files.completeUploadExternal"))
                .header("Authorization", "Bearer " + botToken)
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(completePayload))
                .build();

        send(completeRequest);
    }

    private static JsonNode send(HttpRequest request) throws Exception {

        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode json = MAPPER.readTree(response.body());

        if (!json.path("ok").asBoolean(false)) {
            throw new RuntimeException("Slack API call failed: " + json);
        }
        return json;
    }
}
