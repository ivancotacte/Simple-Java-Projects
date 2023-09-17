import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
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

class ShoppingCartItem {
    private Product product;
    private int quantity;

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

class ShoppingCart {
    private List<ShoppingCartItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        items.add(new ShoppingCartItem(product, quantity));
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}

public class SupermarketBillingSystem {
    private List<Product> products;
    private ShoppingCart cart;
    private Scanner scanner;

    public SupermarketBillingSystem() {
        products = new ArrayList<>();
        cart = new ShoppingCart();
        scanner = new Scanner(System.in);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void listProducts() {
        System.out.println("\nAvailable Products:");
        for (Product product : products) {
            System.out.println(product.getName() + " - Price: $" + product.getPrice() +
                    " - Quantity: " + product.getQuantity());
        }
    }

    public void addToCart() {
        System.out.print("Enter product name to add to cart: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product product = findProduct(productName);
        if (product != null && product.getQuantity() >= quantity) {
            cart.addItem(product, quantity);
            product.setQuantity(product.getQuantity() - quantity);
            System.out.println("Added to cart.");
        } else {
            System.out.println("Product not found or insufficient quantity.");
        }
    }

    public void viewCart() {
        List<ShoppingCartItem> cartItems = cart.getItems();

        System.out.println("\nShopping Cart:");
        double total = 0.0;

        for (ShoppingCartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            double itemTotal = product.getPrice() * quantity;
            total += itemTotal;

            System.out.println(product.getName() + " - Price: $" + product.getPrice() +
                    " - Quantity: " + quantity + " - Total: $" + itemTotal);
        }

        System.out.println("Total: $" + total);
    }

    public void checkout() {
        viewCart();
        cart.clear();
        System.out.println("Thank you for shopping!");
    }

    private Product findProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SupermarketBillingSystem billingSystem = new SupermarketBillingSystem();
        billingSystem.addProduct(new Product("Apple", 0.5, 100));
        billingSystem.addProduct(new Product("Banana", 0.3, 150));

        while (true) {
            System.out.println("\nSupermarket Billing System Menu:");
            System.out.println("1. List Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = billingSystem.scanner.nextInt();
            billingSystem.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    billingSystem.listProducts();
                    break;
                case 2:
                    billingSystem.addToCart();
                    break;
                case 3:
                    billingSystem.viewCart();
                    break;
                case 4:
                    billingSystem.checkout();
                    break;
                case 5:
                    System.out.println("Exiting Supermarket Billing System.");
                    billingSystem.scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
