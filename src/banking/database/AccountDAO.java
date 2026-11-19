package banking.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // Stores account information retrieved from SQLite
    public static class AccountRecord {

        private String accountNumber;
        private int customerId;
        private String accountType;
        private double balance;
        private String status;
        private String dateOpened;

        public AccountRecord(
                String accountNumber,
                int customerId,
                String accountType,
                double balance,
                String status,
                String dateOpened) {

            this.accountNumber = accountNumber;
            this.customerId = customerId;
            this.accountType = accountType;
            this.balance = balance;
            this.status = status;
            this.dateOpened = dateOpened;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getAccountType() {
            return accountType;
        }

        public double getBalance() {
            return balance;
        }

        public String getStatus() {
            return status;
        }

        public String getDateOpened() {
            return dateOpened;
        }
    }

    // CREATE: Add an account
    public boolean addAccount(
            String accountNumber,
            int customerId,
            String accountType,
            double balance,
            String status,
            String dateOpened) {

        String sql = """
            INSERT INTO accounts
            (account_number, customer_id, account_type,
             balance, status, date_opened)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setInt(2, customerId);
            statement.setString(3, accountType);
            statement.setDouble(4, balance);
            statement.setString(5, status);
            statement.setString(6, dateOpened);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Account error: " + e.getMessage());
            return false;
        }
    }

    // READ: Search for an account
    public void searchAccount(String accountNumber) {

        String sql =
                "SELECT * FROM accounts WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                System.out.println("Account Number: "
                        + result.getString("account_number"));
                System.out.println("Customer ID: "
                        + result.getInt("customer_id"));
                System.out.println("Account Type: "
                        + result.getString("account_type"));
                System.out.println("Balance: "
                        + result.getDouble("balance"));
                System.out.println("Status: "
                        + result.getString("status"));
            } else {
                System.out.println("Account not found.");
            }

        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }

    // READ: Retrieve all saved accounts for a customer
    public List<AccountRecord> getAccountsForCustomer(int customerId) {

        List<AccountRecord> accounts = new ArrayList<>();

        String sql = """
            SELECT * FROM accounts
            WHERE customer_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                AccountRecord account = new AccountRecord(
                    result.getString("account_number"),
                    result.getInt("customer_id"),
                    result.getString("account_type"),
                    result.getDouble("balance"),
                    result.getString("status"),
                    result.getString("date_opened")
                );

                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println("Account loading error: " + e.getMessage());
        }

        return accounts;
    }

    // UPDATE: Update account balance
    public boolean updateBalance(
            String accountNumber, double newBalance) {

        String sql = """
            UPDATE accounts
            SET balance = ?
            WHERE account_number = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, newBalance);
            statement.setString(2, accountNumber);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    // DELETE: Delete an account
    public boolean deleteAccount(String accountNumber) {

        String sql =
                "DELETE FROM accounts WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }
}
