package banking.database;

import java.util.List;

public class AccountPersistenceTest {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        List<AccountDAO.AccountRecord> accounts =
                accountDAO.getAccountsForCustomer(4001);

        if (accounts.isEmpty()) {
            System.out.println("No accounts found for customer 4001.");
        } else {
            for (AccountDAO.AccountRecord account : accounts) {
                System.out.println("Account Number: "
                        + account.getAccountNumber());

                System.out.println("Account Type: "
                        + account.getAccountType());

                System.out.println("Balance: BWP "
                        + account.getBalance());

                System.out.println("Status: "
                        + account.getStatus());

                System.out.println("----------------------");
            }
        }
    }
}
