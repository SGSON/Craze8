package comp3350.ppms.domain;

import java.util.ArrayList;
import java.util.UUID;

public class UP implements UPInterface{

    private final User user;
    private final Project project;

    public UP(final User user, final Project project)
    {
        this.user = user;
        this.project = project;
    }

    public UUID getUserID()
    {
        return (user.getUserID());
    }

    public String getUserNickName(){
        return (user.getUserNickName());
    }

    public ArrayList<UUID> getCreatedProjectIDList(){
        return (user.getCreatedProjectIDList());
    }

    public ArrayList<UUID> getLikedProjectIDList(){
        return (user.getLikedProjectIDList());
    }

    public ArrayList<UUID> getMatchedProjectIDList(){
        return (user.getMatchedProjectIDList());
    }

    public ArrayList<String> getUserCredentials(){
        return (user.getUserCredentials());
    }

    public UUID gerProjectID()
    {
        return (project.getProjectID());
    }

    public String getProjectName()
    {
        return (project.getProjectName());
    }

    public String getProjectDesciption(){
        return (project.getProjectDescription());
    }

    public ArrayList<String> getProjectCredentials()
    {
        return (project.getProjectCredentials());
    }

}