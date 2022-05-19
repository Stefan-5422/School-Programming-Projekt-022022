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
        GlobalEventHandler.trigger("app:closing", null);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(YesApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280 , 720 );
        stage.setTitle("Yes!");
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.show();
    }
}