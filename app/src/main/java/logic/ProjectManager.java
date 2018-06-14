package logic;

import java.util.ArrayList;

import application.Service;
import domain.DatabaseObject;
import domain.Project;

import persistence.DatabaseInterface;

/**
 * Here is the Project Manager class that will handle
 * project input to the Database as well as project retrieval from
 * the Database.
 */
public class ProjectManager implements ProjectManagerInterface{

    private DatabaseInterface mDatabase;

    public ProjectManager() {
        mDatabase = Service.getDatabaseInterface();
    }

    public void insertProject(DatabaseObject project) throws CustomException{
        ValidateProject.validateAll(project);
        mDatabase.addProject(project.getId(),project);
    }

    public ArrayList<Project> getProjects(){
        return mDatabase.getProjectSequential();
    }
}
