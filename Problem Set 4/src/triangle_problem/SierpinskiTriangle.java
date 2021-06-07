package triangle_problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiTriangle extends JFrame implements ActionListener {
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;
    private static final int DIGIT_LIMIT = 9;
    private JTextField orderText;
    private JButton draw;

    public static void main(String[] args) {
        SierpinskiTriangle st = new SierpinskiTriangle();
        st.setVisible(true);
    }

    public SierpinskiTriangle() {
        setSize(WIDTH, HEIGHT);
        setTitle("Sierpinski Triangle Fractal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JPanel text = new JPanel();
        text.setLayout(new FlowLayout());
        JLabel enter = new JLabel("Enter an order: ");
        text.add(enter);
        orderText = new JTextField(DIGIT_LIMIT);
        text.add(orderText);
        draw = new JButton("Draw");
        draw.addActionListener(this);
        text.add(draw);
        add(text, BorderLayout.SOUTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Forming the initial equilateral triangle
        int sidelength = (int) Math.sqrt(Math.pow(0.9 * WIDTH / 2, 2) + Math.pow(0.9 * HEIGHT / 2, 2));
        Point A = new Point(WIDTH / 2, 100);
        Point B = new Point((int) (A.x - sidelength * Math.cos(Math.PI / 3)), (int) (A.y + sidelength * Math.sin(Math.PI / 3)));
        Point C = new Point((int) (A.x + sidelength * Math.cos(Math.PI / 3)), (int) (A.y + sidelength * Math.sin(Math.PI / 3)));
        Point.drawTriangle(g, A, B, C);
        String text = orderText.getText();
        int order = 0;
        try {
            if (text.equals(""))
                order = 0;
            else
                order = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            (new ErrorMessage("The order of the fractal must be an integer.")).setVisible(true);
        }
        if (order >= 0 && order < 13) //Higher orders can cause issues
            paintFractal(g, A, B, C, 0, order);
        else
            (new ErrorMessage("Negative orders or orders greater than 12 are not supported.")).setVisible(true);
    }

    private void paintFractal(Graphics g, Point A, Point B, Point C, int currentOrder, int order) {
        if (order == currentOrder)
            return;
        Point ABmid = Point.midpoint(A, B);
        Point BCmid = Point.midpoint(B, C);
        Point CAmid = Point.midpoint(C, A);
        Point.drawTriangle(g, A, ABmid, CAmid);
        Point.drawTriangle(g, B, BCmid, ABmid);
        Point.drawTriangle(g, C, CAmid, BCmid);
        //Every larger triangle gets broken down into four smaller triangles
        paintFractal(g, A, ABmid, CAmid, currentOrder + 1, order);
        paintFractal(g, B, BCmid, ABmid, currentOrder + 1, order);
        paintFractal(g, C, CAmid, BCmid, currentOrder + 1, order);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Draw":
                repaint();
                break;
            default:
        }
    }

    public static class ErrorMessage extends JFrame {
        public ErrorMessage(String message) {
            setTitle("Invalid Number");
            JLabel divLabel = new JLabel(message);
            setSize(500, 150);
            setLayout(new BorderLayout(10, 100));
            add(new JLabel(), BorderLayout.WEST);
            add(divLabel, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
}
