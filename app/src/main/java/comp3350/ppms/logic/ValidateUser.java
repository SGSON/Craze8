package comp3350.ppms.logic;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.PasswordError;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.UsernameError;
import comp3350.ppms.presentation.allusers.ErrorMessaging;

public class ValidateUser {

    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MIN_PASS_LENGTH = 5;
    private static final int MAX_PASS_LENGTH = 20;


    public static boolean validateAll(User user) throws CustomException {

        validateName(user);
        validateDuplicate(user);
        validatePassword(user);

        return true;
    }

    public static boolean validateName(User user) throws CustomException {
        if (user.getUserNickName().length()< MIN_NAME_LENGTH){
            throw new UsernameError(ErrorMessaging.MIN_NAME_PT1 + MIN_NAME_LENGTH + ErrorMessaging.MIN_NAME_PT2);
        } else if (user.getUserNickName() == null || user.getUserNickName() == ""){

            throw new UsernameError(ErrorMessaging.EMPTY_NAME_ERROR);

        } else if (user.getUserNickName().length() > MAX_NAME_LENGTH) {

            throw new UsernameError(ErrorMessaging.MAX_NAME_PT1 + MAX_NAME_LENGTH + ErrorMessaging.MAX_NAME_PT2);

        } else {

            return true;
        }
    }

    public static boolean validatePassword(User user) throws CustomException {
        if (user.getUserPassword().length() < MIN_PASS_LENGTH) {
            throw new PasswordError(ErrorMessaging.MIN_PASSWORD_PT1 + MIN_PASS_LENGTH + ErrorMessaging.MIN_PASSWORD_PT2);
        } else if (user.getUserPassword().length() > MAX_PASS_LENGTH) {
            throw new PasswordError(ErrorMessaging.MAX_PASSWORD_PT1 + MAX_PASS_LENGTH + ErrorMessaging.MAX_PASSWORD_PT2);
        } else {
            return true;
        }
    }

    public static boolean validateDuplicate(User user) throws CustomException {
        UserManager usersAccess = new UserManager();
        if (usersAccess.getUser(user.getUserNickName()) != null) {
            throw new UsernameError(ErrorMessaging.EXISTING_USERNAME_ERROR);
        }
        else{
            return true;
        }
    }

    public static boolean valideUser(User user) throws CustomException{
        if (user == null){
            throw new UsernameError("Invalid Account Name");
        } else{
            return true;
        }
    }

    public static boolean validUserLogin(User user) throws CustomException{
        if (user == null){
            throw new UsernameError(ErrorMessaging.INVALID_ACCOUNT_NAME);
        } else{
            return true;
        }
    }

    public static boolean validateUserSignUp(User user) throws CustomException{
        if (user != null){
            throw new UsernameError(ErrorMessaging.INVALID_ACCOUNT_NAME);
        } else{
            return true;
        }
    }



}
