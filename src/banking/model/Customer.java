
package banking.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {

    // Attributes
    protected int customerId;
    protected String fullName;
    protected String address;
    protected String contactNumber;
    protected String email;

    // A customer can have multiple accounts
    protected List<Account> accounts;

    // Constructor
    public Customer(int customerId, String fullName,
                    String address, String contactNumber,
                    String email) {

        this.customerId = customerId;
        this.fullName = fullName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;

        this.accounts = new ArrayList<>();
    }

    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // Add an account to the customer
    public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
        }
    }

    // Update customer details
    public void updateDetails(String address,
                              String contactNumber,
                              String email) {

        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Display customer information
    public void displayDetails() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Full Name: " + fullName);
        System.out.println("Address: " + address);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Email: " + email);
    }
}
