import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double amount;
        String fromCurrency, toCurrency;
        double result;

        System.out.println("Welcome to the Currency Converter!");

        while (true) {
            System.out.print("Enter the amount: ");
            amount = scanner.nextDouble();

            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter the source currency (USD, EUR, PHP): ");
            fromCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the target currency (USD, EUR, PHP): ");
            toCurrency = scanner.nextLine().toUpperCase();

            result = convertCurrency(amount, fromCurrency, toCurrency);

            if (result == -1) {
                System.out.println("Invalid currency input. Please use USD, EUR, or PHP.");
            } else {
                System.out.println("Converted amount: " + result + " " + toCurrency);
            }

            System.out.print("Do you want to convert another amount? (yes/no): ");
            String anotherConversion = scanner.next().toLowerCase();
            if (!anotherConversion.equals("yes")) {
                System.out.println("Thank you for using the Currency Converter!");
                break;
            }
        }

        scanner.close();
    }

    public static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double usdToEurRate = 0.85; // 1 USD = 0.85 EUR
        double usdToPhpRate = 50.0; // 1 USD = 50 PHP
        double eurToPhpRate = 58.82; // 1 EUR = 58.82 PHP

        if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("USD")) {
                return amount;
            } else if (toCurrency.equals("EUR")) {
                return amount * usdToEurRate;
            } else if (toCurrency.equals("PHP")) {
                return amount * usdToPhpRate;
            }
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("USD")) {
                return amount / usdToEurRate;
            } else if (toCurrency.equals("EUR")) {
                return amount;
            } else if (toCurrency.equals("PHP")) {
                return amount * eurToPhpRate;
            }
        } else if (fromCurrency.equals("PHP")) {
            if (toCurrency.equals("USD")) {
                return amount / usdToPhpRate;
            } else if (toCurrency.equals("EUR")) {
                return amount / eurToPhpRate;
            } else if (toCurrency.equals("PHP")) {
                return amount;
            }
        }

        return -1; // Invalid currency input
    }
}
