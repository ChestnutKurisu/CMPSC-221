package rating_problem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Rating {
    private String filePath;

    public Rating(String filePath) {
        this.filePath = filePath;
    }

    public void execute() {
        Map<String, RatingIntegers> ratingsMap = new HashMap<>();
        try {
            FileInputStream in = new FileInputStream(filePath);
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            int length = Integer.parseInt(read.readLine());
            for (int i = 0; i < length; i++) {
                String movieTitle = read.readLine();
                int rating = Integer.parseInt(read.readLine());
                if (rating < 1 || rating > 5) {
                    System.err.println("Error: " + rating + " is an invalid rating.");
                    System.exit(1);
                }
                if (ratingsMap.containsKey(movieTitle)) {
                    ratingsMap.get(movieTitle).addRating(rating);
                } else
                    ratingsMap.put(movieTitle, new RatingIntegers(rating));
            }
        } catch (IOException ioex) {
            System.err.println("File not found exception: " + ioex.getMessage());
        }
        for (String key : ratingsMap.keySet()) {
            RatingIntegers ratings = ratingsMap.get(key);
            double average = (double) ratings.getSum() / ratings.getSize();
            int numberOfRatings = ratings.getSize();
            System.out.printf("%s: %d review%s, average of %s / 5\n", key, numberOfRatings, ((numberOfRatings>1)?"s":""), average);
        }
    }
}
