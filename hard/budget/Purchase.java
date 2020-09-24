package hard.budget;

public class Purchase {

    public enum Categories {
        FOOD("Food"),
        CLOTHES("Clothes"),
        ENTERTAINMENT("Entertainment"),
        OTHER("Other"),
        ALL("All");
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

    @Override
    public String toString() {
        return name + " $" + price;
    }
}
