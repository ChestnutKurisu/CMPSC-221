package formatting_problem;

public class YearException extends RuntimeException {
    public YearException() {
        super("Invalid Year Entered.");
    }
}