import java.util.Scanner;

class Account {
    private int accountNumber;
    private String pin;
    private double balance;

    public Account(int accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private Account currentAccount;
    private Scanner scanner;

    public ATM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Enter your account number (or '0' to exit): ");
            int accountNumber = scanner.nextInt();

            if (accountNumber == 0) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            }

            System.out.print("Enter your PIN: ");
            String pin = scanner.next();

            Account account = authenticate(accountNumber, pin);

            if (account != null) {
                currentAccount = account;
                showMenu();
            } else {
                System.out.println("Invalid account number or PIN. Please try again.");
            }
        }
    }

    private Account authenticate(int accountNumber, String pin) {
        // In a real application, you would authenticate against a database.
        // Here, we'll use a dummy account for demonstration purposes.
        if (accountNumber == 12345 && pin.equals("1234")) {
            return new Account(accountNumber, pin, 1000.0);
        }
        return null;
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + currentAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: $" + currentAccount.getBalance());
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    if (currentAccount.withdraw(withdrawalAmount)) {
                        System.out.println("Withdrawal successful. New balance: $" + currentAccount.getBalance());
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

public class ATMSimulation {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
