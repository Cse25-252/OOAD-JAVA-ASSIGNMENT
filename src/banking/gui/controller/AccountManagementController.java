package banking.gui.controller;

import banking.model.Account;
import banking.model.Customer;
import banking.model.CustomerRegistry;
import banking.model.IndividualCustomer;
import banking.model.SavingsAccount;
import banking.model.InvestmentAccount;
import banking.model.ChequeAccount;
import banking.database.AccountDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AccountManagementController {

    private AccountDAO accountDAO = new AccountDAO();

    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField customerIdField;

    @FXML
    private ComboBox<String> accountTypeBox;

    @FXML
    private TextField initialDepositField;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        accountTypeBox.getItems().addAll(
            "Savings Account",
            "Investment Account",
            "Cheque Account"
        );
    }

    @FXML
    private void handleCreateAccount() {

        try {
            String accountNumber =
                accountNumberField.getText().trim();

            int customerId =
                Integer.parseInt(customerIdField.getText().trim());

            String accountType = accountTypeBox.getValue();

            double amount =
                Double.parseDouble(initialDepositField.getText().trim());

            if (accountNumber.isEmpty() || accountType == null) {
                messageLabel.setText("Please complete all fields.");
                return;
            }

            if (amount <= 0) {
                messageLabel.setText(
                    "Deposit must be greater than zero."
                );
                return;
            }

            Customer customer =
                CustomerRegistry.searchCustomer(customerId);

            if (customer == null) {
                messageLabel.setText(
                    "Customer ID not found. Register the customer first."
                );
                return;
            }

            // Check for duplicate account numbers in the registry
            for (Customer registeredCustomer :
                    CustomerRegistry.getCustomers()) {

                for (Account existingAccount :
                        registeredCustomer.getAccounts()) {

                    if (existingAccount.getAccountNumber()
                            .equalsIgnoreCase(accountNumber)) {

                        messageLabel.setText(
                            "Account number already exists."
                        );
                        return;
                    }
                }
            }

            Account account;

            if (accountType.equals("Savings Account")) {

                if (amount < 50) {
                    messageLabel.setText(
                        "Minimum savings deposit is BWP 50."
                    );
                    return;
                }

                account = new SavingsAccount(
                    accountNumber,
                    amount,
                    "Active",
                    LocalDate.now(),
                    50
                );

            } else if (accountType.equals("Investment Account")) {

                if (amount < 500) {
                    messageLabel.setText(
                        "Minimum investment deposit is BWP 500."
                    );
                    return;
                }

                account = new InvestmentAccount(
                    accountNumber,
                    amount,
                    "Active",
                    LocalDate.now()
                );

            } else {

                if (!(customer instanceof IndividualCustomer)) {
                    messageLabel.setText(
                        "Cheque accounts require an individual customer."
                    );
                    return;
                }

                IndividualCustomer individual =
                    (IndividualCustomer) customer;

                if (individual.getEmployerName() == null
                        || individual.getEmployerName().trim().isEmpty()
                        || individual.getEmployerAddress() == null
                        || individual.getEmployerAddress().trim().isEmpty()) {

                    messageLabel.setText(
                        "Customer employer details are required for a cheque account."
                    );
                    return;
                }

                account = new ChequeAccount(
                    accountNumber,
                    amount,
                    "Active",
                    LocalDate.now(),
                    individual.getEmployerName(),
                    individual.getEmployerAddress()
                );
            }

            // Save account to SQLite database
            boolean saved = accountDAO.addAccount(
                accountNumber,
                customerId,
                accountType,
                amount,
                "Active",
                LocalDate.now().toString()
            );

            if (saved) {

                customer.addAccount(account);

                messageLabel.setText(
                    "Account created successfully for "
                    + customer.getFullName()
                );

            } else {
                messageLabel.setText(
                    "Could not save account. Check the account number "
                    + "or customer database record."
                );
            }

        } catch (NumberFormatException e) {

            messageLabel.setText(
                "Enter a valid numeric Customer ID and deposit."
            );

        } catch (Exception e) {

            messageLabel.setText(
                "Account creation failed: " + e.getMessage()
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

            Stage stage =
                (Stage) messageLabel.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle(
                "Access Bank Botswana Dashboard"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
