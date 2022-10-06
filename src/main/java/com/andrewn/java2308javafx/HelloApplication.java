package com.andrewn.java2308javafx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
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
//        tag(stage);
//        switchScenes(stage);
//        startWithoutThread(stage);
        startWithThread(stage);
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

    private void switchScenes(Stage stage) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        FXMLLoader indexLoader = new FXMLLoader(HelloApplication.class.getResource("index.fxml"));

        Scene loginScene = new Scene(loginLoader.load(), 600, 400);
        Scene indexScene = new Scene(indexLoader.load(), 600,400);

        LoginController lc = loginLoader.getController();
        IndexController ic = indexLoader.getController();
        lc.setNextController(ic);

        // Простановка ссылки на окно в контроллерах
        lc.setCurrentStage(stage);
        ic.setCurrentStage(stage);

        // Простановка ссылки на следующую сцену
        lc.setNextScene(indexScene);
        ic.setNextScene(loginScene);

        stage.setTitle("Login");
        stage.setScene(loginScene);
        stage.show();
    }

    private void startWithThread(Stage stage) {
        VBox root =  new VBox(5);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        ProgressBar progressBar = new ProgressBar(0.0);

        Label barProgress = new Label();
        Label powProgress = new Label();

        Button buttonProgress = new Button("Start progress bar");
        buttonProgress.setOnAction(event -> startProgressBar(progressBar, barProgress));

        Button buttonPow = new Button("Start 2.9^100 calculate");
        buttonPow.setOnAction(event -> startPow(powProgress));

        HBox temp = new HBox(5);
        temp.setAlignment(Pos.CENTER);
        temp.getChildren().addAll(
                new Label("Current step: "),
                barProgress
        );

        root.getChildren().addAll(
                progressBar,
                temp,
                buttonProgress,
                powProgress,
                buttonPow
        );

        stage.setWidth(300);
        stage.setHeight(300);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void startProgressBar(ProgressBar progressBar, Label barProgress) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                int steps = 1000;
                for (int i = 0; i < steps; i++) {
                    Thread.sleep(10);
                    updateProgress(i, steps);
                    updateMessage(String.valueOf(i));
                }
                return null;
            }
        };

        task.setOnFailed(workerStateEvent -> {
            workerStateEvent.getSource().getException().printStackTrace();
        });

        task.setOnSucceeded(workerStateEvent -> {
            System.out.println("Done!");
        });

        progressBar.progressProperty().bind(task.progressProperty());
        barProgress.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    private void startPow(Label powProgress) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                int steps = 99;
                double initValue = 2.9;
                for (int i = 0; i < steps; i++) {
                    initValue *= 2.9;
                    Thread.sleep(100);
                    updateProgress(i, steps);
                    updateMessage(String.valueOf(initValue));
                }
                return null;
            }
        };

        task.setOnFailed(workerStateEvent -> {
            workerStateEvent.getSource().getException().printStackTrace();
        });

        task.setOnSucceeded(workerStateEvent -> {
            System.out.println("Pow done!");
        });

        powProgress.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    private void startWithoutThread(Stage stage) {
        VBox root =  new VBox(5);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        ProgressBar progressBar = new ProgressBar(0.0);

        Label barProgress = new Label();
        Label powProgress = new Label();

        Button buttonProgress = new Button("Start progress bar");
        buttonProgress.setOnAction(event -> startProgressBarAlt(progressBar, barProgress));

        Button buttonPow = new Button("Start 2.9^100 calculate");
        buttonPow.setOnAction(event -> startPowAlt(powProgress));

        HBox temp = new HBox(5);
        temp.setAlignment(Pos.CENTER);
        temp.getChildren().addAll(
                new Label("Current step: "),
                barProgress
        );

        root.getChildren().addAll(
                progressBar,
                temp,
                buttonProgress,
                powProgress,
                buttonPow
        );

        stage.setWidth(300);
        stage.setHeight(300);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void startPowAlt(Label powProgress) {
        double initValue = 2.9;
        for (int i = 0; i < 99; i++) {
            initValue *= 2.9;
            powProgress.setText(String.valueOf(initValue));
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void startProgressBarAlt(ProgressBar progressBar, Label barProgress) {
        int steps = 1000;
        for (int i = 0; i < steps; i++) {
            progressBar.progressProperty().setValue((double) i / steps);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}