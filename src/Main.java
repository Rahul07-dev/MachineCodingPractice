//import models.Order;
//import models.Product;
//import services.OrderService;
//import services.ProductService;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//    public static void main(String[] args) {
//        ProductService p = ProductService.getInstance();
//        OrderService o   = OrderService.getInstance();
//        Product p1= new Product("bathtub", "easy bath");
//        Product p2  = new Product("Book","contains knowledge" );
//        Product p3 = new Product("Diary","contains specific knowledge" );
//        Product p4 = new Product("Football","contains football");
//        p.addProductToInventory(p1,100);
//        p.addProductToInventory(p2,120);
//        p.addProductToInventory(p3,130);
//        p.addProductToInventory(p4,140);
//        p.printInventory();
//        Map<String,Integer> newOrderList= new HashMap<>();
//        newOrderList.put("bathtub",10);
//        newOrderList.put("Book",12);
//        o.generateOrder(1,newOrderList);
//        o.printALLOrderStatus();
//        o.confirmOrder(1);
//        o.printALLOrderStatus();
//        o.confirmOrder(1);
//        p.printInventory();
//        o.confirmOrder(1);
//        p.printInventory();
//
//    }
//}


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import models.Order;
import models.Product;
import services.OrderService;
import services.ProductService;

public class Main {

    private static final ProductService productService = ProductService.getInstance();
    private static final OrderService orderService = OrderService.getInstance();
    private static final Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {


        printMenu();

        while (true) {
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addProductToInventory();
                    break;
                case 2:
                    printInventory();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    printOrderStatus();
                    break;
                case 5:
                    confirmOrder();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();  // Close Scanner to avoid resource leaks
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n** Inventory Management System **");
        System.out.println("1. Add Product to Inventory");
        System.out.println("2. Print Inventory");
        System.out.println("3. Create Order");
        System.out.println("4. Print Order Status");
        System.out.println("5. Confirm Order");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after integer input
        return choice;
    }

    private static void addProductToInventory() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after integer input

        Product product = new Product(name, description);
        productService.addProductToInventory(product, quantity);
        System.out.println("Product added successfully!");
    }

    private static void printInventory() {
        productService.printInventory();
    }

    private static void createOrder() {
        Map<String, Integer> orderList = new HashMap<>();

        System.out.println("Enter product name and quantity (separated by comma):");
        String inputLine = scanner.nextLine();

        String[] parts = inputLine.split(",");
        for (String part : parts) {
            String[] productInfo = part.trim().split(" ");
            if (productInfo.length == 2) {
                String productName = productInfo[0];
                int quantity = Integer.parseInt(productInfo[1]);
                orderList.put(productName, quantity);
            } else {
                System.out.println("Invalid format. Skipping product: " + part);
            }
        }

        orderService.generateOrder(1, orderList); // Replace 1 with actual order ID generation logic
        System.out.println("Order created successfully!");
    }

    private static void printOrderStatus() {
        orderService.printALLOrderStatus();
    }

    private static void confirmOrder() {
        System.out.print("Enter order ID to confirm: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after integer input

        if (orderService.confirmOrder(orderId)) {
            System.out.println("Order confirmed successfully!");
        } else {
            System.out.println("Order confirmation failed. Please check order ID.");
        }
    }
}