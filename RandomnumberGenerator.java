import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RandomnumberGenerator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JLabel promptLabel;
    private JLabel messageLabel;
    private int lowerBound = 1;
    private int upperBound = 100;
    private int numberToGuess;
    private int numberOfTries;

    public RandomnumberGenerator() {
        setTitle("Number Guessing Game");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        promptLabel = new JLabel("Enter your guess: ");
        inputField = new JTextField(10);
        inputField.addActionListener(this);

        messageLabel = new JLabel(
                "I've selected a random number between " + lowerBound + " and " + upperBound + ". Try to guess it!");

        JPanel panel = new JPanel();
        panel.add(promptLabel);
        panel.add(inputField);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(messageLabel, BorderLayout.CENTER);

        initGame();
    }

    public void initGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        numberOfTries = 0;
        messageLabel.setText(
                "I've selected a random number between " + lowerBound + " and " + upperBound + ". Try to guess it!");
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int userGuess = Integer.parseInt(inputField.getText());
            numberOfTries++;

            if (userGuess < numberToGuess) {
                messageLabel.setText("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("Congratulations! You've guessed the number in " + numberOfTries + " tries.");
                initGame();
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }

        inputField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RandomnumberGenerator game = new RandomnumberGenerator();
            game.setVisible(true);
        });
    }
}
