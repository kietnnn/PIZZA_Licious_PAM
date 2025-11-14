package PIZZA_Licious.order;



import java.util.HashMap;
import java.util.Map;

public class Drink implements Price {
    private final String size; // Small, Medium, Large
    private final String flavor;
    private final String ice;

    private static final Map<String, Double> sizePrice = new HashMap<>();

    static {
        sizePrice.put("Small", 2.00);
        sizePrice.put("Medium", 2.50);
        sizePrice.put("Large", 3.00);
    }

    public Drink(String size, String flavor,String ice) {
        this.size = size;
        this.flavor = flavor;
        this.ice = ice;
    }

    @Override
    public double getPrice() {
        return sizePrice.getOrDefault(size, 0.00);
    }

    @Override
    public String getReceipt() {
        return size + " " + flavor + " Drink (" + ice + ") - $" +
                String.format("%.2f", getPrice());
    }
}

