package array_problem;

public class RecursiveArray {
    public static void main(String[] args) {
        String[][] data1 = {
                {"A", "B"},
                {"1", "2"},
                {"XX", "YY", "ZZ"}
        };
        permuteData(data1);
        System.out.println();
        String[][] data2 = {
                {"A"},
                {"1"},
                {"2"},
                {"XX", "YY"}
        };
        permuteData(data2);
        System.out.println();
        String[][] data3 = {
                {"I"},
                {"am", "was", "will be"},
                {"a", "the"},
                {"noob", "programmer", "student", "mathematician"}
        };
        permuteData(data3);
    }

    public static void permuteData(String[][] data) {
        int[] indexArray = new int[data.length];
        for (int i = 0; i < data.length; i++)
            indexArray[i] = 0;
        permuteDataHelper(data, 0, indexArray);
    }

    public static void permuteDataHelper(String[][] data, int i, int[] indexArray) {
        //End recursion if last combination has been printed
        if (indexArray[0] == data[0].length)
            return;
        //Print when recursion reaches last array element
        if (i == data.length - 1) {
            printCombination(data, indexArray, 0);
            System.out.println();
            indexArray[i]++;
            if (indexArray[i] == data[i].length) {
                indexArray[i] = 0;
                indexArray[i - 1]++;
                i--;
            }
            permuteDataHelper(data, i, indexArray);
        } else {
            if (indexArray[i] == data[i].length) {
                indexArray[i] = 0;
                indexArray[i - 1]++;
                //Need to check if indexArray[i-1] is valid
                permuteDataHelper(data, i - 1, indexArray);
            }
            else
                permuteDataHelper(data, i + 1, indexArray);
        }
    }

    public static void printCombination(String[][] data, int[] indexArray, int i) {
        if (i == data.length)
            return;
        System.out.print(data[i][indexArray[i]] + " ");
        printCombination(data, indexArray, i + 1);
    }
}
