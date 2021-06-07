package bar_chart_problem;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BarChart extends JFrame {
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;
    private double[] data;

    public static void main(String[] args) {
        //Test cases
        double[] data1 = {5, 7, 9, 10, 11, 13, 2, 8, 18};
        BarChart bc1 = new BarChart(data1);
        bc1.setVisible(true);
        double[] data2 = {100, 340, 200, 90, 470, 220, 105, 200, 120};
        BarChart bc2 = new BarChart(data2);
        bc2.setVisible(true);
        double[] data3 = {13500, 15656, 12809, 8235, 22134, 6728, 4501, 10000, 7854};
        BarChart bc3 = new BarChart(data3);
        bc3.setVisible(true);
        double[] data4 = {12345678, 87654321, 32452812, 6838284, 129572783, 12991022, 78281818, 99999999, 44444444};
        BarChart bc4 = new BarChart(data4);
        bc4.setVisible(true);
        double[] data5 = {12, 0, 9, 32, 26, 41, 18, 19, 35, 5, 7, 9, 21, 49, 30, 22, 16, 0, 9};
        BarChart bc5 = new BarChart(data5);
        bc5.setVisible(true);
        double[] data6 = {93, 61, 72, 79, 76, 67, 97, 31, 43, 78, 18, 64, 37, 81, 49, 32, 41, 74, 94, 73, 30,
                66, 35, 95, 71, 48, 84, 92, 11, 22, 23, 88, 20, 1, 47, 3, 38, 100, 77, 90, 45, 13, 2, 6, 14, 69, 40, 63, 58, 89};
        BarChart bc6 = new BarChart(data6);
        bc6.setVisible(true);
    }

    public BarChart(double[] data) {
        this.data = Arrays.copyOf(data, data.length);
        setSize(WIDTH, HEIGHT);
        setTitle("Bar Chart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Graphing the axes
        int x0 = (int) (WIDTH * 0.10), y0 = (int) (HEIGHT * 0.90);
        g.drawLine(x0, y0, (int) (WIDTH * 0.95), y0); //x axis
        g.drawLine(x0, y0, x0, (int) (HEIGHT * 0.12)); //y axis
        int deltaX = (int) (WIDTH * 0.8 / (data.length * 2)); //in pixels
        int deltaY = (int) (HEIGHT * 0.8 / 22); //in pixels
        double maxY = Arrays.stream(data).max().getAsDouble();

        //Finding scale for Y-axis
        int yScale = (int) (maxY / 20) + 1; //Two less than the max
        int factor = (int) Math.log10(yScale) - 1;
        if (factor > 0)
            yScale = (int) (yScale / (Math.pow(10, factor)) + 1) * (int) ((Math.pow(10, factor))); //in units

        //Drawing the bar chart
        for (int i = 0; i < 22; i++) {
            String etch = Integer.toString(yScale * i);
            g.drawLine(x0 - (int) (WIDTH * 0.01), y0 - (i * deltaY), x0 + (int) (WIDTH * 0.01), y0 - (i * deltaY)); //Etchings
            //I learned how to right align strings from: https://stackoverflow.com/questions/2168963/use-java-drawstring-to-achieve-the-following-text-alignment
            g.drawString(etch, (x0 - (int) (WIDTH * 0.02))- g.getFontMetrics().stringWidth(etch), y0 - (i * deltaY));
        }
        for (int i = 0; i < data.length; i++) {
            int c = (int) (data[i] * deltaY / yScale);
            g.setColor(Color.BLACK);
            int midpoint = (int) ((2 * (x0 + 2 * deltaX * (i + 1)) + deltaX) / 2.0);
            g.drawString(Integer.toString(i + 1), midpoint, (int) (HEIGHT * 0.95));
            g.drawString(Integer.toString((int) data[i]), (int) (WIDTH * 0.10) + 2 * deltaX * (i + 1), (int) (HEIGHT * 0.89) - c);
            g.setColor(randomColor());
            g.fillRect((int) (WIDTH * 0.10) + 2 * deltaX * (i + 1), (int) (HEIGHT * 0.90) - c, deltaX, c);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Scale on Y-Axis: 1 Division = " + yScale + " unit" + ((yScale == 1) ? "" : "s"), (int) (WIDTH * 0.4), (int) (HEIGHT * 0.1));
    }

    public static Color randomColor() {
        int R = (int) (Math.random() * 201) + 55; //Don't want light colors
        int G = (int) (Math.random() * 201) + 55;
        int B = (int) (Math.random() * 201) + 55;
        return (new Color(R, G, B));
    }
}
