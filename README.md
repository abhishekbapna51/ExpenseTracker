# ExpenseTracker - Java Console Application

A simple and lightweight **expense management system** built using **Core Java**.  
This project records expenses, categorizes them, filters by month, and exports monthly reports as CSV files.

---

## Features

### Add Expenses
- Amount  
- Category (Food, Travel, Shopping, Bills, Entertainment, Health, Other)  
- Date  
- Description  

### View All Expenses
Sorted by date (latest first).

### Filter by Month
- Enter month in `YYYY-MM`
- Shows expenses + total monthly spending

### Category-wise Summary
- Auto-calculates spending by category for selected month

### Export Monthly CSV Report
Exports to: .csv file

---

## How to Run (VS Code Recommended)
### **Step 1: Clone the Project**

### **Step 2: Open VS Code Terminal**

### **Step 3: Run**
``` bash
git clone https://github.com/abhishekbapna51/ExpenseTracker.git
```
### **Step 4: Open the project**
File → Open Folder → select ExpenseTracker/

### **Step 5: Install Required Extensions**
- Extension Pack for Java  
- Debugger for Java  

### **Step 6: Run the App**
Open:
src/main/java/com/expensetracker/App.java


Click **Run ▶** or press **F5**.

---

## Compile & Run from Terminal (Manual)

### **Compile**
```bash
javac -d out $(find src/main/java -name "*.java")
```
### **Run**
```bash
java -cp out com.expensetracker.App
```

