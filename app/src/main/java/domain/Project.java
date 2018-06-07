package domain;

import java.util.UUID;
import java.util.ArrayList;

public class Project extends DatabaseObject implements ProjectInterface {

    private UUID mProjectID;
    private String mProjectName;
    private String mProjectDescription;
    private ArrayList<String> mCredentials;

    public Project(String name, String descr, ArrayList<String> cred) {
        mProjectID = UUID.randomUUID();
        mProjectName = name;
        mProjectDescription = descr;
        mCredentials = cred;
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
    public ArrayList<String> getCredentials() {
        return mCredentials;
    }
}
