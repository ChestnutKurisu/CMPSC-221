package registration_problem;

import java.util.*;

public class Registration {
    public static void main(String[] args) {
        Map<Integer, ArrayList<String>> courseMap = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int ID = 0;
        System.out.println("Enter Student ID and Course Name or enter -1 to exit.");
        do {
            ID = in.nextInt();
            if(ID == -1)
                break;
            String course = in.next();
            if (courseMap.containsKey(ID)) {
                courseMap.get(ID).add(course);
            } else
                courseMap.put(ID, new ArrayList<>(Arrays.asList(course)));
        }while(true);
        //Sorting ID numbers in ascending order
        ArrayList<Integer> IDList = new ArrayList<>(courseMap.keySet());
        Collections.sort(IDList);
        for (int id : IDList) {
            System.out.printf("Student ID: %d, Courses Taken: %s\n", id, courseMap.get(id));

        }
    }
}
