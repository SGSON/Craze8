package comp3350.ppms.logic;

import java.util.List;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;

public interface ProjectManagerInterface {

    //This function determines if the project fields meet the requirements.
    //If information entered is valid, the project is added to the databases.
    //If the information entered is invalid, the function throws an exception.

    String getProjectName(Project project);
    String getProjectDescription(Project project);
    List<String> getProjectCredentials(Project project);
    void insertProject(Project currentProject) throws CustomException;

    List<Project> getProjects();

    Project getProject(String id);

    void addInterestedUser(Project project, String userName);

    boolean addSelectedUser(Project project, String userID);

    List<String> getSelectedUsersForProject(Project project);

    List<User> getInterestedUsers(Project project);
}

