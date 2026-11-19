
package banking.model;

public interface BankingOperations {

    void deposit(double amount);

    void withdraw(double amount);

    double getBalance();
}
