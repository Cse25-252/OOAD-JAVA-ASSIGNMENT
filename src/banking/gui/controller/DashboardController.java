
package banking.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class DashboardController {

    @FXML
    private void handleCustomers() {
        showMessage("Customer Management selected.");
    }

    @FXML
    private void handleAccounts() {
        showMessage("Account Management selected.");
    }

    @FXML
    private void handleTransactions() {
        showMessage("Transactions selected.");
    }

    @FXML
    private void handleLogout() {
        showMessage("Logout selected.");
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Access Bank Botswana");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
