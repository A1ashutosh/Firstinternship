import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}

class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double calculateTotalExpenses() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public double calculateCategoryTotal(String category) {
        double total = 0.0;
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public void saveExpensesToFile() {
        try (PrintWriter writer = new PrintWriter("expenses.txt")) {
            for (Expense expense : expenses) {
                writer.println(expense.getDescription() + "," + expense.getAmount() + "," + expense.getCategory());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadExpensesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String description = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String category = parts[2];
                    addExpense(new Expense(description, amount, category));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ExpenseTrackerGUI {
    private JFrame frame;
    private ExpenseTracker expenseTracker;
    private JList<String> expenseList;
    private DefaultListModel<String> listModel;

    public ExpenseTrackerGUI() {
        expenseTracker = new ExpenseTracker();
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        expenseList = new JList<>(listModel);

        frame.add(new JScrollPane(expenseList), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        JMenuItem addExpenseItem = new JMenuItem("Add Expense");
        addExpenseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddExpenseDialog();
            }
        });
        menu.add(addExpenseItem);

        JMenuItem showSummaryItem = new JMenuItem("Show Summary");
        showSummaryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSummaryDialog();
            }
        });
        menu.add(showSummaryItem);

        JMenuItem clearExpensesItem = new JMenuItem("Clear All Expenses");
        clearExpensesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openClearExpensesDialog();
            }
        });
        menu.add(clearExpensesItem);

        JMenuItem saveExpensesItem = new JMenuItem("Save Expenses");
        saveExpensesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expenseTracker.saveExpensesToFile();
            }
        });
        menu.add(saveExpensesItem);

        loadExpensesFromFile();
        refreshExpenseList();

        frame.setVisible(true);
    }

    private void openAddExpenseDialog() {
        JFrame addExpenseFrame = new JFrame("Add Expense");
        addExpenseFrame.setSize(300, 200);

        JPanel panel = new JPanel();
        addExpenseFrame.add(panel);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(20);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(15);

        JButton addButton = new JButton("Add Expense");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryField.getText();

                Expense expense = new Expense(description, amount, category);
                expenseTracker.addExpense(expense);
                refreshExpenseList();

                addExpenseFrame.dispose();
            }
        });

        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(addButton);

        addExpenseFrame.setVisible(true);
    }

    private void openSummaryDialog() {
        JFrame summaryFrame = new JFrame("Expense Summaries");
        summaryFrame.setSize(300, 150);

        JPanel panel = new JPanel();
        summaryFrame.add(panel);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(15);

        JButton showSummaryButton = new JButton("Show Summary");

        showSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = categoryField.getText();

                double categoryTotal = expenseTracker.calculateCategoryTotal(category);

                JOptionPane.showMessageDialog(null, "Total expenses for " + category + ": Rs." + categoryTotal);
            }
        });

        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(showSummaryButton);

        summaryFrame.setVisible(true);
    }

    private void openClearExpensesDialog() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all expenses?",
                "Confirm Clear Expenses", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            expenseTracker.getExpenses().clear();
            refreshExpenseList();
        }
    }

    private void refreshExpenseList() {
        listModel.clear();
        for (Expense expense : expenseTracker.getExpenses()) {
            listModel.addElement(expense.getDescription());
        }
    }

    private void loadExpensesFromFile() {
        expenseTracker.loadExpensesFromFile();
        refreshExpenseList();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExpenseTrackerGUI();
            }
        });
    }
}
