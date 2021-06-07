package rating_problem;

import java.util.ArrayList;
import java.util.List;

public class RatingIntegers {
    private int sum, size;
    private List<Integer> ratings;

    public RatingIntegers() {
        ratings = new ArrayList<Integer>();
        size = 0;
        sum = 0;
    }

    public RatingIntegers(int firstRating) {
        ratings = new ArrayList<Integer>();
        ratings.add(firstRating);
        size = 1;
        sum = firstRating;
    }

    public void addRating(int r) {
        ratings.add(r);
        sum += r;
        size++;
    }

    public int getSum() {
        return sum;
    }

    public int getSize() {
        return size;
    }

    public List<Integer> getRatings() {
        return ratings;
    }
}
