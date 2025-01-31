package com.vehiclemanagementsys.vehiclemanagement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaFXFrontend extends Application {
    private static final String BASE_URL = "http://localhost:8081/api/v1/Users";

    @Override
    public void start(Stage primaryStage) {
        TextArea outputArea = new TextArea();
        Button fetchUsersButton = new Button("Fetch Users");

        fetchUsersButton.setOnAction(event -> fetchUsers(outputArea));

        VBox root = new VBox(10, fetchUsersButton, outputArea);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("JavaFX-Spring Communication");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchUsers(TextArea outputArea) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(outputArea::setText)
                .exceptionally(e -> {
                    outputArea.setText("Error: " + e.getMessage());
                    return null;
                });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
