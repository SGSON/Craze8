package logic;

/**
 * The CustomException class is an exception class that allows methods
 * to throw exceptions with specific messages.
 */
public class CustomException extends Exception {
    private String errorMsg;

    public CustomException(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
