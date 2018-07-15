package comp3350.ppms.domain;

import java.util.UUID;
import java.util.ArrayList;

public interface ProjectInterface {

    String getProjectID();

    String getProjectOwner();

    String getProjectName();

    String getProjectDescription();

    ArrayList<String> getProjectCredentials();


    void addInterestedUser(String id);

    void addSelectedUser(String userID);

    ArrayList<String> getInterestedUsers();

    ArrayList<String> getSelectedUsers();

    int getNumInterestedUsers();

}
