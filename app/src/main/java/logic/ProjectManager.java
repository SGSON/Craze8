package logic;

import domain.DatabaseObject;

/**
 * Here is the Project Manager class that will handle
 * and validate data of a Project object. 
 */
public class ProjectManager implements ProjectManagerInterface{


    @Override
    public boolean processNewProjectRequest(DatabaseObject project) {
        return false;
    }

    @Override
    public boolean validateUUID(DatabaseObject project) {
        return false;
    }

    @Override
    public boolean validateName(DatabaseObject project) {
        return false;
    }

    @Override
    public boolean validateDescription(DatabaseObject project) {
        return false;
    }

    @Override
    public boolean validateCredentials(DatabaseObject project) {
        return false;
    }
}
