package banking.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import banking.database.CustomerDAO;
import banking.database.AccountDAO;

public class CustomerRegistry {

    private static final List<Customer> customers =
            new ArrayList<>();

    private static final CustomerDAO customerDAO =
            new CustomerDAO();

    private static final AccountDAO accountDAO =
            new AccountDAO();

    // Add a customer
    public static void addCustomer(Customer customer) {
        if (customer != null
                && searchCustomer(customer.getCustomerId()) == null) {
            customers.add(customer);
        }
    }

    // Search for a customer
    public static Customer searchCustomer(int customerId) {

        for (Customer customer : customers) {

            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    // Get all customers
    public static List<Customer> getCustomers() {
        return customers;
    }

    // Load saved customers and accounts from SQLite
    public static void loadFromDatabase() {

        customers.clear();

        List<CustomerDAO.CustomerRecord> savedCustomers =
                customerDAO.getAllCustomers();

        for (CustomerDAO.CustomerRecord record : savedCustomers) {

            Customer customer;

            if ("Individual Customer".equalsIgnoreCase(record.getType())) {

                customer = new IndividualCustomer(
                    record.getId(),
                    record.getName(),
                    record.getAddress(),
                    record.getPhone(),
                    record.getEmail(),
                    record.getOmang(),
                    "",
                    "",
                    ""
                );

            } else {

                customer = new CompanyCustomer(
                    record.getId(),
                    record.getName(),
                    record.getRegistrationNumber(),
                    record.getAddress(),
                    "",
                    "",
                    "",
                    record.getPhone(),
                    record.getEmail()
                );
            }

            // Restore the customer's saved accounts
            List<AccountDAO.AccountRecord> savedAccounts =
                    accountDAO.getAccountsForCustomer(record.getId());

            for (AccountDAO.AccountRecord accountRecord : savedAccounts) {

                Account account;

                LocalDate dateOpened;

                try {
                    dateOpened = LocalDate.parse(
                        accountRecord.getDateOpened()
                    );
                } catch (Exception e) {
                    dateOpened = LocalDate.now();
                }

                String type = accountRecord.getAccountType();

                if ("Savings Account".equalsIgnoreCase(type)) {

                    account = new SavingsAccount(
                        accountRecord.getAccountNumber(),
                        accountRecord.getBalance(),
                        accountRecord.getStatus(),
                        dateOpened,
                        50
                    );

                } else if ("Investment Account".equalsIgnoreCase(type)) {

                    account = new InvestmentAccount(
                        accountRecord.getAccountNumber(),
                        accountRecord.getBalance(),
                        accountRecord.getStatus(),
                        dateOpened,
                        true
                    );

                } else if ("Cheque Account".equalsIgnoreCase(type)) {

                    account = new ChequeAccount(
                        accountRecord.getAccountNumber(),
                        accountRecord.getBalance(),
                        accountRecord.getStatus(),
                        dateOpened,
                        "",
                        ""
                    );

                } else {
                    continue;
                }

                customer.addAccount(account);
            }

            customers.add(customer);
        }

        System.out.println(
            "Customers and accounts loaded successfully."
        );
    }
}
