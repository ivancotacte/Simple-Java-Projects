import java.util.Random;
import java.util.Scanner;

public class FortuneTeller {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] fortunes = {
            "You will have a great day today!",
            "A surprise awaits you in the near future.",
            "Good things come to those who wait.",
            "Take a chance on something new.",
            "Your hard work will pay off soon.",
            "Stay positive, and good things will happen."
        };

        System.out.println("Welcome to the Fortune Teller!");
        System.out.print("Ask a question or type 'quit' to exit: ");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            int randomIndex = random.nextInt(fortunes.length);
            String fortune = fortunes[randomIndex];
            
            System.out.println("Fortune: " + fortune);
            System.out.print("Ask another question or type 'quit' to exit: ");
        }

        scanner.close();
    }
}
