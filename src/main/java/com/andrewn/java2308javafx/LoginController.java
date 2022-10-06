package com.andrewn.java2308javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends SwitchController {
    private static final String LOGIN = "ADMIN";
    private static final String PASSWORD = "123qwe";

    private IndexController nextController;

    public TextField loginField;
    public PasswordField passwordField;
    public Label errorLabel;


    public void login(ActionEvent actionEvent) {
        String userLoginIn = loginField.getText();
        String userPassIn = passwordField.getText();
        if (userLoginIn.equals(LOGIN) && userPassIn.equals(PASSWORD)) {
            reset();
            nextScene();
            nextController.setWelcomeLabel(userLoginIn);
        } else if (!userLoginIn.equals(LOGIN)) {
            errorLabel.setText("Неверный логин");
        } else {
            errorLabel.setText("Неверный пароль");
        }
    }

    private void reset() {
        loginField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
    }

    public void setNextController(IndexController nextController) {
        this.nextController = nextController;
    }
}
