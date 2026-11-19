package banking.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    // CREATE: Add a transaction
    public boolean addTransaction(
            String accountNumber,
            String type,
            double amount,
            String date,
            String description) {

        String sql = """
            INSERT INTO transactions
            (account_number, transaction_type, amount,
             transaction_date, description)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setString(2, type);
            statement.setDouble(3, amount);
            statement.setString(4, date);
            statement.setString(5, description);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Transaction error: " + e.getMessage());
            return false;
        }
    }

    // READ: Search transactions by account number
    public void searchTransactions(String accountNumber) {

        String sql = """
            SELECT * FROM transactions
            WHERE account_number = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet result = statement.executeQuery();

            boolean found = false;

            while (result.next()) {
                found = true;

                System.out.println("Transaction ID: "
                        + result.getInt("transaction_id"));

                System.out.println("Account Number: "
                        + result.getString("account_number"));

                System.out.println("Type: "
                        + result.getString("transaction_type"));

                System.out.println("Amount: "
                        + result.getDouble("amount"));

                System.out.println("Date: "
                        + result.getString("transaction_date"));

                System.out.println("Description: "
                        + result.getString("description"));

                System.out.println("--------------------");
            }

            if (!found) {
                System.out.println("No transactions found.");
            }

        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }

    // DELETE: Delete transactions for an account
    public boolean deleteTransactions(String accountNumber) {

        String sql = """
            DELETE FROM transactions
            WHERE account_number = ?
            """;

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
