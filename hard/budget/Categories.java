package hard.budget;

public enum Categories {
    FOOD("Food", 1),
    ENTERTAINMENT("Entertainment", 2),
    CLOTHES("Clothes", 3),
    OTHER("Other", 4);

    public final String description;
    public final int id;

    Categories(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public static Categories findById(int id) {
        for (Categories c : Categories.values()) {
            if (id == c.id) {
                return c;
            }
        }
        return null;
    }

    public static String showAll() {
        StringBuilder result = new StringBuilder();
        for (Categories c : Categories.values()) {
            result.append(c.id).append(") ").append(c.description).append("\n");
        }
        return result.toString();
    }
}
