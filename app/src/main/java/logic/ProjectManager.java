package logic;

import java.util.ArrayList;
import java.util.UUID;

import application.Service;
import domain.DatabaseObject;
import domain.Project;

import persistence.DatabaseInterface;

/**
 * Here is the Project Manager class that will handle
 * and validate data of a Project object. 
 */
//public class ProjectManager implements ProjectManagerInterface{
public class ProjectManager{

    private static final int nameLength = 16;
    private static final int descLength = 256;
    private DatabaseInterface database;
    private ArrayList<Project> databaseProjects;
    private ArrayList<Project> projects;

    public ProjectManager() {
        database = Service.getDatabaseInterface();
        projects = new ArrayList<>();
    }

    //@Override
    public static boolean processNewProjectRequest(DatabaseObject project) throws CustomException {
        validateUUID(project);
        validateName(project);
        validateDescription(project);
        validateCredentials(project);
        return false;
    }

    //@Override
    public static boolean validateUUID(DatabaseObject project) {
        return false;
    }

    //@Override
    public static boolean validateName(DatabaseObject project) throws CustomException {
        if (project.getName().length() > nameLength){
            throw new CustomException("Name exceed required amount of characters");
        } else if (project.getName() == null || project.getName() == ""){
            throw new CustomException("Cannot have empty name");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateDescription(DatabaseObject project) throws CustomException {
        if (project.getDescription().length() > descLength){
            throw new CustomException("Description exceed required amount of characters");
        } else if (project.getDescription() == null || project.getDescription().isEmpty()){
            throw new CustomException("Cannot have empty description");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateCredentials(DatabaseObject project) throws CustomException {
        return false;
    }


    public void insertProject(DatabaseObject project){
        UUID uuid = UUID.randomUUID();
        database.addProject(uuid,project);
    }


    public ArrayList<Project> getProjects(){
        return database.getProjectSequential();
    }
}
