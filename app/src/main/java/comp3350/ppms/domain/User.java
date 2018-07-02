package comp3350.ppms.domain;

import java.util.ArrayList;
import java.util.UUID;

public class User implements UserInterface{
    private UUID userID;
    private String userNickName;
    private String userPassword;
    private ArrayList<String> userCredentials;
    private ArrayList<UUID> createdProjectIDList;
    private ArrayList<UUID> likedProjectIDList;
    private ArrayList<UUID> matchedProjectIDList;

    public User(String newUserNickName, String newUserPassword)
    {
        this.userID = UUID.randomUUID();
        this.userNickName = newUserNickName;
        this.userPassword = newUserPassword;
        this.createdProjectIDList = new ArrayList<>();
        this.likedProjectIDList = new ArrayList<>();
        this.matchedProjectIDList = new ArrayList<>();
        this.userCredentials = new ArrayList<>();
    }

    public User(String ID, String userName, ArrayList<String> createdProjectIDList, ArrayList<String> likedProjectIDList, ArrayList<String> matchedProjectIDList, ArrayList<String> userCredentials){
        this.userID = ID;
        this.userNickName = userName;
        this.createdProjectIDList = createdProjectIDList;
        this.likedProjectIDList = likedProjectIDList;
        this.matchedProjectIDList = matchedProjectIDList;
        this.userCredentials = userCredentials;
    }


    public UUID getUserID()
    {
        return (userID);
    }

    public String getUserNickName()
    {
        return (userNickName);
    }

    public String getUserPassword() {
        return (userPassword);}

    public ArrayList<UUID> getCreatedProjectIDList() {
        return createdProjectIDList;
    }

    public ArrayList<UUID> getLikedProjectIDList() {
        return likedProjectIDList;
    }

    public ArrayList<UUID> getMatchedProjectIDList() {
        return matchedProjectIDList;
    }

    public ArrayList<String> getUserCredentials() {
        return userCredentials;
    }

    public void addToCreatedProjectIDList(UUID projectID){
        createdProjectIDList.add(projectID);
    }

    public void addToLikedProjectIDList(UUID projectID){
        likedProjectIDList.add(projectID);
    }

    public void addToMatchedProjectIDList(UUID projectID){
        matchedProjectIDList.add(projectID);
    }

    public void addCredentialList(ArrayList<String> credentials) {
        userCredentials.addAll(credentials);
    }

}
