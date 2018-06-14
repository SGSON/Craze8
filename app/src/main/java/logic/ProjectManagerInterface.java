package logic;

import java.util.ArrayList;

import domain.DatabaseObject;
import domain.Project;

public interface ProjectManagerInterface {

    //This function determines if the project fields meet the requirements.
    //If information entered is valid, the project is added to the databases.
    //If the information entered is invalid, the function returns false.

    void insertProject(DatabaseObject currentProject) throws CustomException;

    ArrayList<Project> getProjects();
}

