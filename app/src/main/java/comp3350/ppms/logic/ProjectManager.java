package comp3350.ppms.logic;

import java.util.ArrayList;
import java.util.List;
import comp3350.ppms.application.Service;
import comp3350.ppms.domain.Project;

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

    public List<Project> getProjects(){
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
    public List<String> getProjectCredentials(Project project) {
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

}
