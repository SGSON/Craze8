package comp3350.ppms.logic;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;
import comp3350.ppms.persistence.database.UserDatabase;
import comp3350.ppms.persistence.hsqldb.DatabaseException;

public class UserManager implements UserManagerInterface{
    private UserDatabaseInterface userDB;

    public UserManager(){
        userDB = Service.getUserDatabaseInterface();
    }


// TODO: write validate data and throws CustomException
    public void insertUser(User user) throws CustomException {
        ValidateUser.validateAll(user);
        userDB.insertUser((user));
    }

    //accepts a String username and returns the User if the account has been created or returns null
    //if name doesn't exist.


    public User getUser (String userName) throws CustomException{
        return userDB.getUserByString(userName);
    }

    @Override
    public void addProjectToUserInterestedList(User user, String projectID) {
        user.addToLikedProjectIDList(projectID);
        userDB.updateUser(user);

    }

    public User signUp(User username) throws CustomException{
        ValidateUser.validateUserSignUp(username);
        return userDB.getUserByString(username.getUserNickName());
    }

    public User loginRequest(String userName) throws CustomException{
        User user = userDB.getUserByString(userName);
        ValidateUser.validUserLogin(user);
        return user;
    }

}
