package PIZZA_Licious.userinterface;


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
            System.out.println("\n🏠 Home Screen");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> newOrder();
                case "0" -> {
                    System.out.println("👋 Exiting. Thank you!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void newOrder() {
        System.out.print("\nEnter customer name: ");
        String name = scanner.nextLine();
        Order order = new Order(name);
        while (true) {
            System.out.println("\n🧾 Order Menu");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> order.addItem(addPizza());
                case "2" -> order.addItem(addDrink());
                case "3" -> order.addItem(new GarlicKnot());
                case "4" -> {
                    checkout(order);
                    return;
                }
                case "0" -> {
                    System.out.println("❌ Order canceled.");
                    return;
                }
                default -> System.out.println("❌ Invalid input.");
            }
        }
    }


    private Pizza addPizza() {
        System.out.println("\n=== Build Your Pizza ===");
        String crust = selectOption("Choose crust type:",
                new String[]{"Thin", "Regular", "Thick", "Cauliflower","No Crust"});
        String size = selectOption("Choose pizza size:",
                new String[]{"8", "12", "16"});
        Pizza pizza = new Pizza(crust, size);

        addMultipleChoices("Select meat toppings:",
                new String[]{"Pepperoni", "Sausage", "Ham", "Bacon", "Chicken", "Meatball"},
                item -> pizza.addMeat(item, false));

        addMultipleChoices("Select cheese toppings:",
                new String[]{"Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"},
                item -> pizza.addCheese(item, false));

        addMultipleChoices("Select regular toppings:",
                new String[]{"Onions","Mushrooms","Bell Peppers","Olives","Tomatoes","Spinach","Basil","Pineapple","Anchovies"},
                pizza::addRegularTopping);

        addMultipleChoices("Select sauces:",
                new String[]{"Marinara","Alfredo","Pesto","BBQ","Buffalo","Olive Oil"}, pizza::addSauce);

        addMultipleChoices("Select sides:",
                new String[]{"red pepper","parmesan"}, pizza::addSide);



        System.out.print("Do you want to extra meats? ");
        System.out.println("\nPress (yes) to extra / press any button to skip ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("How many extra meats you want? ");
            int extra = readInt();
            for (int i = 0; i < extra; i++) pizza.addMeat("Extra Meat", true);
        }

        System.out.print("Do you want to extra cheese ? ");
        System.out.println("\n Press (yes) to extra / press any button to skip ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("How many extra cheese you want? ");
            int extra = readInt();
            for (int i = 0; i < extra; i++) pizza.addCheese("Extra Cheese", true);
        }

        System.out.println("\n✅ Pizza Added:\n" + pizza.getReceipt());
        return pizza;
    }




    private Drink addDrink() {
        System.out.println("\n=== Select a Drink ===");
        String flavor = selectOption("Choose flavor:", new String[]
                {"Coke", "Pepsi", "Sprite", "Fanta", "Root Beer"});
        String size = selectOption("Choose size:", new String[]
                {"Small", "Medium", "Large"});
        String ice = selectOption("Choose ice level:", new String[]{
                "No ice","Light ice", "Half ice", "Full ice"});
        Drink drink = new Drink(size, flavor,ice);
        System.out.println("✅ Drink Added: " + drink.getReceipt());
        return drink;
    }


    private void checkout(Order order) {
        while (true) {
            System.out.println("\n===== Checkout =====");
            System.out.println(order.getReceipt());
            System.out.println("1) Confirm Order");
            System.out.println("2) Return to Order Menu");
            System.out.println("3) Cancel Order");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": // yes
                    ReceiptWriter writer = new ReceiptWriter("src/main/java/PIZZA_Licious/data/OrderReceipt");
                    writer.writeReceipt(order.getReceipt());
                    System.out.println("✅ Order saved. Returning to home screen.");
                     // exit checkout
                case "2": // return
                    System.out.println("↩️ Returning to order menu...");
                    returnToOrderMenu(order); // a method to continue modifying the same order
                    break;
                case "3": // cancel
                    System.out.println("❌ Order canceled. Returning to home screen.");
                    return; // exit checkout, discard order
                default:
                    System.out.println("❌ Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void returnToOrderMenu(Order order) {
        while (true) {
            System.out.println("\n🧾 Order Menu");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
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
        System.out.println("Enter numbers separated by commas: ");
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
}
