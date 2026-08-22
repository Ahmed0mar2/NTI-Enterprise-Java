import java.util.Arrays;
import java.util.Scanner;

public class StoreManagementSystem {
    public static void main(String[] args) {
        int productCount = 0;
        final int MAX_PRODUCTS = 100;
        int[] productCodes = new int[MAX_PRODUCTS];
        String[] productNames = new String[MAX_PRODUCTS];
        double[] prices = new double[MAX_PRODUCTS];
        int[] stockQuantities = new int[MAX_PRODUCTS];
        int[] soldQuantities = new int[MAX_PRODUCTS];
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== STORE MANAGEMENT SYSTEM =====\n" +
                    "1. Add New Product\n" +
                    "2. Display All Products\n" +
                    "3. Sell Product\n" +
                    "4. Restock Product\n" +
                    "5. Search Product by Code\n" +
                    "6. Show Low Stock Alert (quantity < 5)\n" +
                    "7. Calculate Total Inventory Value\n" +
                    "8. Show Sales Report\n" +
                    "9. Show Best Selling Product\n" +
                    "0. Exit\n" +
                    "====================================\n" +
                    "Enter your choice:");
            int choice = sc.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
                case 1:
                    productCount = addProduct(productCodes, productNames, prices, stockQuantities, soldQuantities,
                            productCount, MAX_PRODUCTS);
                    break;
                case 2:
                    displayAllProducts(productCodes, productNames, prices, stockQuantities, soldQuantities,
                            productCount);
                    break;
                case 3:
                    sellProduct(productCodes, prices, stockQuantities, soldQuantities, productCount);
                    break;
                case 4:
                    restockProduct(productCodes, stockQuantities, soldQuantities, productCount);
                    break;
                case 5:
                    searchByCode(productCodes, productNames, prices, stockQuantities, soldQuantities, productCount);
                    break;
                case 6:
                    lowStockAlert(productCodes, productNames, prices, stockQuantities, soldQuantities, productCount);
                    break;
                case 7:
                    totalInventoryValue(prices, stockQuantities, productCount);
                    break;
                case 8:
                    salesReport(prices, soldQuantities, productCount);
                    break;
                case 9:
                    bestSellingProduct(productCodes, productNames, prices, stockQuantities, soldQuantities,
                            productCount);
                    break;
                default:
                    System.out.println("Invalid option! pleaser enter a valid one!");
                    break;

            }

        }
        displayAllProducts(productCodes, productNames, prices, stockQuantities, soldQuantities, productCount);
    }

    static int addProduct(int[] codes, String[] names, double[] prices, int[] stock, int[] sold, int count,
            int maxSize) {
        if (count == maxSize) {
            //Space validation
            System.out.println("Error: Store is full! Cannot add more products.");
            return count;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product code: ");
        int entryCode = scanner.nextInt();
        for (int i = 0;i < count;i++) {
            if (entryCode == codes[i]) {
                System.out.println("Error: Product code already exists!");
                return count;
            }
        }

        scanner.nextLine();
        System.out.println("Enter product name: ");
        String entryName = scanner.nextLine();
        if (entryName.isEmpty()) {
            System.out.println("Error: Product name cannot be empty!");
            return count;
        }

        System.out.println("Enter product price: ");
        double entryPrice = scanner.nextDouble();
        if (entryPrice <= 0) {
            System.out.println("Error: Price must be greater than 0!");
            return count;
        }

        System.out.println("Enter product stock quantity");
        int entryStock = scanner.nextInt();
        if (entryStock < 0) {
            System.out.println("Error: Stock quantity cannot be negative!");
            return count;
        }
        codes[count] = entryCode;
        names[count] = entryName;
        prices[count] = entryPrice;
        stock[count] = entryStock;
        sold[count] = 0;
        System.out.println("✓ Product added successfully!");
        count++;
        return count;
    }

    static void displayAllProducts(int[] codes, String[] names, double[] prices, int[] stock, int[] sold, int count) {
        if(count == 0){
            System.out.println("No products in the system.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Product code: " + codes[i] + "\n" +
                    "Product name: " + names[i] + "\n" +
                    "Price: " + prices[i] + "\n" +
                    "Stock quantity: " + stock[i] + "\n" +
                    "Sold quantity: " + sold[i]);
        }
    }

    static void sellProduct(int[] codes, double[] prices, int[] stock, int[] sold, int count) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product code: ");
        int code = scanner.nextInt();
        int index = findProductIndex(codes, code, count);
        if (index == -1) {
            System.out.println("Error: Product not found!");
            return;
        }
        System.out.println("Enter product quantity: ");
        int quantity = scanner.nextInt();
        if (quantity > stock[index]) {
            System.out.println("Error: Only " + stock[index] + " units available in stock!");
            return;
        } else if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than 0!");
            return;
        }
        double total = prices[index] * quantity;
        stock[index] -= quantity;
        sold[index] += quantity;
        System.out.printf("Total price is: $%.2f%n", total);
        System.out.println("Remaining stock: " + stock[index]);
    }

    static void restockProduct(int[] codes, int[] stock, int[] sold, int count) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product code: ");
        int code = scanner.nextInt();
        int index = findProductIndex(codes, code, count);
        if (index == -1) {
            System.out.println("Error: Product not found!");
            return;
        }
        System.out.println("Enter product quantity: ");
        int quantity = scanner.nextInt();
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than 0!");
            return;
        }
        stock[index] += quantity;
        System.out.println("✓ Product restocked successfully! New stock: " + stock[index] + "units");
    }

    static void searchByCode(int[] codes, String[] names, double[] prices, int[] stock, int[] sold, int count) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product code: ");
        int code = scanner.nextInt();
        int index = findProductIndex(codes, code, count);
        if (index == -1) {
            System.out.println("Error: Product not found!");
            return;
        }
        double totalRevenue = prices[index] * sold[index];
        System.out.println("Product code: " + codes[index] + "\n" +
                "Product name: " + names[index] + "\n" +
                "Price: " + prices[index] + "\n" +
                "Stock quantity: " + stock[index] + "\n" +
                "Sold quantity: " + sold[index] + "\n" +
                "Total revenue: " + totalRevenue);
    }

    static void lowStockAlert(int[] codes, String[] names, double[] prices, int[] stock, int[] sold, int count) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (stock[i] < 5) {
                System.out.println("Product code: " + codes[i] + "\n" +
                        "Product name: " + names[i] + "\n" +
                        "Price: " + prices[i] + "\n" +
                        "Stock quantity: " + stock[i] + "\n" +
                        "Sold quantity: " + sold[i]);
                total++;
            }
        }
        if (total == 0) {
            System.out.println("✓ All products are well stocked!");
            return;
        }
        System.out.println("Total Low Stock Items: " + total);
    }

    static void totalInventoryValue(double[] prices, int[] stock, int count) {
        if (count == 0) {
            System.out.println("No products in the system.");
            return;
        }
        double totalInventoryValue = 0;
        for (int i = 0; i < count; i++) {
            totalInventoryValue += prices[i] * stock[i];
        }
        System.out.println("Total inventory revenue is: " + totalInventoryValue);
    }

    static void salesReport(double[] prices, int[] sold, int count) {
        int totalUnitsSold = 0;
        double totalRevenue = 0;
        for (int i = 0; i < count; i++) {
            totalUnitsSold += sold[i];
            totalRevenue += prices[i] * sold[i];
        }
        if (totalUnitsSold == 0) {
            System.out.println("No sales recorded yet.");
            return;
        }
        double averageSaleValue = totalRevenue / totalUnitsSold;
        System.out.println("Total units sold: " + totalUnitsSold + "\n" +
                "Total revenue: " + totalRevenue + "\n" +
                "Average sale value: " + averageSaleValue);
    }

    static void bestSellingProduct(int[] codes, String[] names, double[] prices, int[] stock, int[] sold, int count) {
        int[] idxs = {-1, -1, -1};
        for (int j = 0; j < idxs.length; j++) {
            int max = 0;
            int best = -1;
            for (int i = 0; i < count; i++) {
                boolean duplicate = false;
                for (int idx : idxs) {
                    if (i == idx) {
                        duplicate = true;
                        break;
                    }
                }
                if (duplicate) continue;
                if (sold[i] > max) {
                    max = sold[i];
                    best = i;
                }
            }
            idxs[j] = best;
        }

        if (idxs[0] == -1) {
            System.out.println("No sales recorded yet.");
            return;
        }
        for (int idx : idxs) {
            if (idx == -1) continue;
            System.out.println("Product code: " + codes[idx] + "\n" +
                    "Product name: " + names[idx] + "\n" +
                    "Price: " + prices[idx] + "\n" +
                    "Stock quantity: " + stock[idx] + "\n" +
                    "Sold quantity: " + sold[idx]);
        }
    }

    private static int findProductIndex(int[] codes, int code, int count) {
        for (int i = 0; i < count; i++) {
            if (code == codes[i]) {
                return i;
            }
        }
        return -1;
    }

}
