import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NotesPasswordManager {
    private static final String NOTES_FILE = "notes.txt";
    private static final String PASSWORDS_FILE = "passwords.txt";
    private static Map<String, String> notes = new HashMap<>();
    private static Map<String, String> passwords = new HashMap<>();

    public static void main(String[] args) {
        loadNotesAndPasswords();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Notes and Password Manager Menu:");
            System.out.println("1. Add Note");
            System.out.println("2. Retrieve Note");
            System.out.println("3. Store Password");
            System.out.println("4. Retrieve Password");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    retrieveNote(scanner);
                    break;
                case 3:
                    storePassword(scanner);
                    break;
                case 4:
                    retrievePassword(scanner);
                    break;
                case 5:
                    saveNotesAndPasswords();
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadNotesAndPasswords() {
        try {
            BufferedReader notesReader = new BufferedReader(new FileReader(NOTES_FILE));
            String line;
            while ((line = notesReader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    notes.put(parts[0], parts[1]);
                }
            }
            notesReader.close();

            BufferedReader passwordsReader = new BufferedReader(new FileReader(PASSWORDS_FILE));
            while ((line = passwordsReader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    passwords.put(parts[0], parts[1]);
                }
            }
            passwordsReader.close();
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveNotesAndPasswords() {
        try {
            BufferedWriter notesWriter = new BufferedWriter(new FileWriter(NOTES_FILE));
            for (Map.Entry<String, String> entry : notes.entrySet()) {
                notesWriter.write(entry.getKey() + ":" + entry.getValue());
                notesWriter.newLine();
            }
            notesWriter.close();

            BufferedWriter passwordsWriter = new BufferedWriter(new FileWriter(PASSWORDS_FILE));
            for (Map.Entry<String, String> entry : passwords.entrySet()) {
                passwordsWriter.write(entry.getKey() + ":" + entry.getValue());
                passwordsWriter.newLine();
            }
            passwordsWriter.close();
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter a note title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the note content: ");
        String content = scanner.nextLine();
        notes.put(title, content);
        System.out.println("Note added successfully.");
    }

    private static void retrieveNote(Scanner scanner) {
        System.out.print("Enter the note title: ");
        String title = scanner.nextLine();
        String content = notes.get(title);
        if (content != null) {
            System.out.println("Note Content:");
            System.out.println(content);
        } else {
            System.out.println("Note not found.");
        }
    }

    private static void storePassword(Scanner scanner) {
        System.out.print("Enter a website or application name: ");
        String website = scanner.nextLine();
        System.out.print("Enter the password: ");
        String password = scanner.nextLine();
        passwords.put(website, password);
        System.out.println("Password stored successfully.");
    }

    private static void retrievePassword(Scanner scanner) {
        System.out.print("Enter the website or application name: ");
        String website = scanner.nextLine();
        String password = passwords.get(website);
        if (password != null) {
            System.out.println("Password: " + password);
        } else {
            System.out.println("Password not found.");
        }
    }
}
