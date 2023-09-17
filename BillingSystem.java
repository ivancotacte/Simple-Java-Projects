import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Customer {
    private String customerID;
    private String name;
    private double previousReading;
    private double currentReading;

    public Customer(String customerID, String name, double previousReading, double currentReading) {
        this.customerID = customerID;
        this.name = name;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public double getPreviousReading() {
        return previousReading;
    }

    public double getCurrentReading() {
        return currentReading;
    }

    public double calculateElectricityConsumption() {
        return currentReading - previousReading;
    }
}

class BillingSystem {
    private List<Customer> customers;
    private Scanner scanner;

    public BillingSystem() {
        customers = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void listCustomers() {
        System.out.println("\nCustomer List:");
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getCustomerID());
            System.out.println("Name: " + customer.getName());
            System.out.println("Previous Reading: " + customer.getPreviousReading());
            System.out.println("Current Reading: " + customer.getCurrentReading());
            System.out.println("Consumption: " + customer.calculateElectricityConsumption());
            System.out.println("---------------");
        }
    }

    public void generateBills() {
        System.out.println("\nElectricity Bills:");

        for (Customer customer : customers) {
            double consumption = customer.calculateElectricityConsumption();
            double totalBill = consumption * 0.1; // Assuming $0.10 per unit

            System.out.println("Customer ID: " + customer.getCustomerID());
            System.out.println("Name: " + customer.getName());
            System.out.println("Consumption: " + consumption + " units");
            System.out.println("Total Bill: $" + totalBill);
            System.out.println("---------------");
        }
    }

    public static void main(String[] args) {
        BillingSystem billingSystem = new BillingSystem();
        billingSystem.addCustomer(new Customer("C001", "John Doe", 1000.0, 1200.0));
        billingSystem.addCustomer(new Customer("C002", "Alice Smith", 2000.0, 2200.0));

        while (true) {
            System.out.println("\nElectricity Billing System Menu:");
            System.out.println("1. List Customers");
            System.out.println("2. Generate Bills");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = billingSystem.scanner.nextInt();
            billingSystem.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    billingSystem.listCustomers();
                    break;
                case 2:
                    billingSystem.generateBills();
                    break;
                case 3:
                    System.out.println("Exiting Electricity Billing System.");
                    billingSystem.scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
