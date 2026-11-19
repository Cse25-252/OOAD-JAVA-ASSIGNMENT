
package banking.model;

public class IndividualCustomer extends Customer {

    // Attributes
    private String omang;
    private String dateOfBirth;
    private String employerName;
    private String employerAddress;

    // Constructor
    public IndividualCustomer(int customerId, String fullName,
            String address, String contactNumber, String email,
            String omang, String dateOfBirth,
            String employerName, String employerAddress) {

        super(customerId, fullName, address, contactNumber, email);

        this.omang = omang;
        this.dateOfBirth = dateOfBirth;
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    // Getters
    public String getOmang() {
        return omang;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmployerName() {
        return employerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    // Update employment details
    public void updateEmploymentDetails(String employerName,
                                        String employerAddress) {
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    // Display individual customer details
    @Override
    public void displayDetails() {
        super.displayDetails();

        System.out.println("OMANG: " + omang);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Employer: " + employerName);
        System.out.println("Employer Address: " + employerAddress);
    }
}
