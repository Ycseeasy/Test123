package myapp.exception;

public class CompanyDeleteException extends RuntimeException {
    public CompanyDeleteException(String message) {
        super(message);
    }
}
