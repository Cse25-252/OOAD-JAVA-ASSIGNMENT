
package banking.model;

public class CompanyCustomer extends Customer {

    private String businessRegistrationName;
    private String registrationNumber;
    private String businessAddress;
    private String natureOfBusiness;
    private String companyRegistrationDocuments;
    private String authorisedSignatoryDocuments;

    // Constructor
    public CompanyCustomer(int customerId,
                           String businessRegistrationName,
                           String registrationNumber,
                           String businessAddress,
                           String natureOfBusiness,
                           String companyRegistrationDocuments,
                           String authorisedSignatoryDocuments,
                           String contactNumber,
                           String email) {

        super(customerId, businessRegistrationName,
              businessAddress, contactNumber, email);

        this.businessRegistrationName = businessRegistrationName;
        this.registrationNumber = registrationNumber;
        this.businessAddress = businessAddress;
        this.natureOfBusiness = natureOfBusiness;
        this.companyRegistrationDocuments = companyRegistrationDocuments;
        this.authorisedSignatoryDocuments = authorisedSignatoryDocuments;
    }

    // Getters
    public String getBusinessRegistrationName() {
        return businessRegistrationName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public String getCompanyRegistrationDocuments() {
        return companyRegistrationDocuments;
    }

    public String getAuthorisedSignatoryDocuments() {
        return authorisedSignatoryDocuments;
    }

    // Display company details
    @Override
    public void displayDetails() {
        System.out.println("Company ID: " + customerId);
        System.out.println("Business Name: " + businessRegistrationName);
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Business Address: " + businessAddress);
        System.out.println("Nature of Business: " + natureOfBusiness);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Email: " + email);
    }
}
