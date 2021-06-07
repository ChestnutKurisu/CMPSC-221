import java.util.Scanner;

public class IntList {
    public static void main(String[] args) {
        //Assuming that no more than 50 elements will be inputted
        final int LENGTH = 50;
        final String SENTINEL = "E";
        Scanner in = new Scanner(System.in);
        int[] N = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            N[i] = 0;
        }
        int length = 0;
        String input = "";
        while (true) {
            System.out.print("Enter E to exit to output or enter element #" + (length + 1) + ": ");
            input = in.next();
            if (input.equalsIgnoreCase(SENTINEL))
                break;
            //We need to account for a non-integer input other than e or E.
            try {
                N[length] = Integer.parseInt(input);
            }
            catch(Exception e){
                System.err.println("Input is not E or an integer.");
                System.exit(1);
            }
            length++;
        }
        //Sort the array N in ascending order using Bubble Sort.
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (N[j] > N[j + 1]) {
                    int temp = N[j];
                    N[j] = N[j + 1];
                    N[j + 1] = temp;
                }
            }
        }
        System.out.println("N\t\tCount");
        boolean flag = true;
        int Count = 1; //Setting Count to 1 accounts for the current element.
        if (length == 1)
            System.out.println(N[0] + "\t\t" + 1);
        else if (length > 1) {
            for (int i = length - 1; i > 0; i--) {
                if (flag) {
                    System.out.print(N[i] + "\t\t");
                    flag = false;
                }
                if (N[i] == N[i - 1])
                    Count++;
                else {
                    System.out.println(Count);
                    Count = 1;
                    flag = true;
                }
            }
            if (N[0] != N[1]) //This is outside the for loop to avoid the ArrayIndexOutOfBounds exception.
                System.out.println(N[0] + "\t\t1");
            else
                System.out.println(Count); //Note that N[0] == N[1] case is already covered by the for loop and we just print that Count.
        }
    }
}
