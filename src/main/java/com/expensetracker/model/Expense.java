package main.java.com.expensetracker.model;

import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private String id;
    private double amount;
    private Category category;
    private LocalDate date;
    private String description;

    public Expense() {
        this.id = UUID.randomUUID().toString();
    }

    public Expense(double amount, Category category, LocalDate date, String description) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description == null ? "" : description;
    }

    public Expense(String id, double amount, Category category, LocalDate date, String description) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description == null ? "" : description;
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String toCSVLine() {
        return String.join(",",
            id,
            String.valueOf(amount),
            category.name(),
            date.toString(),
            description.replace(",", " "));
    }

    public static Expense fromCSVLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(",", 5);
        if (parts.length < 5) return null;
        try {
            String id = parts[0];
            double amount = Double.parseDouble(parts[1]);
            Category category = Category.valueOf(parts[2]);
            java.time.LocalDate date = java.time.LocalDate.parse(parts[3]);
            String desc = parts[4];
            return new Expense(id, amount, category, date, desc);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Rs.%.2f | %s | %s",
            id.substring(0, Math.min(8, id.length())),
            category.name(),
            amount,
            date.toString(),
            description);
    }
}
