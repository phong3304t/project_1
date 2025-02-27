package com.library;

import com.library.util.DatabaseConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LibraryManagementApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection.connect();

        // Tải giao diện FXML
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        DatabaseConnection.disconnect();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
