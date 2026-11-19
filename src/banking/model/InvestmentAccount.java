package banking.model;

import java.time.LocalDate;

public class InvestmentAccount extends Account {

    private double monthlyInterestRate = 0.05;

    // Constructor for creating a NEW investment account
    public InvestmentAccount(String accountNumber, double balance,
                             String status, LocalDate dateOpened) {

        super(accountNumber, balance, status, dateOpened);

        if (balance < 500) {
            throw new IllegalArgumentException(
                "Minimum initial investment deposit is BWP 500."
            );
        }
    }

    // Constructor for loading an EXISTING investment account
    public InvestmentAccount(String accountNumber, double balance,
                             String status, LocalDate dateOpened,
                             boolean loadingExistingAccount) {

        super(accountNumber, balance, status, dateOpened);
    }

    // Calculate monthly interest
    @Override
    public double calculateAccruedInterest() {
        return balance * monthlyInterestRate;
    }

    // Add monthly interest
    public void payMonthlyInterest() {
        double interest = calculateAccruedInterest();
        balance += interest;

        System.out.println("Investment interest paid: BWP " + interest);
    }
}
