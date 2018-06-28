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
    
    void addProject(UUID ID, Project project);
    void removeProject(UUID ID);
    public ArrayList<Project> getProjectSequential();

}
