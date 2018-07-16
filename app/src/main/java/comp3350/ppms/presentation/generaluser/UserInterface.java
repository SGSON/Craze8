package comp3350.ppms.domain;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public interface UserInterface {

    String getUserID();

    String getUserNickName();

    String getUserPassword();

    List<String> getCreatedProjectIDList();

    List<String> getLikedProjectIDList();

    List<String> getMatchedProjectIDList();

    List<String> getUserCredentials();

    void addToCreatedProjectIDList(String projectID);

    void addToLikedProjectIDList(String projectID);

    void addToMatchedProjectIDList(String projectID);

    void addCredentialList(ArrayList<String> credentials);
}
