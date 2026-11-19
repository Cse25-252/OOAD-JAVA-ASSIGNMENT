
package banking.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText(
                "Please enter your username and password."
            );
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../DashboardView.fxml")
                );

                Scene scene = new Scene(loader.load());

                Stage stage = (Stage) messageLabel.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Access Bank Botswana - Dashboard");

            } catch (Exception e) {
                e.printStackTrace();
                messageLabel.setText("Unable to open dashboard.");
            }
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) messageLabel.getScene().getWindow();
        stage.close();
    }
}
