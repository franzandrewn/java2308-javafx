package com.andrewn.java2308javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class IndexController extends SwitchController {

    public Label welcomeLabel;

    public void logout(ActionEvent actionEvent) {
        nextScene();
    }

    public void setWelcomeLabel(String login) {
        welcomeLabel.setText("Hello, "  + login);
    }
}
