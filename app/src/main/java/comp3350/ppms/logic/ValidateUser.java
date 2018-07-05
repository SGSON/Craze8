package comp3350.ppms.logic;

import comp3350.ppms.domain.User;

public class ValidateUser {

    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MIN_PASS_LENGTH = 5;
    private static final int MAX_PASS_LENGTH = 20;


    public static boolean validateAll(User user) throws CustomException{
        validateName(user);
        validatePassword(user);

        return true;
    }

    public static boolean validateName(User user) throws CustomException {
        UserManager usersAccess = new UserManager();
        if (user.getUserNickName().length()< MIN_NAME_LENGTH){
            throw new UsernameError("Name requires at least " + MIN_NAME_LENGTH + " characters.");
        } else if (user.getUserNickName() == null || user.getUserNickName() == ""){
            throw new UsernameError("Cannot have empty user name");
        } else if (user.getUserNickName().length() > MAX_NAME_LENGTH) {
            throw new UsernameError("Name cannot exceed " + MAX_NAME_LENGTH + " characters.");
        } else if (usersAccess.getUser(user.getUserNickName()) != null) {
            throw new UsernameError("Username already exists, please choose another user name.");
        } else {
            return true;
        }
    }

    public static boolean validatePassword(User user) throws CustomException {
        if (user.getUserPassword().length() < MIN_PASS_LENGTH) {
            throw new PasswordError("Password requires at least " + MIN_PASS_LENGTH + " characters.");
        } else if (user.getUserPassword().length() > MAX_PASS_LENGTH) {
            throw new PasswordError("Password cannot exceed " + MAX_PASS_LENGTH + " characters.");
        } else {
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
            throw new UsernameError("Invalid Account Name");
        } else{
            return true;
        }
    }

    public static boolean validateUserSignUp(User user) throws CustomException{
        if (user != null){
            throw new UsernameError("Invalid Account Name");
        } else{
            return true;
        }
    }


}
