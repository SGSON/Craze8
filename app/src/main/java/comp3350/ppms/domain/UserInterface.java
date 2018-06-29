package comp3350.ppms.domain;

import java.util.UUID;
import java.util.ArrayList;

public interface UserInterface {

    String getUserID();

    String getUserNickName();

    String getUserPassword();

    ArrayList<String> getCreatedProjectIDList();

    ArrayList<String> getLikedProjectIDList();

    ArrayList<String> getMatchedProjectIDList();

    ArrayList<String> getUserCredentials();

    void addToCreatedProjectIDList(String projectID);

    void addToLikedProjectIDList(String projectID);

    void addToMatchedProjectIDList(String projectID);

    void addCredentialList(ArrayList<String> credentials);
}
