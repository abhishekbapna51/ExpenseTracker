package main.java.com.expensetracker.service;

import main.java.com.expensetracker.model.Expense;
import main.java.com.expensetracker.model.Category;
import main.java.com.expensetracker.util.DateUtils;
import main.java.com.expensetracker.util.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseManager {
    private final List<Expense> expenses = new ArrayList<>();
    private final Path dataFile = Paths.get("ExpenseTracker","src","main","resources","data","expenses.csv");
    private final ReportService reportService = new ReportService();
    private final Scanner scanner = new Scanner(System.in);

    public ExpenseManager() {
        loadFromFile();
    }

    public void runCLI() {
        System.out.println("=== ExpenseTracker (Console) ===");
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add expense");
            System.out.println("2. List expenses");
            System.out.println("3. Filter by month");
            System.out.println("4. Category summary for month");
            System.out.println("5. Export monthly report (CSV)");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            String ch = scanner.nextLine();
            switch (ch) {
                case "1": addExpenseCLI(); break;
                case "2": listExpensesCLI(); break;
                case "3": filterByMonthCLI(); break;
                case "4": categorySummaryCLI(); break;
                case "5": exportReportCLI(); break;
                case "6": running = false; saveAll(); break;
                default: System.out.println("Invalid choice."); break;
            }
        }
        System.out.println("Goodbye.");
    }

    private void addExpenseCLI() {
        try {
            System.out.print("Amount (e.g. 250.50): ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Category (FOOD,TRAVEL,SHOPPING,BILLS,ENTERTAINMENT,HEALTH,OTHER): ");
            Category category = Category.fromString(scanner.nextLine().trim());
            System.out.print("Date (YYYY-MM-DD) [leave blank for today]: ");
            LocalDate date = DateUtils.parse(scanner.nextLine().trim());
            System.out.print("Description: ");
            String desc = scanner.nextLine().trim();
            Expense e = new Expense(amount, category, date, desc);
            expenses.add(e);
            FileUtils.appendLine(dataFile, e.toCSVLine());
            System.out.println("Added: " + e);
        } catch (Exception ex) {
            System.out.println("Failed to add expense: " + ex.getMessage());
        }
    }

    private void listExpensesCLI() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .forEach(e -> System.out.println(e.toString()));
    }

    private void filterByMonthCLI() {
        try {
            System.out.print("Enter month (YYYY-MM): ");
            String m = scanner.nextLine().trim();
            YearMonth ym = YearMonth.parse(m);
            List<Expense> filtered = filterByMonth(ym);
            if (filtered.isEmpty()) {
                System.out.println("No expenses for " + ym);
                return;
            }
            double total = filtered.stream().mapToDouble(Expense::getAmount).sum();
            System.out.println("Expenses for " + ym + " (total ₹" + String.format("%.2f", total) + "):");
            filtered.forEach(e -> System.out.println("  " + e));
        } catch (Exception ex) {
            System.out.println("Invalid month format.");
        }
    }

    private void categorySummaryCLI() {
        try {
            System.out.print("Enter month (YYYY-MM): ");
            YearMonth ym = YearMonth.parse(scanner.nextLine().trim());
            reportService.printCategorySummary(expenses, ym);
        } catch (Exception ex) {
            System.out.println("Invalid month.");
        }
    }

    private void exportReportCLI() {
        try {
            System.out.print("Enter month (YYYY-MM): ");
            YearMonth ym = YearMonth.parse(scanner.nextLine().trim());
            reportService.exportMonthlyCSV(expenses, ym);
        } catch (Exception ex) {
            System.out.println("Invalid month.");
        }
    }

    public List<Expense> filterByMonth(YearMonth ym) {
        return expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(ym))
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public void saveAll() {
        List<String> lines = new ArrayList<>();
        lines.add("ID,Amount,Category,Date,Description");
        for (Expense e : expenses) lines.add(e.toCSVLine());
        FileUtils.writeAllLines(dataFile, lines);
    }

    public void loadFromFile() {
        try {
            FileUtils.ensureFileExists(dataFile, "ID,Amount,Category,Date,Description\n");
            List<String> lines = FileUtils.readAllLines(dataFile);
            boolean first = true;
            for (String l : lines) {
                if (first) { first = false; continue; }
                Expense e = Expense.fromCSVLine(l);
                if (e != null) expenses.add(e);
            }
        } catch (Exception ex) {
            System.err.println("Failed to load data: " + ex.getMessage());
        }
    }
}
