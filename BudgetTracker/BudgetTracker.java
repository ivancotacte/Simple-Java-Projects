package BudgetTracker;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();
        double balance = 0.0;

        System.out.println("Welcome to the Budget Tracker!");

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. View Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter income description: ");
                    String incomeDesc = scanner.nextLine();
                    System.out.print("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    transactions.add(new Transaction(incomeDesc, incomeAmount));
                    balance += incomeAmount;
                    System.out.println("Income added successfully!");
                    break;
                case 2:
                    System.out.print("Enter expense description: ");
                    String expenseDesc = scanner.nextLine();
                    System.out.print("Enter expense amount: ");
                    double expenseAmount = scanner.nextDouble();
                    transactions.add(new Transaction(expenseDesc, -expenseAmount));
                    balance -= expenseAmount;
                    System.out.println("Expense added successfully!");
                    break;
                case 3:
                    System.out.println("\nTRANSACTIONS:");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction.description + ": " + transaction.amount);
                    }
                    break;
                case 4:
                    System.out.println("\nBALANCE: " + balance);
                    break;
                case 5:
                    System.out.println("Thank you for using the Budget Tracker!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}