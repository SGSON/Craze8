package comp3350.ppms.logic;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;
import java.util.UUID;

public class UserManager implements UserManagerInterface{
    private UserDatabaseInterface userStub;

    public UserManager(){
        userStub = Service.getUserDatabaseInterface();
    }

// TODO: write validate data and throws CustomException
    public void insertUser(User user) throws CustomException {
        ValidateUser.validateAll(user);
        userStub.insertUser((user));
    }

    //accepts a String username and returns the User if the account has been created or returns null
    //if name doesn't exist.
    public User getUser (String userName){
        return userStub.getUserByString(userName);
    }

}
