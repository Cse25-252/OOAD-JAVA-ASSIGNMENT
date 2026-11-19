
package banking.model;

import java.time.LocalDate;

public class InvestmentAccount extends Account {

    private double monthlyInterestRate = 0.05;

    // Constructor
    public InvestmentAccount(String accountNumber, double balance,
                             String status, LocalDate dateOpened) {

        super(accountNumber, balance, status, dateOpened);

        if (balance < 500) {
            throw new IllegalArgumentException(
                "Minimum initial investment deposit is BWP 500."
            );
        }
    }

    // Calculate monthly interest
    @Override
    public double calculateAccruedInterest() {
        return balance * monthlyInterestRate;
    }

    // Add monthly interest to the investment
    public void payMonthlyInterest() {
        double interest = calculateAccruedInterest();
        balance += interest;

        System.out.println("Investment interest paid: BWP " + interest);
    }
}
