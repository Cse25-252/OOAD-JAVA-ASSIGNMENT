package banking.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Stores customer information retrieved from SQLite
    public static class CustomerRecord {

        private int id;
        private String type;
        private String name;
        private String address;
        private String phone;
        private String email;
        private String omang;
        private String registrationNumber;

        public CustomerRecord(
                int id, String type, String name,
                String address, String phone, String email,
                String omang, String registrationNumber) {

            this.id = id;
            this.type = type;
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.omang = omang;
            this.registrationNumber = registrationNumber;
        }

        public int getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getOmang() {
            return omang;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }
    }

    // CREATE: Add a customer
    public boolean addCustomer(
            int id, String type, String name,
            String address, String phone, String email,
            String omang, String registrationNumber) {

        String sql = """
            INSERT INTO customers
            (customer_id, customer_type, full_name,
             address, contact_number, email,
             omang, registration_number)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.setString(2, type);
            statement.setString(3, name);
            statement.setString(4, address);
            statement.setString(5, phone);
            statement.setString(6, email);
            statement.setString(7, omang);
            statement.setString(8, registrationNumber);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Customer error: " + e.getMessage());
            return false;
        }
    }

    // READ: Search for a customer
    public void searchCustomer(int id) {

        String sql =
                "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                System.out.println("Customer ID: "
                        + result.getInt("customer_id"));
                System.out.println("Name: "
                        + result.getString("full_name"));
                System.out.println("Email: "
                        + result.getString("email"));
                System.out.println("Phone: "
                        + result.getString("contact_number"));
            } else {
                System.out.println("Customer not found.");
            }

        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }

    // READ: Retrieve all saved customers
    public List<CustomerRecord> getAllCustomers() {

        List<CustomerRecord> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                CustomerRecord customer = new CustomerRecord(
                    result.getInt("customer_id"),
                    result.getString("customer_type"),
                    result.getString("full_name"),
                    result.getString("address"),
                    result.getString("contact_number"),
                    result.getString("email"),
                    result.getString("omang"),
                    result.getString("registration_number")
                );

                customers.add(customer);
            }

        } catch (SQLException e) {
            System.out.println("Customer loading error: " + e.getMessage());
        }

        return customers;
    }

    // UPDATE: Update customer details
    public boolean updateCustomer(
            int id, String name, String address,
            String phone, String email) {

        String sql = """
            UPDATE customers
            SET full_name = ?, address = ?,
                contact_number = ?, email = ?
            WHERE customer_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setInt(5, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Delete a customer
    public boolean deleteCustomer(int id) {

        String sql =
                "DELETE FROM customers WHERE customer_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }
}
