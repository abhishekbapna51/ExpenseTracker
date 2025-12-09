package main.java.com.expensetracker.model;

public enum Category {
    FOOD,
    TRAVEL,
    SHOPPING,
    BILLS,
    ENTERTAINMENT,
    HEALTH,
    OTHER;

    public static Category fromString(String s) {
        if (s == null) return OTHER;
        try {
            return Category.valueOf(s.toUpperCase());
        } catch (Exception e) {
            return OTHER;
        }
    }
}
