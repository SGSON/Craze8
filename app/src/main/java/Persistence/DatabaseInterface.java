package com.example.test.ppms.database;

import java.util.UUID;

public interface DatabaseInterface {

    /*
    This is the method signatures that will interact with out database
    All will have void return values for now, but once we have our classes in the project
    I will add the correct return values
     */

    void addUser(UUID ID, String user);
    void removeUser(UUID ID);
    void getUserInfo(UUID ID);


    void addProject(UUID ID, String project);
    void removeProject(UUID ID);
    void getProjectOwner(UUID projectID);
    void getProjectsByOwnerID(UUID projectOwnerID);
    void getInterestedUsersForProject(UUID projectID);
}
