package comp3350.ppms.domain;

/**
 * The CustomException class is an exception class that allows methods
 * to throw exceptions with specific messages.
 */
public class CustomException extends Exception {
    private String mErrorMsg;

    //CUSTOMER PROJECT ERROR MESSAGES
    public static final String NO_PROJECT_ID_ERROR = "No Id for project";
    public static final String EXCEED_CHARACTER_MAX_ERROR = "Name exceed required amount of characters";
    public static final String EMPTY_NAME_ERROR = "Cannot have empty name";
    public static final String EXCEEDED_DESCRIPTION_ERROR = "Description exceed required amount of characters";
    public static final String EMPTY_DESCRIPTION_ERROR = "Cannot have empty description";
    public static final String PROJECT_CREDENTIALS_ERROR = "Projects must list credentials required";

    //CUSTOMER USER ERROR MESSAGES
    public static final String EXISTING_USERNAME_ERROR = "Username already exists, please choose another user name.";
    public static final String LIST_CREDENTIALS_ERROR  = "you must list your credentials";




    public CustomException(String errorMsg){
        this.mErrorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }
}