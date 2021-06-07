package text_editor_problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {

    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;
    private JTextArea write;

    public static void main(String[] args) {
        TextEditor te = new TextEditor();
        te.setVisible(true);
    }

    public TextEditor() {
        setTitle("Notepad");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        write = new JTextArea();
        write.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(write);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scroll);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New");
        item1.addActionListener(this);
        menu.add(item1);
        JMenuItem item2 = new JMenuItem("Open");
        item2.addActionListener(this);
        menu.add(item2);
        JMenuItem item3 = new JMenuItem("Save");
        item3.addActionListener(this);
        menu.add(item3);
        JMenuItem item4 = new JMenuItem("Exit");
        item4.addActionListener(this);
        menu.add(item4);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File f = null;
        //I learned about JFileChooser from: https://www.geeksforgeeks.org/java-swing-jfilechooser/
        JFileChooser choose = new JFileChooser();
        switch (e.getActionCommand()) {
            case "New":
                write.setText("");
                break;
            case "Open":
                int r = choose.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    f = choose.getSelectedFile();
                    try {
                        Scanner in = new Scanner(f);
                        String content = "";
                        while (in.hasNextLine()) {
                            content = content + in.nextLine() + "\n";
                        }
                        write.setText(content);
                        in.close();
                    } catch (FileNotFoundException ioex) {
                        System.err.println("File not found!");
                    }
                }
                break;
            case "Save":
                int s = choose.showSaveDialog(null);
                if (s == JFileChooser.APPROVE_OPTION) {
                    f = choose.getSelectedFile();
                    try {
                        PrintWriter out = new PrintWriter(f);
                        out.println(write.getText());
                        out.close();
                    } catch (FileNotFoundException ioex) {
                        System.err.println("File not found!");
                    }
                }
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
        }
    }
}
