import java.util.Scanner;

public class Airplane {
    private static boolean seats[][] = new boolean[7][4];

    public static void main(String[] args) {
        //Initializing the seat array to have all seats set to vacant
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                seats[i][j] = false;
            }
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the seat numbers which are occupied.");
        while (true) {
            int row = 0, col = 0;
            System.out.print("Enter row number (1 - 7): ");
            row = (in.nextInt() - 1);
            System.out.print("Enter column letter (A - D): ");
            col = ((int) Character.toUpperCase((in.next().charAt(0))) - 65);
            if (seats[row][col]) {
                System.out.println("The seat that you are trying to assign is already occupied.");
            } else
                seats[row][col] = true;
            //Printing the seats grid
            System.out.println("Row A\tB\tC\tD");
            for (int i = 0; i < seats.length; i++) {
                System.out.print((i + 1) + "\t");
                for (int j = 0; j < seats[0].length; j++) {
                    if (seats[i][j])
                        System.out.print("X\t");
                    else
                        System.out.print("\t");
                }
                System.out.println();
            }
            System.out.println("Enter 1 to continue or 0 to exit the program");
            int choice = in.nextInt();
            if (choice == 0)
                break;
            System.out.println();
        }
    }
}
