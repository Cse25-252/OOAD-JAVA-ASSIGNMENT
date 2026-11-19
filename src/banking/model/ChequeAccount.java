
package banking.model;

import java.time.LocalDate;

public class ChequeAccount extends Account {

    private String employerName;
    private String employerAddress;

    // Constructor
    public ChequeAccount(String accountNumber, double balance,
                         String status, LocalDate dateOpened,
                         String employerName, String employerAddress) {

        super(accountNumber, balance, status, dateOpened);

        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    // Credit monthly salary
    public void creditSalary(double salary) {
        if (salary > 0) {
            deposit(salary);
            System.out.println("Salary credited successfully.");
        } else {
            System.out.println("Invalid salary amount.");
        }
    }

    // Calculate interest
    @Override
    public double calculateAccruedInterest() {
        return 0;
    }

    // Get employer details
    public String getEmployerName() {
        return employerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }
}
