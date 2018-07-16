package comp3350.ppms.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements UserInterface{
    private String userID;
    private String userNickName;
    private String userPassword;
    private List<String> userCredentials;
    private List<String> createdProjectIDList;
    private List<String> likedProjectIDList;
    private List<String> matchedProjectIDList;

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

    public User(String ID, String userName, String password, List<String> createdProjectIDList,
                List<String> likedProjectIDList, List<String> matchedProjectIDList,
                List<String> userCredentials) {
        this.userID = ID;
        this.userNickName = userName;
        this.userPassword = password;
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

    public List<String> getCreatedProjectIDList() {
        return createdProjectIDList;
    }

    public List<String> getLikedProjectIDList() {
        return likedProjectIDList;
    }

    public List<String> getMatchedProjectIDList() {
        return matchedProjectIDList;
    }

    public List<String> getUserCredentials() {
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
