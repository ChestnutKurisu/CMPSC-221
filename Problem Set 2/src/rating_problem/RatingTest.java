package rating_problem;

public class RatingTest {
    public static void main(String[] args) {
        System.out.println("File 1");
        Rating r1 = new Rating("src/rating_problem/MovieRatings.txt");
        r1.execute();
        System.out.println("\nFile 2");
        Rating r2 = new Rating("src/rating_problem/InvalidMovieRatings.txt");
        r2.execute();
    }
}
