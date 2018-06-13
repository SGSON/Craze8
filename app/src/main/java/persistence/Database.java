package persistence;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import domain.DatabaseObject;
import domain.Project;


public class Database implements DatabaseInterface {

    private static Database databaseSingleton;
    private Hashtable<UUID, DatabaseObject> databaseStorage;

    /**
     * getDatabaseInstance
     *
     * PURPOSE: Other layers will call this method to get the signle instance
     * of the Database, if the single instance does not already exists, call the constructor
     * @return
     */

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
        String[] PROJECT_NAMES = {"InternetFlix", "Sports Analyzer,",
                "PickMeUp", "Fun Messenger"};
        String[] PROJECT_DESCRIPTIONS = {"View videos online",
                "Analyze Cool Sports", "Get a ride anywhere", "Talk to your friends"};
        String[] PROJECT_CREDENTIALS = {"C", "C++", "Java", "Python"};
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
    public ArrayList<DatabaseObject> getInterestedUsersForProject(UUID projectID) {
        //Will return a list of Users interested in the specified Project ID
        return null;
    }

    @Override
    public ArrayList<Project> getProjectSequential() {
        ArrayList<Project> projects = new ArrayList<Project>();
        Set<UUID> hashKeys = databaseStorage.keySet();

        for(UUID key: hashKeys) {
            DatabaseObject object = databaseStorage.get(key);
            if(object instanceof Project) {
                Project dbProject = (Project) object;
                projects.add(dbProject);
            }
        }
        return projects;
    }
}
