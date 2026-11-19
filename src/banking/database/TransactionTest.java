package banking.database;

public class TransactionTest {

    public static void main(String[] args) {

        TransactionDAO transactionDAO = new TransactionDAO();

        System.out.println("CHECKING INVESTMENT ACCOUNT TRANSACTIONS");

        transactionDAO.searchTransactions("1");

        System.out.println("\nTRANSACTION CHECK COMPLETED.");
    }
}
