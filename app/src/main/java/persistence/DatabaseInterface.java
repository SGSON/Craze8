package persistence;

import java.util.ArrayList;
import java.util.UUID;

import domain.DatabaseObject;

public interface DatabaseInterface {

    /*
    This is the method signatures that will interact with out database
    All will have void return values for now, but once we have our classes in the project
    We can add the correct return values and the correct parameters
     */

    void addUser(UUID ID, DatabaseObject user);
    void removeUser(UUID ID);
    void getUserInfo(UUID ID);


    void addProject(UUID ID, DatabaseObject project);
    void removeProject(UUID ID);
    DatabaseObject getProjectOwner(UUID projectID);
    ArrayList<DatabaseObject> getProjectsByOwnerID(UUID projectOwnerID);
    ArrayList<DatabaseObject> getInterestedUsersForProject(UUID projectID);

    public ArrayList<DatabaseObject> getProjectSequential();

}
