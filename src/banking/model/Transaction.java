
package banking.model;

import java.time.LocalDateTime;

public class Transaction {

    private int transactionId;
    private String transactionType;
    private double amount;
    private LocalDateTime dateTime;
    private String description;

    // Constructor
    public Transaction(int transactionId, String transactionType,
                       double amount, String description) {

        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    // Get transaction details
    public void getTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: BWP " + amount);
        System.out.println("Date and Time: " + dateTime);
        System.out.println("Description: " + description);
    }
}
