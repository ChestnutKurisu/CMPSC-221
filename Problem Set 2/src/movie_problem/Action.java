package movie_problem;

public class Action extends Movie{

    public Action() {
        super();
    }

    public Action(MPAARRating rating, int ID, String title) {
        super(rating, ID, title);
    }

    @Override
    public double calculateLateFees(int daysLate) {
        return (3 * daysLate);
    }
}