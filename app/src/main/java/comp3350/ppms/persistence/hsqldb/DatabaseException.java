package comp3350.ppms.persistence.hsqldb;

/**
 *   wrapping java.sql.SQLException in an unchecked java.lang.RuntimeException
 */

public class DatabaseException extends RuntimeException {

    public DatabaseException(final Exception cause) {
        super(cause);
    }

}
