package comp3350.ppms.logic;

import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;

import comp3350.ppms.domain.User;
import comp3350.ppms.domain.ValidateProject;
import comp3350.ppms.persistence.ProjectDatabaseInterface;

/**
 * Here is the Project Manager class that will handle
 * project input to the ProjectProjectDatabase as well as project retrieval from
 * the ProjectProjectDatabase.
 */
public class ProjectManager implements ProjectManagerInterface{

    public ProjectManager() {
        projectDB = Service.getProjectDatabaseInterface();
    }

    public ArrayList<Project> getProjects(){
        return projectDB.getProjectSequential();
    }

    public Project getProject(String id) { return projectDB.getProject(id); }

    private ProjectDatabaseInterface projectDB;


    @Override
    public String getProjectName(Project project) {
        return project.getProjectName();
    }

    @Override
    public String getProjectDescription(Project project) {
        return project.getProjectDescription();
    }

    @Override
    public ArrayList<String> getProjectCredentials(Project project) {
        return project.getProjectCredentials();
    }

    public void insertProject(Project project) throws CustomException {
        ValidateProject.validateAll(project);

        projectDB.addProject(project.getProjectID(), project);
    }



    @Override
    public void addInterestedUser(Project project, String userName) {
        project.addInterestedUser(userName);
        projectDB.updateProject(project);
    }

    @Override
    public boolean addSelectedUser(Project project, String userID) {
        boolean added = true;
        project.addSelectedUser(userID);
        projectDB.updateProject(project);
        return added;
    }

    @Override
    public ArrayList<String> getSelectedUsersForProject(Project project) {
        return project.getSelectedUsers();
    }

    @Override
    public ArrayList<User> getInterestedUsers(Project project) {
        ArrayList<User> intUsers = new ArrayList<User>();
        UserManager userManager = new UserManager();
        ArrayList<String> userIDs = project.getInterestedUsers();

        for (int i = 0; i < userIDs.size(); i++) {
            User currUser = userManager.getUserByID(userIDs.get(i));
            intUsers.add(currUser);
        }

        return intUsers;
    }
}
