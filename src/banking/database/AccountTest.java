package banking.database;

public class AccountTest {

    public static void main(String[] args) {

        CustomerDAO customerDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();

        // First create a customer
        System.out.println("CREATING TEST CUSTOMER");

        boolean customerAdded = customerDAO.addCustomer(
                2001,
                "Individual",
                "Account Test Customer",
                "Gaborone",
                "71234567",
                "accounttest@gmail.com",
                "123456789",
                null
        );

        System.out.println("Customer added: " + customerAdded);

        // CREATE: Add an account
        System.out.println("\nADDING ACCOUNT");

        boolean added = accountDAO.addAccount(
                "ACC2001",
                2001,
                "Cheque",
                1000.00,
                "Active",
                "2026-11-19"
        );

        System.out.println("Account added: " + added);

        // READ: Search for account
        System.out.println("\nSEARCHING ACCOUNT");
        accountDAO.searchAccount("ACC2001");

        // UPDATE: Change account balance
        System.out.println("\nUPDATING ACCOUNT");

        boolean updated = accountDAO.updateBalance(
                "ACC2001",
                1500.00
        );

        System.out.println("Account updated: " + updated);

        // READ: Confirm updated balance
        System.out.println("\nCHECKING UPDATED ACCOUNT");
        accountDAO.searchAccount("ACC2001");

        // DELETE: Delete account
        System.out.println("\nDELETING ACCOUNT");

        boolean deleted = accountDAO.deleteAccount("ACC2001");

        System.out.println("Account deleted: " + deleted);

        // Confirm deletion
        System.out.println("\nCHECKING DELETION");
        accountDAO.searchAccount("ACC2001");

        // Delete test customer
        System.out.println("\nDELETING TEST CUSTOMER");
        System.out.println(
                "Customer deleted: " +
                customerDAO.deleteCustomer(2001)
        );
    }
}
