import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean checkedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.checkedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkOut() {
        checkedOut = true;
    }

    public void checkIn() {
        checkedOut = false;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Check Out Book");
            System.out.println("3. Check In Book");
            System.out.println("4. List Books");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    Book newBook = new Book(title, author);
                    library.addBook(newBook);
                    System.out.println("Book added successfully!");
                    break;
                case 2:
                    System.out.print("Enter book title to check out: ");
                    String checkoutTitle = scanner.nextLine();
                    Book checkoutBook = library.findBook(checkoutTitle);
                    if (checkoutBook != null && !checkoutBook.isCheckedOut()) {
                        checkoutBook.checkOut();
                        System.out.println("Book checked out successfully!");
                    } else {
                        System.out.println("Book not found or already checked out.");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title to check in: ");
                    String checkinTitle = scanner.nextLine();
                    Book checkinBook = library.findBook(checkinTitle);
                    if (checkinBook != null && checkinBook.isCheckedOut()) {
                        checkinBook.checkIn();
                        System.out.println("Book checked in successfully!");
                    } else {
                        System.out.println("Book not found or not checked out.");
                    }
                    break;
                case 4:
                    List<Book> books = library.getBooks();
                    System.out.println("\nList of Books in the Library:");
                    for (Book book : books) {
                        String status = book.isCheckedOut() ? "Checked Out" : "Available";
                        System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Status: " + status);
                    }
                    break;
                case 5:
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
