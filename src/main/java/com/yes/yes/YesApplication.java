package com.yes.yes;

import com.yes.yes.utils.GlobalEventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class YesApplication extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        System.out.println("Closing!");
        GlobalEventHandler.trigger("app:closing", null);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(YesApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.show();
    }
}