package hard.budget;

public class Purchase implements Comparable<Purchase> {

    @Override
    public int compareTo(Purchase o) {
        if (this.getPrice() < o.getPrice()) {
            return 1;
        } else if (this.getPrice() == o.getPrice()) {
            return 0;
        }
        return -1;
    }

    public enum Categories {
        FOOD("Food"),
        ENTERTAINMENT("Entertainment"),
        CLOTHES("Clothes"),
        OTHER("Other");
        public final String description;
        Categories(String description) {
            this.description = description;
        }
    }

    private final String name;
    private final double price;
    private final Categories category;

    public Purchase(String name, double price, Categories category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Categories getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " $" + Main.format.format(price);
    }


}
