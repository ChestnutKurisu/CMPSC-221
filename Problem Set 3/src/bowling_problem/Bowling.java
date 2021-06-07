package bowling_problem;

public class Bowling {
    public static void main(String[] args) {
        //Testing bowlingPinCount()
        System.out.println("Bowling pins in five rows: " + bowlingPinCount(5));
        System.out.println();
        System.out.println("Bowling pins in ten rows: " + bowlingPinCount(10));
        System.out.println();
        System.out.println("Bowling pins in twenty rows: " + bowlingPinCount(20));
    }

    public static int bowlingPinCount(int n) {
        return bowlingPinCountHelper(n, n);
    }

    public static int bowlingPinCountHelper(int n, int totalRows) {
        if (n == 0)
            return 0;
        int sumOfPins = n + bowlingPinCountHelper(n - 1, totalRows);
        printSpaces(totalRows - n);
        printPins(n);
        System.out.println();
        return (sumOfPins);
    }

    public static void printSpaces(int s) {
        if (s == 0)
            return;
        System.out.print(" ");
        printSpaces(s - 1);
    }

    public static void printPins(int p) {
        if (p == 0)
            return;
        System.out.print("* ");
        printPins(p - 1);
    }
}
