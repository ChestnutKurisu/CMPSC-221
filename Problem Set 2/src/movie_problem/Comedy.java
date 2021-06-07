package movie_problem;

public class Comedy extends Movie{
    public Comedy() {
        super();
    }

    public Comedy(MPAARRating rating, int ID, String title) {
        super(rating, ID, title);
    }

    @Override
    public double calculateLateFees(int daysLate) {
        return (2.50 * daysLate);
    }
}