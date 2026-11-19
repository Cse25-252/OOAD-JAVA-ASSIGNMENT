package banking.gui.controller;

import banking.model.Customer;
import banking.model.CustomerRegistry;
import banking.model.IndividualCustomer;
import banking.model.CompanyCustomer;
import banking.database.CustomerDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerManagementController {

    private CustomerDAO customerDAO = new CustomerDAO();

    @FXML private ComboBox<String> customerTypeBox;

    @FXML private TextField customerIdField;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    @FXML private TextField omangField;
    @FXML private TextField dateOfBirthField;
    @FXML private TextField employerNameField;
    @FXML private TextField employerAddressField;

    @FXML private TextField registrationNumberField;
    @FXML private TextField natureOfBusinessField;
    @FXML private TextField companyDocumentsField;
    @FXML private TextField signatoryDocumentsField;

    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        customerTypeBox.getItems().addAll(
            "Individual Customer",
            "Company Customer"
        );
    }

    @FXML
    private void handleRegister() {

        try {
            int id = Integer.parseInt(
                customerIdField.getText().trim()
            );

            if (CustomerRegistry.searchCustomer(id) != null) {
                messageLabel.setText("Customer ID already exists.");
                return;
            }

            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || address.isEmpty()
                    || phone.isEmpty() || email.isEmpty()) {
                messageLabel.setText(
                    "Please complete all basic details."
                );
                return;
            }

            String type = customerTypeBox.getValue();

            if (type == null) {
                messageLabel.setText(
                    "Please select a customer type."
                );
                return;
            }

            Customer customer;

            String omang = null;
            String registrationNumber = null;

            if (type.equals("Individual Customer")) {

                omang = omangField.getText().trim();

                if (omang.isEmpty()
                        || dateOfBirthField.getText().trim().isEmpty()) {
                    messageLabel.setText(
                        "Enter OMANG and date of birth."
                    );
                    return;
                }

                customer = new IndividualCustomer(
                    id, name, address, phone, email,
                    omang,
                    dateOfBirthField.getText().trim(),
                    employerNameField.getText().trim(),
                    employerAddressField.getText().trim()
                );

            } else {

                registrationNumber =
                    registrationNumberField.getText().trim();

                if (registrationNumber.isEmpty()) {
                    messageLabel.setText(
                        "Enter the company registration number."
                    );
                    return;
                }

                customer = new CompanyCustomer(
                    id, name,
                    registrationNumber,
                    address,
                    natureOfBusinessField.getText().trim(),
                    companyDocumentsField.getText().trim(),
                    signatoryDocumentsField.getText().trim(),
                    phone, email
                );
            }

            boolean saved = customerDAO.addCustomer(
                id,
                type,
                name,
                address,
                phone,
                email,
                omang,
                registrationNumber
            );

            if (saved) {
                CustomerRegistry.addCustomer(customer);
                messageLabel.setText(
                    "Customer registered successfully."
                );
            } else {
                messageLabel.setText(
                    "Could not save customer to database."
                );
            }

        } catch (NumberFormatException e) {
            messageLabel.setText(
                "Customer ID must be a number."
            );

        } catch (Exception e) {
            messageLabel.setText(
                "Registration failed: " + e.getMessage()
            );
        }
    }

    @FXML
    private void handleSearch() {

        try {
            int id = Integer.parseInt(
                customerIdField.getText().trim()
            );

            Customer customer = CustomerRegistry.searchCustomer(id);

            if (customer == null) {
                messageLabel.setText("Customer not found.");
            } else {
                messageLabel.setText(
                    "Customer found: " + customer.getFullName()
                    + " | Contact: " + customer.getContactNumber()
                );
            }

        } catch (NumberFormatException e) {
            messageLabel.setText(
                "Enter a valid customer ID."
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

            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(
                "Access Bank Botswana Dashboard"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
