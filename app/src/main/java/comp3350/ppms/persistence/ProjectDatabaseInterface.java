package comp3350.ppms.persistence;

import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.domain.Project;

public interface ProjectDatabaseInterface {

    /*
    This is the method signatures that will interact with out database
    All will have void return values for now, but once we have our classes in the project
    We can add the correct return values and the correct parameters
     */

    void addProject(String ID, Project project);
    void updateProject(Project project);
    void removeProject(String ID);
    ArrayList<Project> getProjectSequential();
    Project getProject(String id);


}
