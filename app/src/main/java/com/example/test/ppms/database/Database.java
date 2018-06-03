package com.example.test.ppms.database;

import java.util.Hashtable;
import java.util.UUID;


public class Database implements DatabaseInterface {

    private static Hashtable<UUID, String> DatabaseStorage = new Hashtable<UUID, String>();

    @Override
    public void addUser(UUID ID, String user) {
        DatabaseStorage.put(ID, user);
    }

    @Override
    public void removeUser(UUID ID) {
        DatabaseStorage.remove(ID);
    }

    @Override
    public void getUserInfo(UUID ID) {
        DatabaseStorage.get(ID);
    }

    @Override
    public void addProject(UUID ID, String project) {
        DatabaseStorage.put(ID, project);
    }

    @Override
    public void removeProject(UUID ID) {
        DatabaseStorage.remove(ID);
    }

    @Override
    public void getProjectOwner(UUID projectID) {
        DatabaseStorage.get(projectID);
    }

    @Override
    public void getProjectsByOwnerID(UUID projectOwnerID) {
        //Will return a list of Projects created by Project Owner
    }

    @Override
    public void getInterestedUsersForProject(UUID projectID) {
        //Will return a list of Users interested in the specified Project ID
    }
}
