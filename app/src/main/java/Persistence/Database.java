package Persistence;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;


public class Database implements DatabaseInterface {

    private static Hashtable<UUID, DatabaseObject> DatabaseStorage = new Hashtable<UUID, DatabaseObject>();

    @Override
    public void addUser(UUID ID, DatabaseObject user) {
        DatabaseStorage.put(ID, user);
    }

    @Override
    public void removeUser(UUID ID) {
        DatabaseStorage.remove(ID);
    }

    @Override
    public void getUserInfo(UUID ID) {
        DatabaseStorage.get(ID);
    }

    @Override
    public void addProject(UUID ID, DatabaseObject project) {
        DatabaseStorage.put(ID, project);
    }

    @Override
    public void removeProject(UUID ID) {
        DatabaseStorage.remove(ID);
    }

    @Override
    public DatabaseObject getProjectOwner(UUID projectID) {
        DatabaseStorage.get(projectID);
        return null;
    }

    @Override
    public ArrayList<DatabaseObject> getProjectsByOwnerID(UUID projectOwnerID) {
        //Will return a list of Projects created by Project Owner
        return null;
    }

    @Override
    public ArrayList<DatabaseObject> getInterestedUsersForProject(UUID projectID) {
        //Will return a list of Users interested in the specified Project ID
        return null;
    }
}
