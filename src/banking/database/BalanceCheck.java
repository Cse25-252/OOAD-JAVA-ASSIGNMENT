package banking.database;

public class BalanceCheck {

    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        accountDAO.searchAccount("ACC4001");
    }
}
