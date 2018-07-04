package comp3350.ppms.domain;

import java.util.ArrayList;
import java.util.UUID;

public interface UPInterface {

      String getUserID();

      String getUserNickName();

      ArrayList<String> getCreatedProjectIDList();

      ArrayList<String> getLikedProjectIDList();

      ArrayList<String> getMatchedProjectIDList();

      ArrayList<String> getUserCredentials();

      String getProjectID();

      String getProjectName();

      String getProjectDesciption();

      ArrayList<String> getProjectCredentials();
}
