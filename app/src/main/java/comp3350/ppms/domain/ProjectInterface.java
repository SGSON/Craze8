package comp3350.ppms.domain;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public interface ProjectInterface {

    String getProjectID();

    String getProjectOwner();

    String getProjectName();

    String getProjectDescription();

    List<String> getProjectCredentials();

    void addInterestedUser(String id);

    List<String> getInterestedUsers();

    void addSelectedUser(String userID);

    List<String> getSelectedUsers();

    int getNumInterestedUsers();
}
