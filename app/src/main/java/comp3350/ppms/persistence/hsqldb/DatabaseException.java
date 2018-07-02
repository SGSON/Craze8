package comp3350.ppms.persistence.hsqldb;

public class DatabaseException extends RuntimeException {
    public DatabaseException(final Exception cause) {
        super(cause);
    }
}
