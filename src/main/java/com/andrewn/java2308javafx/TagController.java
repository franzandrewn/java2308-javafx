package com.andrewn.java2308javafx;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class TagController {

    public Label label;

    public void found(MouseEvent mouseEvent) {
        label.setLayoutX(new Random().nextInt(600));
        label.setLayoutY(new Random().nextInt(400));
        label.setStyle("-fx-text-fill: #f44336");
        System.out.println(label.getStyle());

        System.out.println(label.getStylesheets().get(0));
    }
}
