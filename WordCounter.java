import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to a text file or type your text here:\n");
        String input = scanner.nextLine();

        Map<String, Integer> wordCount = new HashMap<>();

        try {
            BufferedReader reader;
            if (input.endsWith(".txt")) {
                reader = new BufferedReader(new FileReader(input));
            } else {
                reader = new BufferedReader(new java.io.StringReader(input));
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Remove non-alphabet characters
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
            reader.close();

            System.out.println("\nWord Frequency Count:");
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
