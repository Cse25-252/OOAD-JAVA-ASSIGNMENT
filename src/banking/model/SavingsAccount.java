
package banking.model;

import java.time.LocalDate;

public class SavingsAccount extends Account {

    private double minimumBalance;
    private double monthlyInterestRate = 0.0005;

    public SavingsAccount(String accountNumber, double balance,
                          String status, LocalDate dateOpened,
                          double minimumBalance) {

        super(accountNumber, balance, status, dateOpened);
        this.minimumBalance = minimumBalance;
    }

    // Savings accounts do not allow withdrawals
    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed on savings accounts.");
    }

    // Calculate monthly interest
    @Override
    public double calculateAccruedInterest() {

        if (balance >= minimumBalance) {
            return balance * monthlyInterestRate;
        }

        return 0;
    }

    // Pay interest into the account
    public void payMonthlyInterest() {
        double interest = calculateAccruedInterest();
        balance += interest;

        System.out.println("Interest paid: BWP " + interest);
    }
}
