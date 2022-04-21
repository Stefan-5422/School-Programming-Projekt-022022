package com.yes.yes;

import com.yes.yes.utils.GlobalEventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        System.out.println("closing bro");
        GlobalEventHandler.trigger("closing",null);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(YesApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        //Scale scale = new Scale(0.25,0.25, 0, 0);
        //scene.getRoot().getTransforms().add(scale);

        stage.setMaximized(true);
        stage.show();
    }
}