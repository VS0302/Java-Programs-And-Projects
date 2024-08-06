package project.main;
import java.util.Scanner;
import project.dao.ProductDAO;
import project.pojo.Product;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Inventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. List All Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Product
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Product newProduct = new Product(0, name, description, price, quantity);
                    dao.addProduct(newProduct);
                    System.out.println("Product added successfully!");
                    break;

                case 2: // Update Product
                    System.out.print("Enter product ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Product existingProduct = dao.getProduct(updateId);
                    if (existingProduct != null) {
                        System.out.print("Enter new name (current: " + existingProduct.getName() + "): ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new description (current: " + existingProduct.getDescription() + "): ");
                        String newDescription = scanner.nextLine();
                        System.out.print("Enter new price (current: " + existingProduct.getPrice() + "): ");
                        double newPrice = scanner.nextDouble();
                        System.out.print("Enter new quantity (current: " + existingProduct.getQuantity() + "): ");
                        int newQuantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        existingProduct.setName(newName);
                        existingProduct.setDescription(newDescription);
                        existingProduct.setPrice(newPrice);
                        existingProduct.setQuantity(newQuantity);

                        dao.updateProduct(existingProduct);
                        System.out.println("Product updated successfully!");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3: // Delete Product
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    dao.deleteProduct(deleteId);
                    System.out.println("Product deleted successfully!");
                    break;

                case 4: // List All Products
                    List<Product> products = dao.getAllProducts();
                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;

                case 0: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }
}