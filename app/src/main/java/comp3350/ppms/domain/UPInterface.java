package comp3350.ppms.domain;

import java.util.ArrayList;
import java.util.UUID;

public interface UPInterface {

      UUID getUserID();

      String getUserNickName();

      ArrayList<UUID> getCreatedProjectIDList();

      ArrayList<UUID> getLikedProjectIDList();

      ArrayList<UUID> getMatchedProjectIDList();

      ArrayList<String> getUserCredentials();

      UUID gerProjectID();

      String getProjectName();

      String getProjectDesciption();

      ArrayList<String> getProjectCredentials();
}
