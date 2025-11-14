package PIZZA_Licious.order;



import java.util.HashMap;
import java.util.Map;

public class Drink implements Price {
    private final String size;
    private final String flavor;
    private final String ice;

    private static final Map<String, Double> SIZE_PRICE = new HashMap<>();

    static {
        SIZE_PRICE.put("Small", 2.00);
        SIZE_PRICE.put("Medium", 2.50);
        SIZE_PRICE.put("Large", 3.00);
    }

    public Drink(String size, String flavor,String ice) {
        this.size = size;
        this.flavor = flavor;
        this.ice = ice;
    }

    @Override
    public double getPrice() {
        return SIZE_PRICE.getOrDefault(size, 0.00);
    }

    @Override
    public String getReceipt() {
        return size + " " + flavor + " Drink (" + ice + ") - $" +
                String.format("%.2f", getPrice());
    }
}

