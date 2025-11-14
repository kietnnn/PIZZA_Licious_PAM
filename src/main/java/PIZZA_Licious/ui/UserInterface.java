package PIZZA_Licious.ui;


import PIZZA_Licious.order.Drink;
import PIZZA_Licious.order.GarlicKnot;
import PIZZA_Licious.order.Order;
import PIZZA_Licious.order.Pizza;
import PIZZA_Licious.data.ReceiptWriter;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {


        while (true) {
            showWelcomeScreen();
            homeMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> newOrder();
                case "2" -> printMenuTable();
                case "0" -> {
                    System.out.println("👋 Exiting. Thank you!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void showWelcomeScreen() {
        System.out.println("    _____    ___    __  __     ");
        System.out.println("   | ___ \\  / _ \\  |  \\/  | ");
        System.out.println("   | |_/ / / /_\\ \\ | .  . |  ");
        System.out.println("   |  __/  |  _  | | |\\/| |   ");
        System.out.println("   | |     | | | | | |  | |    ");
        System.out.println("   \\_|     \\_| |_/ \\_|  |_/ ");
        System.out.println("\n=======================================");
        System.out.println("   Press Enter to continue");
        System.out.println("=======================================\n");
        scanner.nextLine();

        System.out.println("🍕 WELCOME TO PIZZA-LICIOUS 🍕");
        System.out.println("We're happy to serve you today.");


        scanner.nextLine();
        System.out.println("\nEnjoy your time with Pizza Automatic Machine!");
        System.out.println("To customize your Perfect Awesome Meals!");
        scanner.nextLine();

    }


    private void homeMenu() {
        System.out.println("\n Home Screen");
        System.out.println("1) New Order \uD83C\uDD95 ");
        System.out.println("2) Display Menu \uD83E\uDDFE");
        System.out.println("0) Exit ➜] ");
        System.out.print("Enter choice: ");
    }

    public static void printMenuTable() {
        System.out.println("===============================================================");
        System.out.println("                          PIZZA MENU                           ");
        System.out.println("===============================================================");
        System.out.printf("%-20s %-8s %-8s %-8s%n", "Item", "8\"", "12\"", "16\"");
        System.out.println("---------------------------------------------------------------");

        System.out.printf("%-20s %-8s %-8s %-8s%n", "Crust (all types)", "8.50", "12.00", "16.50");

        System.out.printf("%-20s %-8s %-8s %-8s%n", "Meats ", "1.00", "2.00", "3.00");

        System.out.printf("%-20s %-8s %-8s %-8s%n", "Extra Meat", "0.50", "1.00", "1.50");

        System.out.printf("%-20s %-8s %-8s %-8s%n", "Cheese", "0.75", "1.50", "2.25");
        System.out.printf("%-20s %-8s %-8s %-8s%n", "Extra Cheese", "0.30", "0.60", "0.90");

        System.out.printf("%-20s %-8s %-8s %-8s%n", "Regular Toppings", "Included", "Included", "Included");

        System.out.printf("%-20s %-8s %-8s %-8s%n", "Sauces", "Included", "Included", "Included");

        System.out.printf("%-20s %-8s %-8s %-8s%n", "Sides", "Included", "Included", "Included");

        System.out.println("---------------------------------------------------------------");

        // Other products
        System.out.printf("%-20s %-8s %-8s %-8s%n", "Drinks", "2.00", "2.50", "3.00");
        System.out.printf("%-20s %-8s %-8s %-8s%n", "Garlic Knots", "1.50", "1.50", "1.50");

        System.out.println("===============================================================");
    }

    private void newOrder() {
        System.out.print("\nEnter customer name: ");
        String name = scanner.nextLine();
        Order order = new Order(name);
        while (true) {
            orderMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> order.addItem(addPizza());
                case "2" -> order.addItem(addDrink());
                case "3" -> order.addItem(new GarlicKnot());
                case "4" -> {
                    checkout(order);
                }
                case "0" -> {
                    System.out.println("❌ Order canceled.");
                    return;
                }
                default -> System.out.println("❌ Invalid input.");
            }
        }

    }
    private void orderMenu(){
        System.out.println("\n \uD83C\uDD95 Order Menu");
        System.out.println("1) Add Pizza\uD83C\uDF55");
        System.out.println("2) Add Drink\uD83E\uDD64");
        System.out.println("3) Add Garlic Knots\uD83E\uDDC4(1.50)");
        System.out.println("4) Checkout\uD83D\uDCB0");
        System.out.println("0) Cancel Order❌");
        System.out.print("Enter choice: ");
    }


    private Pizza addPizza() {
        System.out.println("\n=== Build Your Pizza ===");
        String crust = selectOption("Choose crust type:",
                new String[]{"Thin", "Regular", "Thick", "Cauliflower","No Crust"});
        String size = selectOption("Choose pizza size:",
                new String[]{"8", "12", "16"});
        Pizza pizza = new Pizza(crust, size);

        addMultipleChoices("Select meat (multiple choice):",
                new String[]{"Pepperoni", "Sausage", "Ham", "Bacon", "Chicken", "Meatball"},
                item -> pizza.addMeat(item, false));

        addMultipleChoices("Select cheese (multiple choice):",
                new String[]{"Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"},
                item -> pizza.addCheese(item, false));

        addMultipleChoices("Select regular toppings (multiple choice):",
                new String[]{"Onions","Mushrooms","Bell Peppers","Olives","Tomatoes",
                        "Spinach","Basil","Pineapple","Anchovies"},
                pizza::addRegularTopping);

        addMultipleChoices("Select sauces (multiple choice):",
                new String[]{"Marinara","Alfredo","Pesto","BBQ","Buffalo","Olive Oil"}, pizza::addSauce);

        addMultipleChoices("Select sides (multiple choice):",
                new String[]{"red pepper","parmesan"}, pizza::addSide);



        System.out.print("Do you want to extra meats? ");
        System.out.println("\nPress (yes) to extra / press ENTER to skip ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("How many extra meats you want? ");
            int extra = readInt();
            for (int i = 0; i < extra; i++) pizza.addMeat("Extra Meat", true);
        }

        System.out.print("Do you want to extra cheese ? ");
        System.out.println("\n Press (yes) to extra / press ENTER to skip ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("How many extra cheese you want? ");
            int extra = readInt();
            for (int i = 0; i < extra; i++) pizza.addCheese("Extra Cheese", true);
        }

        System.out.println("\n✅ Pizza Added:\n" + pizza.getReceipt());
        return pizza;
    }

    private String selectOption(String prompt, String[] options) {
        while (true) {
            System.out.println("\n" + prompt);
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Enter choice: ");
            int choice = readInt();
            if (choice >= 1 && choice <= options.length) return options[choice - 1];
            System.out.println("❌ Invalid selection. Try again.");
        }
    }

    private void addMultipleChoices(String prompt, String[] options, java.util.function.Consumer<String> consumer) {
        System.out.println("\n" + prompt);
        for (int i = 0; i < options.length; i++) System.out.println((i + 1) + ") " + options[i]);
        System.out.println("Enter numbers separated by commas/ press ENTER to skip ");
        String input = scanner.nextLine();
        if (input.isBlank()) return;
        for (String num : input.split(",")) {
            try {
                int index = Integer.parseInt(num.trim()) - 1;
                if (index >= 0 && index < options.length) consumer.accept(options[index]);
            } catch (NumberFormatException ignored) {}
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }


    private Drink addDrink() {
        System.out.println("\n=== Select a Drink ===");
        String flavor = selectOption("Choose flavor:", new String[]
                {"Coke", "Pepsi", "Sprite", "Fanta", "Root Beer"});
        String size = selectOption("Choose size:", new String[]
                {"Small","Medium","Large"});
        String ice = selectOption("Choose ice level:", new String[]{
                "No ice","Light ice", "Half ice", "Full ice"});
        Drink drink = new Drink(size, flavor,ice);
        System.out.println("✅ Drink Added: " + drink.getReceipt());
        return drink;
    }


    private void checkout(Order order) {
        while (true) {
            System.out.println("\n===== Checkout =====");
            System.out.println(order.getOrder());
            System.out.println("1) Confirm Order");
            System.out.println("2) Return to Order Menu");
            System.out.println("3) Cancel Order");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    String timestamp = java.time.LocalDateTime.now()
                            .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
                    String filePath = "src/main/" + timestamp + ".txt";

                    ReceiptWriter writer = new ReceiptWriter(filePath);
                    writer.writeReceipt(order.getOrder());

                    System.out.println("✅ Order saved. Returning to home screen.");
                    return;
                case "2":
                    System.out.println("↩️ Returning to order menu...");
                    returnOrderMenu(order);
                    return;
                case "3":
                    System.out.println("❌ Order canceled. Returning to home screen.");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void returnOrderMenu(Order order) {
        while (true) {
            System.out.println("\n🧾 Order Menu");
            System.out.println("1) Add Pizza\uD83C\uDF55");
            System.out.println("2) Add Drink\uD83E\uDD64");
            System.out.println("3) Add Garlic Knots\uD83E\uDDC4(1.50)");
            System.out.println("4) Checkout\uD83D\uDCB0");
            System.out.println("0) Cancel Order❌");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    order.addItem(addPizza());
                    break;
                case "2":
                    order.addItem(addDrink());
                    break;
                case "3":
                    order.addItem(new GarlicKnot());
                    break;
                case "4":
                    checkout(order);
                    return;
                case "0":
                    System.out.println("❌ Order canceled. Returning to home screen.");
                    return;
                default:
                    System.out.println("❌ Invalid input. Try again.");
            }
        }
    }
}
