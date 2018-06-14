package persistence;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import domain.DatabaseObject;
import domain.Project;


public class Database implements DatabaseInterface {

    private static Database mDatabaseSingleton;
    private Hashtable<UUID, DatabaseObject> mDatabaseStorage;

    /**
     * getDatabaseInstance
     *
     * PURPOSE: Other layers will call this method to get the signle instance
     * of the Database, if the single instance does not already exists, call the constructor
     * @return
     */

    public static Database getDatabaseInstance() {
        if(mDatabaseSingleton == null) {
            mDatabaseSingleton = new Database();
        }

        return mDatabaseSingleton;
    }

    /**
     * Private Constructor Only called if the database has not been
     * "initialized" Creates and stores sample Projects
     */
    private Database() {
        mDatabaseStorage = new Hashtable<>();
        storeSampleData();
    }

    private void storeSampleData() {
        final String[] PROJECT_NAMES = {"InternetFlix", "Sports Analyzer,",
                "PickMeUp", "Fun Messenger"};
        final String[] PROJECT_DESCRIPTIONS = {"View videos online",
                "Analyze Cool Sports", "Get a ride anywhere", "Talk to your friends"};
        final String[] PROJECT_CREDENTIALS = {"C", "C++", "Java", "Python"};
        ArrayList<String> credentials = new ArrayList<String>();

        for(int i = 0; i < PROJECT_CREDENTIALS.length; i++) {
            credentials.add(PROJECT_CREDENTIALS[i]);
        }
        for(int i = 0; i < PROJECT_NAMES.length; i++) {
            Project project = new Project(PROJECT_NAMES[i], PROJECT_DESCRIPTIONS[i], credentials);
            mDatabaseStorage.put(project.getId(), project);
        }
    }

    @Override
    public void addProject(UUID ID, DatabaseObject project) {

        mDatabaseStorage.put(ID, project);
    }

    @Override
    public void removeProject(UUID ID) {
        mDatabaseStorage.remove(ID);
    }


    @Override
    public ArrayList<Project> getProjectSequential() {
        ArrayList<Project> projects = new ArrayList<Project>();
        Set<UUID> hashKeys = mDatabaseStorage.keySet();

        for(UUID key: hashKeys) {
            DatabaseObject object = mDatabaseStorage.get(key);
            if(object instanceof Project) {
                Project dbProject = (Project) object;
                projects.add(dbProject);
            }
        }
        return projects;
    }
}
