package persistence;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import domain.DatabaseObject;
import domain.Project;


public class Database implements DatabaseInterface {

    private static final String[] PROJECT_NAMES = {"InternetFlix", "Sports Analyzer,",
            "PickMeUp", "Fun Messenger"};

    private static final String[] PROJECT_DESCRIPTIONS = {"View videos online",
            "Analyze Cool Sports", "Get a ride anywhere", "Talk to your friends"};

    private static final String[] PROJECT_CREDENTIALS = {"C", "C++", "Java", "Python"};


    private static Database databaseSingleton;
    private Hashtable<UUID, DatabaseObject> databaseStorage;

    public static Database getDatabaseInstance() {
        if(databaseSingleton == null) {
            databaseSingleton = new Database();
        }

        return databaseSingleton;
    }

    /**
     * Private Constructor Only called if the database has not been
     * "initialized" Creates and stores sample Projects
     */
    private Database() {
        databaseStorage = new Hashtable<>();
        storeSampleData();
    }

    private void storeSampleData() {
        ArrayList<String> credentials = new ArrayList<String>();
        for(int i = 0; i < PROJECT_CREDENTIALS.length; i++) {
            credentials.add(PROJECT_CREDENTIALS[i]);
        }
        for(int i = 0; i < PROJECT_NAMES.length; i++) {
            Project project = new Project(PROJECT_NAMES[i], PROJECT_DESCRIPTIONS[i], credentials);
            databaseStorage.put(project.getId(), project);
        }
    }

    @Override
    public void addUser(UUID ID, DatabaseObject user) {
        databaseStorage.put(ID, user);
    }

    @Override
    public void removeUser(UUID ID) {
        databaseStorage.remove(ID);
    }

    @Override
    public void getUserInfo(UUID ID) {
        databaseStorage.get(ID);
    }

    @Override
    public void addProject(UUID ID, DatabaseObject project) {
        databaseStorage.put(ID, project);
    }

    @Override
    public void removeProject(UUID ID) {
        databaseStorage.remove(ID);
    }

    @Override
    public DatabaseObject getProjectOwner(UUID projectID) {
        databaseStorage.get(projectID);
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

    @Override
    public ArrayList<DatabaseObject> getProjectSequential() {
        return null;
    }
}
