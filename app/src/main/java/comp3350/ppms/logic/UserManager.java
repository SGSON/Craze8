package comp3350.ppms.logic;

import java.util.List;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;

public class UserManager implements UserManagerInterface{
    private UserDatabaseInterface userDB;

    public UserManager(){
        userDB = Service.getUserDatabaseInterface();
    }


    public void insertUser(User user) throws CustomException {
        ValidateUser.validateAll(user);
        userDB.insertUser((user));
    }

    //accepts a String username and returns the User if the account has been created or returns null
    //if name doesn't exist.
    public User getUser (String userName) throws CustomException{
        return userDB.getUserByUserName(userName);
    }

    @Override
    public User getUserByID(String ID) {
        return userDB.getUserByID(ID);
    }

    @Override
    public void addProjectToUserInterestedList(User user, String projectID) {
        user.addToLikedProjectIDList(projectID);
        userDB.updateUser(user);

    }

    @Override
    public List<String> getUserCredentials(User user) {
        return user.getUserCredentials();
    }

    @Override
    public List<String> getUsersInterestedProjects(User user) {
        return user.getLikedProjectIDList();
    }

    @Override
    public boolean userIsProjectOwner(User user, Project project) {
        String userID;
        String projOwnerID;

        userID = user.getUserID();
        projOwnerID = project.getProjectOwner();

        return userID.equals(projOwnerID);
    }

    public User signUp(User username) throws CustomException{
        User potentialUser = userDB.getUserByUserName(username.getUserNickName());
        if(potentialUser != null){
            throw new CustomException("test");
        }else{
            return potentialUser;
        }
    }

    public User loginRequest(String userName) throws CustomException{
        User user = userDB.getUserByUserName(userName);
        ValidateUser.validUserLogin(user);
        return user;
    }

}
