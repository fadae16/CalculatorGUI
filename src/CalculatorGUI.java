import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CalculatorGUI extends JFrame implements ActionListener {
    private final JTextField display;
    private String input = "";
    private double result = 0;
    private String operator = "";

    public CalculatorGUI() {
        setTitle("Standard Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setText("0");
        display.setFont(new Font("Arial", Font.PLAIN, 40));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(300, 75));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        JButton[] buttons = new JButton[16];

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 25));
        }
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.matches("[0-9]")) {
            input += command;
            display.setText(input);
        } else if (command.equals("C")) {
            input = "";
            display.setText("0");
            result = 0;
            operator = "";
        } else if (command.matches("[+\\-*/]")) {
            if (!input.isEmpty()) {
                result = Double.parseDouble(input);
                input = "";
                operator = command;
            }
        } else if (command.equals("=")) {
            if (!input.isEmpty()) {
                double secondOperand = Double.parseDouble(input);
                switch (operator) {
                    case "+":
                        result += secondOperand;
                        break;
                    case "-":
                        result -= secondOperand;
                        break;
                    case "*":
                        result *= secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            result /= secondOperand;
                        } else {
                            display.setText("Error: Division by zero");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                input = String.valueOf(result);
                operator = "";
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}