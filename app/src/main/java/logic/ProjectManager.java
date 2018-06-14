package logic;

import java.util.ArrayList;

import application.Service;
import domain.DatabaseObject;
import domain.Project;

import persistence.ProjectDatabaseInterface;

/**
 * Here is the Project Manager class that will handle
 * project input to the ProjectProjectDatabase as well as project retrieval from
 * the ProjectProjectDatabase.
 */
public class ProjectManager implements ProjectManagerInterface{

    private ProjectDatabaseInterface mDatabase;

    public ProjectManager() {
        mDatabase = Service.getProjectDatabaseInterface();
    }

    public void insertProject(DatabaseObject project) throws CustomException{
        ValidateProject.validateAll(project);
        mDatabase.addProject(project.getId(),project);
    }

    public ArrayList<Project> getProjects(){
        return mDatabase.getProjectSequential();
    }
}
