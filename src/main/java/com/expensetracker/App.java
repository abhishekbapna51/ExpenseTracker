package main.java.com.expensetracker;

import main.java.com.expensetracker.service.ExpenseManager;

public class App {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.runCLI();
    }
}
