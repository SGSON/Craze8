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
 * and validate data of a Project object. 
 */
//public class ProjectManager implements ProjectManagerInterface{
public class ProjectManager{

    private DatabaseInterface database;
    private ArrayList<DatabaseObject> databaseObjects;
    private ArrayList<Project> projects;

    public ProjectManager(){
        database = Service.getdatabaseInterface();
        projects = new ArrayList<>();
    }

    public void insertProject(DatabaseObject project) throws CustomException{
        ValidateProject.validateAll(project);
        database.addProject(project.getId(),project);
    }

    public ArrayList<Project> getProjects(){
        databaseObjects = database.getProjectSequential();
        for(DatabaseObject item : databaseObjects){
            projects.add((Project)item);
        }
        return projects;
    }
}
