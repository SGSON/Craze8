package comp3350.ppms.domain;

import java.util.ArrayList;
import java.util.UUID;

public class User implements UserInterface{
    private String userID;
    private String userNickName;
    private String userPassword;
    private ArrayList<String> userCredentials;
    private ArrayList<String> createdProjectIDList;
    private ArrayList<String> likedProjectIDList;
    private ArrayList<String> matchedProjectIDList;

    public User(String newUserNickName, String newUserPassword)
    {
        this.userID = UUID.randomUUID().toString();
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


    public String getUserID()
    {
        return (userID);
    }

    public String getUserNickName()
    {
        return (userNickName);
    }

    public String getUserPassword() {
        return (userPassword);}

    public ArrayList<String> getCreatedProjectIDList() {
        return createdProjectIDList;
    }

    public ArrayList<String> getLikedProjectIDList() {
        return likedProjectIDList;
    }

    public ArrayList<String> getMatchedProjectIDList() {
        return matchedProjectIDList;
    }

    public ArrayList<String> getUserCredentials() {
        return userCredentials;
    }

    public void addToCreatedProjectIDList(String projectID){
        createdProjectIDList.add(projectID);
    }

    public void addToLikedProjectIDList(String projectID){
        likedProjectIDList.add(projectID);
    }

    public void addToMatchedProjectIDList(String projectID){
        matchedProjectIDList.add(projectID);
    }

    public void addCredentialList(ArrayList<String> credentials) {
        userCredentials.addAll(credentials);
    }

}
