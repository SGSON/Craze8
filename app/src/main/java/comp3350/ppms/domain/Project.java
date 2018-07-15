package comp3350.ppms.domain;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public class Project implements ProjectInterface {

    private String projectID;
    private String projectName;
    private String projectDescription;
    private List<String> projectCredentials;
    private List<String> interestedUsers;
    private List<String> selectedUsers;


    public Project(String name, String descr, ArrayList<String> cred) {
        projectID = UUID.randomUUID().toString();
        projectName = name;
        projectDescription = descr;
        projectCredentials = cred;
        interestedUsers = new ArrayList<String>();
        selectedUsers = new ArrayList<String>();
    }

    public Project(String ID, String name, String descr, List<String> cred,
                   List<String> inUsers, List<String> selUsers) {
        projectID = ID;
        projectName = name;
        projectDescription = descr;
        projectCredentials = cred;
        interestedUsers = inUsers;
        selectedUsers = selUsers;
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

    public List<String> getProjectCredentials() {
        return projectCredentials;
    }

    public void addInterestedUser(String id) { interestedUsers.add(id); }

    @Override
    public List<String> getInterestedUsers() {
        return interestedUsers;
    }

    @Override
    public List<String> getSelectedUsers() {
        return selectedUsers;
    }

}
