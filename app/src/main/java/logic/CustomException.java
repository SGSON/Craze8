package logic;

/**
 * The CustomException class is an exception class that allows methods
 * to throw exceptions with specific messages.
 */
public class CustomException extends Exception {
    private String mErrorMsg;

    public CustomException(String errorMsg){
        this.mErrorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }
}

