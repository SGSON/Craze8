package comp3350.ppms.persistence.database;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.ProjectDatabaseInterface;


public class ProjectDatabase implements ProjectDatabaseInterface {

    private Hashtable<String, Project> projectsStub;

    /**
     * Private Constructor Only called if the database has not been
     * "initialized" Creates and stores sample Projects
     */
    public ProjectDatabase() {
        projectsStub = new Hashtable<>();
        storeSampleData();
    }

    private void storeSampleData() {
        final String[] PROJECT_NAMES = {"InternetFlix", "Sports Analyzer,",
                "PickMeUp", "Fun Messenger"};
        final String[] PROJECT_DESCRIPTIONS = {"View videos online",
                "Analyze Cool Sports", "Get a ride anywhere", "Talk to your friends"};
        final String[] PROJECT_CREDENTIALS = {"C", "C++", "Java", "Python"};
        ArrayList<String> credentials = new ArrayList<String>();

        final String[] PROJECT_CREATORS = {"4658", "9651", "4681", "4689"};

        for(int i = 0; i < PROJECT_CREDENTIALS.length; i++) {
            credentials.add(PROJECT_CREDENTIALS[i]);
        }
        for(int i = 0; i < PROJECT_NAMES.length; i++) {
            Project project = new Project(PROJECT_NAMES[i], PROJECT_CREATORS[i],
                    PROJECT_DESCRIPTIONS[i], credentials);
            projectsStub.put(project.getProjectID().toString(), project);
        }
    }

    @Override
    public void addProject(String ID, Project project) {

        projectsStub.put(ID, project);
    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public void removeProject(String ID) {
        projectsStub.remove(ID);
    }

    @Override
    public Project getProject(String id)
    {
        return projectsStub.get(id);
    }

    @Override
    public List<Project> getProjectSequential() {
        List<Project> projects = new ArrayList<Project>();
        Set<String> hashKeys = projectsStub.keySet();

        for(String key: hashKeys) {
            Project project = projectsStub.get(key);
            projects.add(project);
            
        }
        return projects;
    }
}
