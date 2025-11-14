package PIZZA_Licious.order;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 1;
    private final int orderId;
    private final String customer;
    private final List<Price> items = new ArrayList<>();

    public Order(String customer) {
        this.orderId = nextId++;
        this.customer = customer;
    }

    public void addItem(Price item) {
        items.add(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(Price::getPrice).sum();
    }

    public String getReceipt() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("===== PIZZA-Licious Receipt =====\n");
        stringBuilder.append("Order ID: ").append(orderId).append("\n");
        stringBuilder.append("Customer: ").append(customer).append("\n");
        stringBuilder.append("Date: ").append(LocalDateTime.now().withNano(0)).append("\n\n");

        for (Price item : items) {
            stringBuilder.append(item.getReceipt()).append("\n");
        }

        stringBuilder.append("\n------------------------------\n");
        stringBuilder.append("Total: $").append(String.format("%.2f", getTotal())).append("\n");
        stringBuilder.append("==============================\n");
        return stringBuilder.toString();
    }

}

