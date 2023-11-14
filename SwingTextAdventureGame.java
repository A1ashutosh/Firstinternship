import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTextAdventureGame extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private int currentChoice = 0;

    public SwingTextAdventureGame() {
        setTitle("Mystical Forest Adventure");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea(15, 40);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        inputField = new JTextField(20);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        startGame();
    }

    private void startGame() {
        appendText("Welcome to the Mystical Forest Adventure!");
        appendText("You find yourself at the entrance of a mystical forest.");
        appendText("\nWhat do you want to do?");
        appendText("1. Enter the forest.");
        appendText("2. Turn back and leave.");
    }

    private void processInput() {
        String inputText = inputField.getText();
        int choice = 0;

        try {
            choice = Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            appendText("Invalid input. Please enter a number.");
            inputField.setText("");
            return;
        }

        if (currentChoice == 0) {
            if (choice == 1) {
                appendText("\nYou enter the mystical forest and encounter a crossroads.");
                appendText("What path will you take?");
                appendText("1. Follow the path to the left.");
                appendText("2. Take the path to the right.");
                currentChoice = 1;
            } else if (choice == 2) {
                appendText("You decide to leave. The adventure ends.");
                inputField.setEnabled(false);
            } else {
                appendText("Invalid choice. Please enter 1 or 2.");
            }
        } else if (currentChoice == 1) {
            if (choice == 1) {
                appendText("\nYou follow the path to the left and discover a hidden waterfall.");
                appendText("Do you want to approach the waterfall?");
                appendText("1. Approach the waterfall.");
                appendText("2. Continue on the path.");
                currentChoice = 2;
            } else if (choice == 2) {
                appendText("\nYou take the path to the right and encounter a rickety bridge.");
                appendText("What will you do?");
                appendText("1. Cross the bridge.");
                appendText("2. Find another route.");
                currentChoice = 3;
            } else {
                appendText("Invalid choice. Please enter 1 or 2.");
            }
        } else if (currentChoice == 2) {
            if (choice == 1) {
                appendText("\nAs you approach the waterfall, you find a hidden treasure!");
                appendText("Congratulations! You've completed the adventure!");
                inputField.setEnabled(false);
            } else if (choice == 2) {
                appendText("\nYou continue on the path and encounter a friendly forest creature.");
                appendText("It leads you to safety. Adventure complete!");
                inputField.setEnabled(false);
            } else {
                appendText("Invalid choice. Please enter 1 or 2.");
            }
        } else if (currentChoice == 3) {
            if (choice == 1) {
                appendText("\nYou cautiously cross the bridge and reach the other side safely.");
                appendText("Congratulations! You've completed the adventure!");
                inputField.setEnabled(false);
            } else if (choice == 2) {
                appendText("\nYou explore another route but get lost in the forest.");
                appendText("The adventure ends.");
                inputField.setEnabled(false);
            } else {
                appendText("Invalid choice. Please enter 1 or 2.");
            }
        }

        inputField.setText("");
    }

    private void appendText(String text) {
        textArea.append(text + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingTextAdventureGame().setVisible(true);
            }
        });
    }
}
