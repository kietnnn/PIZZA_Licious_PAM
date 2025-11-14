package PIZZA_Licious.order;


public class GarlicKnot implements Price {
    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String getReceipt() {
        return "Garlic Knots - $1.50\n";
    }
}
