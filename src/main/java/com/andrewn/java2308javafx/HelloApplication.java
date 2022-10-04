package com.andrewn.java2308javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    // MVC паттерн - Model-View-Controller
    // Model - данные
    // View - представление
    // Controller - объект, соединяющий данные и представление

    @Override
    public void start(Stage stage) throws IOException {
//        helloWorld(stage);
//        manualCreation(stage);
        tag(stage);
    }

    private void helloWorld(Stage stage) throws IOException {
        // Получение интерфейса
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Создание окна интерфейса
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void manualCreation(Stage stage) throws IOException {
        // Создание интерфейса
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        Label firstLabel = new Label("Это приложение JavaFX");
        Label labelNoText = new Label();
        Button button = new Button("Нажми меня");

        // Привязывание функционала
        button.setOnAction(actionEvent -> {
            String text = labelNoText.getText();
            labelNoText.setText(text + "!");
            labelNoText.setTextFill(new Color(Math.random(),Math.random(),Math.random(),1));
        });

        firstLabel.setOnMouseEntered(mouseEvent -> firstLabel.setTextFill(new Color(0,0,0,0)));
        firstLabel.setOnMouseExited(mouseEvent -> firstLabel.setTextFill(new Color(0,0,0,1)));


        vBox.getChildren().addAll(firstLabel, labelNoText, button);
        // Создание окна интерфейса
        Scene scene = new Scene(vBox, 400, 400);
        scene.setOnKeyPressed(keyEvent ->  {
            if (keyEvent.getCode().getCode() == KeyCode.R.getCode())
                labelNoText.setText("");
        });
        stage.setTitle("Manual creation");
        stage.setScene(scene);
        stage.show();
    }

    private void tag(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tag.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Tag!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}