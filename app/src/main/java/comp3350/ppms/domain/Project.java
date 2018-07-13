package comp3350.ppms.domain;

import java.util.UUID;
import java.util.ArrayList;

public class Project implements ProjectInterface {

    private String projectID;
    private String projectName;
    private String projectDescription;
    private ArrayList<String> projectCredentials;
    private ArrayList<String> interestedUsers;
    private ArrayList<String> selectedUsers;


    public Project(String name, String descr, ArrayList<String> cred) {
        projectID = UUID.randomUUID().toString();
        projectName = name;
        projectDescription = descr;
        projectCredentials = cred;
        interestedUsers = new ArrayList<String>();
        selectedUsers = new ArrayList<String>();
    }

    public Project(String ID, String name, String descr, ArrayList<String> cred,
                   ArrayList<String> inUsers, ArrayList<String> selUsers) {
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

    public ArrayList<String> getProjectCredentials() {
        return projectCredentials;
    }

    public void addInterestedUser(String id) { interestedUsers.add(id); }

    @Override
    public void addSelectedUser(String userID) {
     selectedUsers.add(userID);
    }

    @Override
    public ArrayList<String> getInterestedUsers() {
        return interestedUsers;
    }

    @Override
    public ArrayList<String> getSelectedUsers() {
        return selectedUsers;
    }

    @Override
    public int getNumInterestedUsers() {
        return interestedUsers.size();
    }

}
