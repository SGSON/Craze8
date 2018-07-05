package comp3350.ppms.logic;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;

public interface UserManagerInterface {

    void insertUser(User user) throws CustomException;

    User getUser (String userName) throws CustomException;

    void addProjectToUserInterestedList(User user, String projectID);

}

