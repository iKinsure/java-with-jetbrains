package medium.processor;

public enum Decision {
    ADD(1, "Add matrices"),
    SCALAR_MULTIPLY(2, "Multiply matrix by a constant"),
    MULTIPLY(3, "Multiply matrices"),
    TRANSPOSE(4, "Transpose matrix"),
    DETERMINANT(5, "Calculate a determinant"),
    INVERSE(6, "Inverse matrix"),
    EXIT(0, "Exit");

    private final int id;
    private final String description;

    Decision(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Decision findById(int id) {
        for (Decision decision : Decision.values()) {
            if (decision.id == id) {
                return decision;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + ". " + description;
    }
}

