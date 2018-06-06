package domain;

import java.util.UUID;
import java.util.List;

public class Project extends DatabaseObject implements ProjectInterface {

    private UUID mProjectID;
    private String mProjectName;
    private String mProjectDescription;

    public Project(String name, String descr) {
        mProjectID = UUID.randomUUID();
        mProjectName = name;
        mProjectDescription = descr;
    }

    @Override
    public UUID getId() {
        return mProjectID;
    }

    //returns the name associated with the project
    public String getName() {
        return mProjectName;
    }

    //returns the description associated with the project
    public String getDescription() {
        return mProjectDescription;
    }

    //returns the list of credentials associated with the project
    public List<String> getCredentials() {
        return null; //TODO - return an ArrayList (an implementation of List interface)
    }
}
