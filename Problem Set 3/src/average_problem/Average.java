package average_problem;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        int N = 0, input = 0;
        double sum = 0.0;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        N = in.nextInt();
        for (int i = 0; i < N; i++) {
            System.out.printf("Enter integer #%d: ", (i + 1));
            try {
                input = in.nextInt();
                if (input <= 0) {
                    throw new NonPositiveIntegerException("N must be positive.");
                }
            } catch (NonPositiveIntegerException e) {
                System.err.println("Error: " + e.getMessage());
                i--;
                continue;
            }
            sum += input;
        }
        if (N > 0)
            System.out.printf("The average of %d entered number%s is %s\n", N, (N > 1) ? "s" : "", sum / N);
        else
            System.out.println("You did not enter any numbers.");
    }
}
