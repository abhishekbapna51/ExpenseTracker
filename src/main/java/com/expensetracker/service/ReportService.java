package main.java.com.expensetracker.service;

import main.java.com.expensetracker.model.Expense;
import main.java.com.expensetracker.model.Category;
import main.java.com.expensetracker.util.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {
    private static final Path REPORTS_DIR = Paths.get("ExpenseTracker","src","main","resources","data","reports");

    public void printCategorySummary(List<Expense> expenses, YearMonth ym) {
        Map<Category, Double> map = new EnumMap<>(Category.class);
        for (Expense e : expenses) {
            if (YearMonth.from(e.getDate()).equals(ym)) {
                map.put(e.getCategory(), map.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
            }
        }
        System.out.println("Category summary for " + ym);
        for (Category c : Category.values()) {
            System.out.printf("  %s : Rs.%.2f%n", c.name(), map.getOrDefault(c, 0.0));
        }
        double total = map.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.printf("  Total : Rs.%.2f%n", total);
    }

    public void exportMonthlyCSV(List<Expense> expenses, YearMonth ym) {
        try {
            if (!java.nio.file.Files.exists(REPORTS_DIR)) java.nio.file.Files.createDirectories(REPORTS_DIR);
            String fileName = String.format("%s-report.csv", ym.toString());
            Path out = REPORTS_DIR.resolve(fileName);
            List<String> lines = new ArrayList<>();
            lines.add("ID,Amount,Category,Date,Description");
            List<Expense> filtered = expenses.stream()
                    .filter(e -> YearMonth.from(e.getDate()).equals(ym))
                    .collect(Collectors.toList());
            for (Expense e : filtered) lines.add(e.toCSVLine());
            FileUtils.writeAllLines(out, lines);
            System.out.println("Report exported to: " + out.toString());
        } catch (Exception ex) {
            System.err.println("Failed to export report: " + ex.getMessage());
        }
    }
}
