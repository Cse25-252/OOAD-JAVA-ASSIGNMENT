package banking.model;

import java.time.LocalDate;

public class bank {

    public static void main(String[] args) {

    InvestmentAccount account = new InvestmentAccount(
    "I001", 1000, "Active",
    LocalDate.now()
);
       System.out.println("Savings interest: BWP "
                   + account.calculateAccruedInterest());

account.payMonthlyInterest();

System.out.println("Balance after interest: BWP "
                   + account.getBalance());
    }
}
