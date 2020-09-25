package hard.budget;

import java.text.DecimalFormat;

public class Purchase implements Comparable<Purchase> {

    private final String name;
    private final double price;
    private final Categories category;

    public Purchase(String name, double price, Categories category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters methods

    public Categories getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    // Override methods

    @Override
    public int compareTo(Purchase o) {
        if (this.price < o.getPrice()) {
            return 1;
        } else if (this.price == o.getPrice()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return name + " $" + new DecimalFormat("#0.00").format(price);
    }
}
