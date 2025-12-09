# 📘 ExpenseTracker - Java Console Application

A simple and lightweight **expense management system** built using **Core Java**.  
This project records expenses, categorizes them, filters by month, and exports monthly reports as CSV files.

---

## 🚀 Features

### ✔ Add Expenses
- Amount  
- Category (Food, Travel, Shopping, Bills, Entertainment, Health, Other)  
- Date  
- Description  

### ✔ View All Expenses
Sorted by date (latest first).

### ✔ Filter by Month
- Enter month in `YYYY-MM`
- Shows expenses + total monthly spending

### ✔ Category-wise Summary
- Auto-calculates spending by category for selected month

### ✔ Export Monthly CSV Report
Exports to: .csv file



---

## 🖥️ How to Run (VS Code Recommended)

### **1️⃣ Open the project**
File → Open Folder → select ExpenseTracker/

### **2️⃣ Install Required Extensions**
- Extension Pack for Java  
- Debugger for Java  

### **3️⃣ Run the App**
Open:
src/main/java/com/expensetracker/App.java


Click **Run ▶** or press **F5**.

---

## 🏗️ Compile & Run from Terminal (Manual)

### **Compile**
```bash
javac -d out $(find src/main/java -name "*.java")
```
### **Run**
```bash
java -cp out com.expensetracker.App
```

