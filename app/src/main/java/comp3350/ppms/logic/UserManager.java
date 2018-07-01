package comp3350.ppms.logic;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;

public class UserManager implements UserManagerInterface{
    private UserDatabaseInterface userStub;

    public UserManager(){
        userStub = Service.getUserDatabaseInterface();
    }

// TODO: write validate data and throws CustomException
    public void insertUser(User user) {
        userStub.insertUser((user));
    }

    public User getUserByString(String userNickname){
        return userStub.getUserByString(userNickname);
    }

}
