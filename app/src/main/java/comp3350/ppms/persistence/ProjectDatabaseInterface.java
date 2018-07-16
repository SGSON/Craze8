package comp3350.ppms.persistence;


import java.util.List;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;

public interface ProjectDatabaseInterface {

    /*
    This is the method signatures that will interact with out database
    All will have void return values for now, but once we have our classes in the project
    We can add the correct return values and the correct parameters
     */

    void addProject(String ID, Project project);
    void updateProject(Project project);
    void removeProject(String ID);
    List<Project> getProjectSequential();
    Project getProject(String id);

}
