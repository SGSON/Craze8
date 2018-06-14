package logic;

import java.util.ArrayList;
import java.util.UUID;

import application.Service;
import domain.DatabaseObject;
import domain.Project;

import persistence.Database;
import persistence.DatabaseInterface;

/**
 * Here is the Project Manager class that will handle
 * project input to the database as well as project retrieval from
 * the database.
 */
public class ProjectManager implements ProjectManagerInterface{

    private static final int nameLength = 16;
    private static final int descLength = 256;
    private DatabaseInterface database;
    private ArrayList<Project> databaseProjects;
    private ArrayList<Project> projects;

    public ProjectManager() {
        database = Service.getDatabaseInterface();
        projects = new ArrayList<>();
    }

    public void insertProject(DatabaseObject project) throws CustomException{
        ValidateProject.validateAll(project);
        database.addProject(project.getId(),project);
    }

    public ArrayList<Project> getProjects(){
        return database.getProjectSequential();
    }
}
