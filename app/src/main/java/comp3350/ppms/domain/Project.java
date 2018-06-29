package comp3350.ppms.domain;

import java.util.UUID;
import java.util.ArrayList;

public class Project implements ProjectInterface {

    private String projectID;
    private String projectName;
    private String projectDescription;
    private ArrayList<String> projectCredentials;

    public Project(String name, String descr, ArrayList<String> cred) {
        projectID = UUID.randomUUID().toString();
        projectName = name;
        projectDescription = descr;
        projectCredentials = cred;
    }

    @Override
    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public ArrayList<String> getProjectCredentials() {
        return projectCredentials;
    }
}
