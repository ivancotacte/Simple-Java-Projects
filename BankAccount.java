import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

public class BankingApplication {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static int accountNumberGenerator = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Banking Application Menu:");
            System.out.println("1. Create an Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    performDeposit(scanner);
                    break;
                case 3:
                    performWithdrawal(scanner);
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter the account holder's name: ");
        String accountHolder = scanner.nextLine();

        String accountNumber = "AC" + accountNumberGenerator++;
        double initialBalance = 0.0;

        BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);
        accounts.put(accountNumber, account);

        System.out.println("Account created successfully.");
        System.out.println("Account Number: " + accountNumber);
    }

    private static void performDeposit(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);

            System.out.print("Enter the deposit amount: ");
            double amount = scanner.nextDouble();

            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void performWithdrawal(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);

            System.out.print("Enter the withdrawal amount: ");
            double amount = scanner.nextDouble();

            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            System.out.println("Account Holder: " + account.getAccountHolder());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
