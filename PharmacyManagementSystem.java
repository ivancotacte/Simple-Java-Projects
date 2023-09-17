import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Medicine {
    private String name;
    private double price;
    private int quantity;

    public Medicine(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Prescription {
    private int prescriptionNumber;
    private String patientName;
    private Map<String, Integer> prescribedMedicines;

    public Prescription(int prescriptionNumber, String patientName) {
        this.prescriptionNumber = prescriptionNumber;
        this.patientName = patientName;
        this.prescribedMedicines = new HashMap<>();
    }

    public int getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void addMedicine(String medicineName, int quantity) {
        prescribedMedicines.put(medicineName, quantity);
    }

    public Map<String, Integer> getPrescribedMedicines() {
        return prescribedMedicines;
    }
}

public class PharmacyManagementSystem {
    private List<Medicine> medicines;
    private List<Prescription> prescriptions;
    private int prescriptionCounter;
    private Scanner scanner;

    public PharmacyManagementSystem() {
        medicines = new ArrayList<>();
        prescriptions = new ArrayList<>();
        prescriptionCounter = 1;
        scanner = new Scanner(System.in);
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public void listMedicines() {
        System.out.println("\nAvailable Medicines:");
        for (Medicine medicine : medicines) {
            System.out.println(medicine.getName() + " - Price: $" + medicine.getPrice() +
                    " - Quantity: " + medicine.getQuantity());
        }
    }

    public void createPrescription() {
        System.out.print("Enter patient's name: ");
        String patientName = scanner.nextLine();

        Prescription prescription = new Prescription(prescriptionCounter++, patientName);

        while (true) {
            System.out.print("Enter medicine name (or 'done' to finish prescription): ");
            String medicineName = scanner.nextLine();

            if (medicineName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Medicine medicine = findMedicine(medicineName);
            if (medicine != null && medicine.getQuantity() >= quantity) {
                prescription.addMedicine(medicineName, quantity);
                medicine.setQuantity(medicine.getQuantity() - quantity);
            } else {
                System.out.println("Medicine not found or insufficient quantity.");
            }
        }

        prescriptions.add(prescription);
        System.out.println("Prescription created successfully.");
    }

    private Medicine findMedicine(String medicineName) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(medicineName)) {
                return medicine;
            }
        }
        return null;
    }

    public void listPrescriptions() {
        System.out.println("\nPrescriptions:");
        for (Prescription prescription : prescriptions) {
            System.out.println("Prescription #" + prescription.getPrescriptionNumber() +
                    " - Patient: " + prescription.getPatientName());

            System.out.println("Medicines:");
            Map<String, Integer> prescribedMedicines = prescription.getPrescribedMedicines();
            for (String medicineName : prescribedMedicines.keySet()) {
                int quantity = prescribedMedicines.get(medicineName);
                System.out.println(medicineName + " - Quantity: " + quantity);
            }
        }
    }

    public static void main(String[] args) {
        PharmacyManagementSystem pharmacySystem = new PharmacyManagementSystem();
        pharmacySystem.addMedicine(new Medicine("Aspirin", 5.99, 100));
        pharmacySystem.addMedicine(new Medicine("Paracetamol", 3.99, 150));

        while (true) {
            System.out.println("\nPharmacy Management System Menu:");
            System.out.println("1. List Medicines");
            System.out.println("2. Create Prescription");
            System.out.println("3. List Prescriptions");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = pharmacySystem.scanner.nextInt();
            pharmacySystem.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    pharmacySystem.listMedicines();
                    break;
                case 2:
                    pharmacySystem.createPrescription();
                    break;
                case 3:
                    pharmacySystem.listPrescriptions();
                    break;
                case 4:
                    System.out.println("Exiting Pharmacy Management System.");
                    pharmacySystem.scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
