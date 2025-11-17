 ================================ INTRODUCTION ==========================
   
- Welcome to the project "PizzaLicious.PAM,". 
- The PAM or "Pizza Automatic Machine" is a technology system that allows customers to create and customize their own pizza. 
- Everyone is welcome to view and use the project, and feel free to give some feedback if you aren't satisfied.
- I am open to receiving any positive feedback that helps me improve my invention.
========================================================================


================================ DEVELOPMENT ==========================
   
  
- The Dell laptop and some research tools are my assistance to help me finish my project in a week.
- I also appreciate the care and support from everyone around me, although I don't often communicate or chit-chat with them.
- The new knowledge that I learn in a few weeks, like OOC, drawing diagrams, or "Package", is an important factor in my project.
- My work gets heavily inspired by other works, like the list below:
-      https://gist.github.com/erics273/f16242ba2311c2d7f26eec34af5a3c51#user-stories-sugestions--deli-cious-capstone
-      https://github.com/kietnnn/BlackJack
-      https://github.com/craigmckeachie/Week6-Fall2025/blob/main/notes/inheritance-abstract-interfaces.md#-summary-table
-      https://github.com/craigmckeachie/Streams/blob/main/notes/packages-exercise.md

========================================================================


================================ THE DIAGRAM  ==========================

  -this is diagram sample for my project ( It can be wrong )
-    <img width="801" height="712" alt="PAM drawio" src="https://github.com/user-attachments/assets/ee136df0-ada7-45d9-a9f5-fa215ad459cc" />

- This diagram will be presented as a recap of what happens in my program.

  ========================================================================

  ================================ THE CODING (GENERAL)  ==========================

- I use price() at a InterFace that contain getPrice(), and getReceipt().
- class Pizza(), GarnicKnot(), Drinks() inplements class Price()
- Using UserInterface() is the most important factor when it contains all of the elements and makes it work follow a system.
- UserInterface() is created based on the menu format.
- ReceiptWriter() has a role to create an independent file, receive data from checkout orders, and save that data to the file.
 ========================================================================

================================ THE CODING (IN DETAIL)  ==========================

+For class pizza(), I used a HashMap to store the various prices for size, meat, cheese, and extra stuff. (The same with Drink() class).
 
  - ( Pizza example )
-      private static final Map<String, Double> BASE_PRICE = new HashMap<>();
       private static final Map<String, Double> MEAT_PRICE = new HashMap<>();
       private static final Map<String, Double> CHEESE_PRICE = new HashMap<>();
       private static final Map<String, Double> EXTRA_MEAT_PRICE = new HashMap<>();
       private static final Map<String, Double> EXTRA_CHEESE_PRICE = new HashMap<>();
       static {
          BASE_PRICE.put("8", 8.50);
          BASE_PRICE.put("12", 12.00);
          BASE_PRICE.put("16", 16.50);

          MEAT_PRICE.put("8", 1.00);
          MEAT_PRICE.put("12", 2.00);
          MEAT_PRICE.put("16", 3.00);

          CHEESE_PRICE.put("8", 0.75);
          CHEESE_PRICE.put("12", 1.50);
          CHEESE_PRICE.put("16", 2.25);

          EXTRA_MEAT_PRICE.put("8", 0.50);
          EXTRA_MEAT_PRICE.put("12", 1.00);
          EXTRA_MEAT_PRICE.put("16", 1.50);

          EXTRA_CHEESE_PRICE.put("8", 0.30);
          EXTRA_CHEESE_PRICE.put("12", 0.60);
          EXTRA_CHEESE_PRICE.put("16", 0.90);
       }
   
   - (drink example)
-     private static final Map<String, Double> SIZE_PRICE = new HashMap<>();

      static {
        SIZE_PRICE.put("Small", 2.00);
        SIZE_PRICE.put("Medium", 2.50);
        SIZE_PRICE.put("Large", 3.00);
       }

+ To collect all the prices from pizza, drink, and Knot, use the stream method in getTotal(), which is in the class Order.
  
-     public double getTotal() {
        return items.stream().mapToDouble(Price::getPrice).sum();
       }
+ getOrder () uses a StringBuilder to build a long string efficiently for the receipt. It is cleaner and easier to enhance the program.
  
 -     public String getOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("===== PIZZA-Licious Receipt =====\n");
        stringBuilder.append("Customer: ").append(customer).append("\n");
        stringBuilder.append("Date: ").append(LocalDateTime.now().withNano(0)).append("\n\n");

        for (Price item : items) {
            stringBuilder.append(item.getReceipt()).append("\n");
        }

        stringBuilder.append("\n------------------------------\n");
        stringBuilder.append("Total: $").append(String.format("%.2f", getTotal())).append("\n");
        stringBuilder.append("=================================\n");
        return stringBuilder.toString();
        }


+ selectOption() is used to make sure the customer can choose only one of them. Moreover, it will list all the List<String> options as a number to choose from.
  -     private String selectOption(String prompt, String[] options) {
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
+ addMultipleChoices() uses the same function as selectOption(), but customers can make multiple choices in the section ( and customers can skip it by pressing ENTER ).
  -     private void addMultipleChoices(String prompt, String[] options, java.util.function.Consumer<String> consumer) {
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
+ readInt() to make sure the customer doesn't type wrong.
  -     private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
         }
+ returnOrderMenu(Order order) is used to help the customer return to the menu to add more items while saving the previous choice of the customer.
  -     private void returnOrderMenu(Order order) {
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
+ checkout () use LocalDateTime.now() to save the current time the customer gives an order. Moreover, all of the menu choice is made with the switch method
  - (For example in checkOut())
   -     private void checkout(Order order) {
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
                    System.exit(0);
                    break;
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
+ createFolder() is necessary when making a file place to input the receipt in by writeReceipt().
  -      private void createFolder(String path) {
        File file = new File(path).getParentFile();
        if (!file.exists()) {
            file.mkdir();
        }
        }

         public void writeReceipt(String content) {
        try (BufferedWriter bufferedWriterwriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriterwriter.write(content);
        } catch (IOException e) {
            System.err.println("❌ Failed to write receipt: " + e.getMessage());
        }
        }

 

  
 
