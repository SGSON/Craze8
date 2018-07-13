package comp3350.ppms.logic;

import java.util.List;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;

public interface UserManagerInterface {

    void insertUser(User user) throws CustomException;

    User getUser (String userName) throws CustomException;

    User getUserByID(String ID);

    void addProjectToUserInterestedList(User user, String projectID);

    List<String> getUserCredentials(User user);
    List<String> getUsersInterestedProjects(User user);

    boolean userIsProjectOwner(User user, Project project);

}

