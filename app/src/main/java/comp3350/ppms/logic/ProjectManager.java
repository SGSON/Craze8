package comp3350.ppms.logic;

import java.util.ArrayList;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.Project;

import comp3350.ppms.persistence.ProjectDatabaseInterface;

/**
 * Here is the Project Manager class that will handle
 * project input to the ProjectProjectDatabase as well as project retrieval from
 * the ProjectProjectDatabase.
 */
public class ProjectManager implements ProjectManagerInterface{

    private ProjectDatabaseInterface projectDB;

    public ProjectManager() {
        projectDB = Service.getProjectDatabaseInterface();
    }

    public void insertProject(Project project) throws CustomException{
        ValidateProject.validateAll(project);

        projectDB.addProject(project.getProjectID(), project);
    }

    public ArrayList<Project> getProjects(){
        return projectDB.getProjectSequential();
    }

    public Project getProject(String id) { return projectDB.getProject(id); }

    @Override
    public void addInterestedUser(Project project, String userName) {
        project.addInterestedUser(userName);
        projectDB.updateProject(project);
    }

}
