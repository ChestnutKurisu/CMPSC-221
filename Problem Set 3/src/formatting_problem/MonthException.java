package formatting_problem;

public class MonthException extends RuntimeException {
    public MonthException() {
        super("Invalid Month Entered.");
    }
}