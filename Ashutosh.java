import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ashutosh {
    JFrame frame = new JFrame("Temperature Converter");
    JLabel t1 = new JLabel("Temperature Converter", SwingConstants.CENTER);
    Checkbox ctof = new Checkbox("Celsius to Fahrenheit");
    Checkbox ftoc = new Checkbox("Fahrenheit to Celsius");
    JLabel inp = new JLabel("Enter the Temperature");
    JTextField numb = new JTextField(10);
    Choice c = new Choice();
    JButton bb = new JButton("Convert");

    JLabel resultLabel = new JLabel("Converted Temperature:");

    public Ashutosh() {
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set font size for the title label
        t1.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(t1);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        Font componentFont = new Font("Arial", Font.PLAIN, 18);
        ctof.setFont(componentFont);
        ftoc.setFont(componentFont);
        inp.setFont(componentFont);
        numb.setFont(componentFont);
        c.setFont(componentFont);
        bb.setFont(componentFont);

        centerPanel.add(ctof);
        centerPanel.add(ftoc);
        centerPanel.add(inp);
        centerPanel.add(numb);
        c.add("C");
        c.add("F");
        centerPanel.add(c);
        centerPanel.add(bb);
        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(resultLabel);

        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));

        frame.add(bottomPanel, BorderLayout.SOUTH);

        bb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        frame.setSize(400, 250);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ashutosh();
            }
        });
    }

    private void convertTemperature() {
        try {
            double input = Double.parseDouble(numb.getText());
            double result = 0;

            if (ctof.getState()) {
                result = (input * 9 / 5) + 32;
                resultLabel.setText("Converted Temperature: " + result + " °F");
            } else if (ftoc.getState()) {
                result = (input - 32) * 5 / 9;
                resultLabel.setText("Converted Temperature: " + result + " °C");
            }

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }
}
