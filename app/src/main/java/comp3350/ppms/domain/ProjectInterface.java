package comp3350.ppms.domain;

import java.util.UUID;
import java.util.ArrayList;

public interface ProjectInterface {

    UUID getProjectID();

    String getProjectName();

    String getProjectDescription();

    ArrayList<String> getProjectCredentials();
}
