package banking.model;

import java.util.ArrayList;
import java.util.List;

public class BankAdministrator {

    // Attributes
    private int id;
    private String username;
    private String password;

    private List<Customer> customers;

    // Constructor
    public BankAdministrator(int id, String username,
                             String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.customers = new ArrayList<>();
    }

    // Login
    public boolean login(String username, String password) {
        return this.username.equals(username)
                && this.password.equals(password);
    }

    // Register customer
    public void registerCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
            System.out.println("Customer registered successfully.");
        }
    }

    // Open account
    public void openAccount(Customer customer, Account account) {
        if (customer != null && account != null) {
            customer.addAccount(account);
            System.out.println("Account opened successfully.");
        }
    }

    // Process deposit
    public void processDeposit(Account account, double amount) {
        account.deposit(amount);
    }

    // Process withdrawal
    public void processWithdrawal(Account account, double amount) {
        account.withdraw(amount);
    }

    // Manage interest
    public void manageInterest(Account account) {
        double interest = account.calculateAccruedInterest();
        System.out.println("Accrued interest: BWP " + interest);
    }
}
