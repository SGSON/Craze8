package comp3350.ppms.domain;

import java.util.UUID;
import java.util.ArrayList;

public interface UserInterface {

    UUID getUserID();

    String getUserNickName();

    String getUserPassword();

    ArrayList<UUID> getCreatedProjectIDList();

    ArrayList<UUID> getLikedProjectIDList();

    ArrayList<UUID> getMatchedProjectIDList();

    ArrayList<String> getUserCredentials();

    void addToCreatedProjectIDList(UUID projectID);

    void addToLikedProjectIDList(UUID projectID);

    void addToMatchedProjectIDList(UUID projectID);

    void addCredentialList(ArrayList<String> credentials);
}
