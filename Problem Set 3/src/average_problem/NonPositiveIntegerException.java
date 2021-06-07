package average_problem;

public class NonPositiveIntegerException extends RuntimeException {
    public NonPositiveIntegerException(){
        super("Error: Encountered a non-positive integer.");
    }
    public NonPositiveIntegerException(String message){
        super(message);
    }
}
