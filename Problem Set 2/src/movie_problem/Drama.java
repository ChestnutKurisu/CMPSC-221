package movie_problem;

public class Drama extends Movie{
    public Drama() {
        super();
    }

    public Drama(MPAARRating rating, int ID, String title) {
        super(rating, ID, title);
    }

    @Override
    public double calculateLateFees(int daysLate) {
        return (2 * daysLate);
    }
}
