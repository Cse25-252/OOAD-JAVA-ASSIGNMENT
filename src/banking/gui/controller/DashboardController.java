package banking.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private void handleCustomers(ActionEvent event) {
        openScreen(event,
                "/banking/gui/CustomerManagementView.fxml",
                "Customer Management");
    }

    @FXML
    private void handleAccounts(ActionEvent event) {
        openScreen(event,
                "/banking/gui/AccountManagementView.fxml",
                "Account Management");
    }

    @FXML
    private void handleTransactions(ActionEvent event) {
        openScreen(event,
                "/banking/gui/TransactionView.fxml",
                "Transactions");
    }

    @FXML
    private void handleLogout() {
        showMessage("You have selected Logout.");
    }

    private void openScreen(ActionEvent event,
                            String file,
                            String title) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource(file)
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Access Bank Botswana - " + title);

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Unable to open " + title + ".");
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Access Bank Botswana");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
