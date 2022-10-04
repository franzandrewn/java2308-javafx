package com.andrewn.java2308javafx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class HelloController {


    public Label labelNoText;
    public Label firstLabel;


    public void enteredFirstLabel(MouseEvent mouseEvent) {
        firstLabel.setTextFill(new Color(0, 0, 0, 0));
    }

    public void exitedFirstLabel(MouseEvent mouseEvent) {
        firstLabel.setTextFill(new Color(0, 0, 0, 1));
    }

    public void buttonClick(ActionEvent actionEvent) {
        String text = labelNoText.getText();
        labelNoText.setText(text + "!");
        labelNoText.setTextFill(new Color(Math.random(), Math.random(), Math.random(), 1));
    }

    // task1
    /*
    Создать интерфейс с двумя кнопками на нём: button1, button2
    При нажатии на button1, у текста button2 меняется цвет на случайный
    При нажатии на button2, текст button1 изменяется на "Привет из button2"
     */
    public Button button1;
    public Button button2;


    public void button1Click(ActionEvent actionEvent) {
        button2.setTextFill(new Color(Math.random(),
                Math.random(),
                Math.random(),
                1));
    }

    public void button2Click(ActionEvent actionEvent) {
        button1.setText("Привет из button2");
    }

}