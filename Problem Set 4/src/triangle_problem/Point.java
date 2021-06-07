package triangle_problem;

import java.awt.*;

public class Point {
    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point midpoint(Point A, Point B) {
        Point C = new Point((A.x + B.x) / 2, (A.y + B.y) / 2);
        return C;
    }

    private static void drawLine(Graphics g, Point A, Point B) {
        g.drawLine(A.x, A.y, B.x, B.y);
    }

    public static void drawTriangle(Graphics g, Point A, Point B, Point C) {
        drawLine(g, A, B);
        drawLine(g, B, C);
        drawLine(g, C, A);
    }
}