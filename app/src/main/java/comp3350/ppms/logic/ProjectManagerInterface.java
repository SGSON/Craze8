package comp3350.ppms.logic;

import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.domain.Project;

public interface ProjectManagerInterface {

    //This function determines if the project fields meet the requirements.
    //If information entered is valid, the project is added to the databases.
    //If the information entered is invalid, the function throws an exception.

    void insertProject(Project currentProject) throws CustomException;

    ArrayList<Project> getProjects();

    Project getProject(UUID id);
}

