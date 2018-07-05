package comp3350.ppms.logic;

import comp3350.ppms.domain.User;

public class ValidateUser {

    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MIN_PASS_LENGTH = 5;
    private static final int MAX_PASS_LENGTH = 20;
    private static final int CREDENTIAL_LENGTH = 2;

    public static boolean validateAll(User user) throws CustomException{
        validateName(user);
        validatePassword(user);
        validateCredentials(user);
        return true;
    }

    public static boolean validateName(User user) throws CustomException {
        UserManager usersAccess = new UserManager();
        if (user.getUserNickName().length()< MIN_NAME_LENGTH){
            throw new CustomException("Name requires at least " + MIN_NAME_LENGTH + " characters.");
        } else if (user.getUserNickName() == null || user.getUserNickName() == ""){
            throw new CustomException(CustomException.EMPTY_NAME_ERROR);
        } else if (user.getUserNickName().length() > MAX_NAME_LENGTH) {
            throw new CustomException("Name cannot exceed " + MAX_NAME_LENGTH + " characters.");
        } else if (usersAccess.getUser(user.getUserNickName()) != null) {
            throw new CustomException(CustomException.EXISTING_USERNAME_ERROR);
        } else {
            return true;
        }
    }

    public static boolean validatePassword(User user) throws CustomException {
        if (user.getUserPassword().length() < MIN_PASS_LENGTH) {
            throw new CustomException("Password requires at least " + MIN_PASS_LENGTH + " characters.");
        } else if (user.getUserPassword().length() > MAX_PASS_LENGTH) {
            throw new CustomException("Password cannot exceed " + MAX_PASS_LENGTH + " characters.");
        } else {
            return true;
        }
    }

    public static boolean validateCredentials(User user) throws CustomException {
        int i = 0;
        if (user.getUserCredentials().isEmpty()){
            throw new CustomException(CustomException.LIST_CREDENTIALS_ERROR);
        } else {
            while (i < user.getUserCredentials().size()){
                if (user.getUserCredentials().get(i).length() < CREDENTIAL_LENGTH){
                    throw new CustomException("Credentials must exceed " + CREDENTIAL_LENGTH + " characters.");
                }
                i++;
            }
            return true;
        }
    }
}
