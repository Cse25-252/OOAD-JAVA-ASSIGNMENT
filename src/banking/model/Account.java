package banking.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements BankingOperations {

    // Attributes
    protected String accountNumber;
    protected double balance;
    protected String status;
    protected LocalDate dateOpened;
    protected List<Transaction> transactions;

    // Constructor
    public Account(String accountNumber, double balance,
                   String status, LocalDate dateOpened) {

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
        this.dateOpened = dateOpened;
        this.transactions = new ArrayList<>();
    }

    // Deposit money
    public void deposit(double amount) {
    if (amount > 0 && status.equalsIgnoreCase("Active")) {
        balance += amount;

        Transaction transaction = new Transaction(
            transactions.size() + 1,
            "Deposit",
            amount,
            "Money deposited into account"
        );

        transactions.add(transaction);

        System.out.println("Deposit successful.");
    } else {
        System.out.println("Invalid deposit.");
    }
}

    // Withdraw money
    public void withdraw(double amount) {
    if (amount > 0 && amount <= balance
            && status.equalsIgnoreCase("Active")) {

        balance -= amount;

        Transaction transaction = new Transaction(
            transactions.size() + 1,
            "Withdrawal",
            amount,
            "Money withdrawn from account"
        );

        transactions.add(transaction);

        System.out.println("Withdrawal successful.");
    } else {
        System.out.println("Withdrawal unsuccessful.");
    }
}

    // Get account balance
    public double getBalance() {
        return balance;
    }

    public void viewTransactions() {
    if (transactions.isEmpty()) {
        System.out.println("No transactions found.");
    } else {
        for (Transaction transaction : transactions) {
            transaction.getTransactionDetails();
            System.out.println("----------------------");
        }
    }
}

    // Calculate interest (implemented by subclasses)
    public abstract double calculateAccruedInterest();

    // Close account
    public void closeAccount() {
        status = "Closed";
        System.out.println("Account closed.");
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }
}
