package banking.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseTables {

    public static void createTables() {

        String customers = """
            CREATE TABLE IF NOT EXISTS customers (
                customer_id INTEGER PRIMARY KEY,
                customer_type TEXT NOT NULL,
                full_name TEXT NOT NULL,
                address TEXT,
                contact_number TEXT,
                email TEXT,
                omang TEXT,
                registration_number TEXT
            )
            """;

        String accounts = """
            CREATE TABLE IF NOT EXISTS accounts (
                account_number TEXT PRIMARY KEY,
                customer_id INTEGER NOT NULL,
                account_type TEXT NOT NULL,
                balance REAL NOT NULL,
                status TEXT NOT NULL,
                date_opened TEXT,
                FOREIGN KEY (customer_id)
                    REFERENCES customers(customer_id)
            )
            """;

        String transactions = """
            CREATE TABLE IF NOT EXISTS transactions (
                transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,
                account_number TEXT NOT NULL,
                transaction_type TEXT NOT NULL,
                amount REAL NOT NULL,
                transaction_date TEXT,
                description TEXT,
                FOREIGN KEY (account_number)
                    REFERENCES accounts(account_number)
            )
            """;

        try (Connection connection =
                    DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(customers);
            statement.execute(accounts);
            statement.execute(transactions);

            System.out.println("Database tables created successfully!");

        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
