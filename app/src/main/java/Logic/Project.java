package Logic;

import java.util.UUID;

import Persistence.DatabaseObject;

public class Project extends DatabaseObject {

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
}
