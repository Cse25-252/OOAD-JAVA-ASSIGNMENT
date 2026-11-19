package banking.database;

public class DatabaseTest {

    public static void main(String[] args) {

        CustomerDAO dao = new CustomerDAO();

        // CREATE: Add customer
        System.out.println("ADDING CUSTOMER");

        boolean added = dao.addCustomer(
                1001,
                "Individual",
                "Test Customer",
                "Gaborone",
                "71234567",
                "test@gmail.com",
                "123456789",
                null
        );

        System.out.println("Added: " + added);

        // READ: Search customer
        System.out.println("\nSEARCHING CUSTOMER");
        dao.searchCustomer(1001);

        // UPDATE: Update customer
        System.out.println("\nUPDATING CUSTOMER");

        boolean updated = dao.updateCustomer(
                1001,
                "Updated Customer",
                "Lobatse",
                "72345678",
                "updated@gmail.com"
        );

        System.out.println("Updated: " + updated);

        // READ: Search again to confirm update
        System.out.println("\nCHECKING UPDATED CUSTOMER");
        dao.searchCustomer(1001);

        // DELETE: Delete customer
        System.out.println("\nDELETING CUSTOMER");

        boolean deleted = dao.deleteCustomer(1001);

        System.out.println("Deleted: " + deleted);

        // READ: Confirm deletion
        System.out.println("\nCHECKING DELETION");
        dao.searchCustomer(1001);
    }
}
