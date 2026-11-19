package banking.gui.controller;

import banking.model.Account;
import banking.model.Customer;
import banking.model.CustomerRegistry;
import banking.model.SavingsAccount;

import banking.database.TransactionDAO;
import banking.database.AccountDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransactionController {

    private TransactionDAO transactionDAO = new TransactionDAO();
    private AccountDAO accountDAO = new AccountDAO();

    @FXML
    private TextField transactionIdField;

    @FXML
    private TextField accountNumberField;

    @FXML
    private ComboBox<String> transactionTypeBox;

    @FXML
    private TextField amountField;

    @FXML
    private DatePicker transactionDatePicker;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        transactionTypeBox.getItems().addAll(
            "Deposit",
            "Withdrawal"
        );
    }

    @FXML
    private void handleProcessTransaction() {

        try {
            String transactionId =
                transactionIdField.getText().trim();

            String accountNumber =
                accountNumberField.getText().trim();

            String transactionType =
                transactionTypeBox.getValue();

            double amount =
                Double.parseDouble(amountField.getText().trim());

            if (transactionId.isEmpty()
                    || accountNumber.isEmpty()
                    || transactionType == null
                    || transactionDatePicker.getValue() == null) {

                messageLabel.setText("Please fill in all fields.");
                return;
            }

            int id = Integer.parseInt(transactionId);

            if (amount <= 0) {
                messageLabel.setText(
                    "Amount must be greater than zero."
                );
                return;
            }

            Account account = null;

            for (Customer customer : CustomerRegistry.getCustomers()) {

                for (Account existingAccount : customer.getAccounts()) {

                    if (existingAccount.getAccountNumber()
                            .equalsIgnoreCase(accountNumber)) {

                        account = existingAccount;
                        break;
                    }
                }

                if (account != null) {
                    break;
                }
            }

            if (account == null) {
                messageLabel.setText("Account not found.");
                return;
            }

            double oldBalance = account.getBalance();

            if (transactionType.equals("Deposit")) {

                account.deposit(amount);

            } else if (transactionType.equals("Withdrawal")) {

                if (account instanceof SavingsAccount) {
                    messageLabel.setText(
                        "Withdrawals are not allowed on savings accounts."
                    );
                    return;
                }

                if (amount > oldBalance) {
                    messageLabel.setText("Insufficient balance.");
                    return;
                }

                account.withdraw(amount);
            }

            double newBalance = account.getBalance();

            // Check whether the model processed the transaction
            boolean successful;

            if (transactionType.equals("Deposit")) {
                successful = newBalance > oldBalance;
            } else {
                successful = newBalance < oldBalance;
            }

            if (!successful) {
                messageLabel.setText("Transaction unsuccessful.");
                return;
            }

            // Save the updated account balance
            boolean balanceSaved = accountDAO.updateBalance(
                accountNumber,
                newBalance
            );

            if (!balanceSaved) {
                messageLabel.setText(
                    "Transaction processed in memory, "
                    + "but account balance could not be saved."
                );
                return;
            }

            // Save transaction details in SQLite
            boolean transactionSaved = transactionDAO.addTransaction(
                accountNumber,
                transactionType,
                amount,
                transactionDatePicker.getValue().toString(),
                "Transaction reference: " + id
            );

            if (transactionSaved) {

                messageLabel.setText(
                    transactionType + " successful! New balance: BWP "
                    + newBalance
                );

            } else {

                messageLabel.setText(
                    "Balance saved, but transaction record could not be saved."
                );
            }

        } catch (NumberFormatException e) {

            messageLabel.setText(
                "Enter a valid numeric transaction ID and amount."
            );

        } catch (Exception e) {

            messageLabel.setText(
                "Transaction failed: " + e.getMessage()
            );
        }
    }

    @FXML
    private void handleBack() {

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                    "/banking/gui/DashboardView.fxml"
                )
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) messageLabel.getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.setTitle(
                "Access Bank Botswana Dashboard"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}