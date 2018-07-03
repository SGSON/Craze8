package comp3350.ppms.domain;

import java.util.UUID;
import java.util.ArrayList;

public interface ProjectInterface {

    String getProjectID();

    String getProjectName();

    String getProjectDescription();

    ArrayList<String> getProjectCredentials();

    ArrayList<String> getInterestedUsers();

    ArrayList<String> getSelectedUsers();
}
