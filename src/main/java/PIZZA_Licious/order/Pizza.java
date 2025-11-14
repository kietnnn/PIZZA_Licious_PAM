package PIZZA_Licious.order;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pizza implements Price {
    private final String crustType;
    private final String size;
    private final List<String> meats;
    private final List<String> cheeses;
    private final List<String> regularToppings;
    private final List<String> sauces;
    private final List<String> sides;

    private int extraMeatCount;
    private int extraCheeseCount;

    private static final Map<String, Double> BASE_PRICE = new HashMap<>();
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

    public Pizza(String crustType, String size) {
        this.crustType = crustType;
        this.size = size;
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.extraMeatCount = 0;
        this.extraCheeseCount = 0;
    }

    public void addMeat(String meat, boolean isExtra) {
        meats.add(meat);
        if (isExtra) extraMeatCount++;
    }

    public void addCheese(String cheese, boolean isExtra) {
        cheeses.add(cheese);
        if (isExtra) extraCheeseCount++;
    }

    public void addRegularTopping(String topping) {
        regularToppings.add(topping);
    }

    public void addSauce(String sauce) {
        sauces.add(sauce);
    }

    public void addSide(String side) {
        sides.add(side);

    }

    @Override
    public double getPrice() {
        double base = BASE_PRICE.getOrDefault(size, 0.0);
        double meatCost = meats.size() * MEAT_PRICE.getOrDefault(size, 0.0);
        double cheeseCost = cheeses.size() * CHEESE_PRICE.getOrDefault(size, 0.0);
        double extraMeatCost = extraMeatCount * EXTRA_MEAT_PRICE.getOrDefault(size, 0.0);
        double extraCheeseCost = extraCheeseCount * EXTRA_CHEESE_PRICE.getOrDefault(size, 0.0);


        return base + meatCost + cheeseCost + extraMeatCost + extraCheeseCost;
    }



    @Override
    public String getReceipt() {
        return size + "” " + crustType + " Pizza" +
                "\n  Meats: " + meats +
                "\n  Cheeses: " + cheeses +
                "\n  Toppings: " + regularToppings +
                "\n  Sauces: " + sauces +
                "\n  Sides: " + sides +
                "\n  Extra meat: " + extraMeatCount +
                "\n  Extra cheese: " + extraCheeseCount +
                "\n  Price: $" + String.format("%.2f", getPrice());
    }
}
