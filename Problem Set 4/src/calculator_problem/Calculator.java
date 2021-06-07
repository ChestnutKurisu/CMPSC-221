package calculator_problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator extends JFrame implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final int DIGIT_LIMIT = 18;
    private JTextField result;
    private JLabel operations;
    private ArrayList<Double> numberHistory = new ArrayList<>();
    private ArrayList<String> operationHistory = new ArrayList<>();

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.setVisible(true);
    }

    public static class DivisionFrame extends JFrame {
        public DivisionFrame(String message) {
            setTitle("Arithmetic Exception");
            JLabel divLabel = new JLabel(message);
            setSize(500, 150);
            setLayout(new BorderLayout(10, 10));
            add(new JLabel(), BorderLayout.WEST);
            add(divLabel, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    public Calculator() {
        setSize(WIDTH, HEIGHT);
        setTitle("Java Swing Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Calculator menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem close = new JMenuItem("Exit");
        close.addActionListener(this);
        file.add(close);
        JMenu help = new JMenu("Help");
        JMenuItem getHelp = new JMenuItem("Get Help");
        getHelp.addActionListener(this);
        help.add(getHelp);
        menuBar.add(file);
        menuBar.add(help);
        setJMenuBar(menuBar);

        //Text field and memory
        JPanel showResult = new JPanel();
        showResult.setLayout(new GridLayout(2, 1));
        JPanel memory = new JPanel();
        memory.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel history = new JLabel("Memory: ");
        memory.add(history);
        operations = new JLabel();
        memory.add(operations);
        showResult.add(memory);
        result = new JTextField("Result", DIGIT_LIMIT);
        result.setEditable(false);
        result.setText("0");
        result.setBackground(Color.WHITE);
        showResult.add(result);
        add(showResult, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        JPanel topButtons = new JPanel();
        topButtons.setLayout(new GridLayout(1, 3, 5, 5));
        JButton backspace = new JButton("Backspace");
        backspace.addActionListener(this);
        backspace.setForeground(Color.RED);
        topButtons.add(backspace);
        JButton clearElement = new JButton("CE");
        clearElement.addActionListener(this);
        clearElement.setForeground(Color.RED);
        topButtons.add(clearElement);
        JButton cancel = new JButton("C");
        cancel.addActionListener(this);
        cancel.setForeground(Color.RED);
        topButtons.add(cancel);
        buttonsPanel.add(topButtons, BorderLayout.NORTH);

        JPanel allButtons = new JPanel();
        allButtons.setLayout(new GridLayout(4, 5, 5, 5));
        JButton seven = new JButton("7");
        seven.addActionListener(this);
        seven.setForeground(Color.BLUE);
        allButtons.add(seven);
        JButton eight = new JButton("8");
        eight.addActionListener(this);
        eight.setForeground(Color.BLUE);
        allButtons.add(eight);
        JButton nine = new JButton("9");
        nine.addActionListener(this);
        nine.setForeground(Color.BLUE);
        allButtons.add(nine);
        JButton divide = new JButton("/");
        divide.addActionListener(this);
        divide.setForeground(Color.RED);
        allButtons.add(divide);
        JButton sqrt = new JButton("sqrt");
        sqrt.addActionListener(this);
        sqrt.setForeground(Color.RED);
        allButtons.add(sqrt);
        JButton four = new JButton("4");
        four.addActionListener(this);
        four.setForeground(Color.BLUE);
        allButtons.add(four);
        JButton five = new JButton("5");
        five.addActionListener(this);
        five.setForeground(Color.BLUE);
        allButtons.add(five);
        JButton six = new JButton("6");
        six.addActionListener(this);
        six.setForeground(Color.BLUE);
        allButtons.add(six);
        JButton multiply = new JButton("*");
        multiply.addActionListener(this);
        multiply.setForeground(Color.RED);
        allButtons.add(multiply);
        JButton divisional = new JButton("1/x");
        divisional.addActionListener(this);
        divisional.setForeground(Color.RED);
        allButtons.add(divisional);
        JButton one = new JButton("1");
        one.addActionListener(this);
        one.setForeground(Color.BLUE);
        allButtons.add(one);
        JButton two = new JButton("2");
        two.addActionListener(this);
        two.setForeground(Color.BLUE);
        allButtons.add(two);
        JButton three = new JButton("3");
        three.addActionListener(this);
        three.setForeground(Color.BLUE);
        allButtons.add(three);
        JButton subtract = new JButton("-");
        subtract.addActionListener(this);
        subtract.setForeground(Color.RED);
        allButtons.add(subtract);
        JButton percentage = new JButton("%");
        percentage.addActionListener(this);
        percentage.setForeground(Color.RED);
        allButtons.add(percentage);
        JButton zero = new JButton("0");
        zero.addActionListener(this);
        zero.setForeground(Color.BLUE);
        allButtons.add(zero);
        JButton plusMinus = new JButton("+/-");
        plusMinus.addActionListener(this);
        plusMinus.setForeground(Color.RED);
        allButtons.add(plusMinus);
        JButton dot = new JButton(".");
        dot.addActionListener(this);
        dot.setForeground(Color.RED);
        allButtons.add(dot);
        JButton plus = new JButton("+");
        plus.addActionListener(this);
        plus.setForeground(Color.RED);
        allButtons.add(plus);
        JButton equals = new JButton("=");
        equals.addActionListener(this);
        equals.setForeground(Color.RED);
        allButtons.add(equals);
        buttonsPanel.add(allButtons, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = result.getText(), operator = e.getActionCommand();
        double current = (text.charAt(text.length() - 1) == '.') ?
                Double.parseDouble(text.substring(0, text.length() - 1)) : Double.parseDouble(text);
        //To make "-0.0" look better
        if(current == 0)
            result.setText("0");
        switch (e.getActionCommand()) {
            case "Exit":
                System.exit(0);
                break;
            case "Get Help":
                JFrame help = new JFrame("How to use the program?");
                JLabel label1 = new JLabel("The program is self-explanatory.");
                help.setSize(500, 150);
                help.setLayout(new BorderLayout(10, 10));
                help.add(new JLabel(), BorderLayout.WEST);
                help.add(label1, BorderLayout.CENTER);
                help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                help.setVisible(true);
                break;
            case "Backspace":
                if (text.length() == 1)
                    result.setText("0");
                else
                    result.setText(text.substring(0, text.length() - 1));
                break;
            case "CE":
                result.setText("0");
                break;
            case "C":
                result.setText("0");
                operations.setText("");
                numberHistory.clear();
                operationHistory.clear();
                break;
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (!text.equals("0"))
                    result.setText(text + e.getActionCommand());
                else
                    result.setText(e.getActionCommand());
                break;
            case "sqrt":
                if (current >= 0)
                    result.setText(Double.toString(Math.sqrt(current)));
                else {
                    DivisionFrame df0 = new DivisionFrame("Complex/Imaginary numbers are not supported!");
                    df0.setVisible(true);
                }
                break;
            case "1/x":
                if (current != 0) {
                    current = 1 / current;
                    result.setText(Double.toString(current));
                } else {
                    DivisionFrame df1 = new DivisionFrame("Cannot divide by zero!");
                    df1.setVisible(true);
                }
                break;
            case "%":
                //Converts current to percentage, unlike the Windows 10 calculator.
                result.setText(Double.toString(current/100));
                break;
            case "+/-":
                if (current != 0) {
                    if (text.charAt(0) == '-')
                        result.setText(text.substring(1));
                    else
                        result.setText("-" + text);
                }
                break;
            case ".":
                if (current - (int) current == 0 && text.charAt(text.length() - 1) != '.')
                    result.setText(text + ".");
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                numberHistory.add(current);
                operationHistory.add(operator);
                operations.setText(operations.getText() + current + operator);
                result.setText("0");
                break;
            case "=":
                numberHistory.add(current);
                String value = compute();
                result.setText(value);
                numberHistory.clear();
                operationHistory.clear();
                operations.setText("");
                break;
            default:
        }
    }

    private String compute() {
        double value = numberHistory.get(0);
        int index = 1;
        for (String operation : operationHistory) {
            switch (operation) {
                case "+":
                    value += numberHistory.get(index);
                    break;
                case "-":
                    value -= numberHistory.get(index);
                    break;
                case "*":
                    value *= numberHistory.get(index);
                    break;
                case "/":
                    if (numberHistory.get(index) != 0)
                        value /= numberHistory.get(index);
                    else {
                        DivisionFrame div2 = new DivisionFrame("Cannot divide by zero!");
                        div2.setVisible(true);
                        return "0";
                    }
                    break;
                default:
            }
            index++;
        }
        return Double.toString(value);
    }
}
