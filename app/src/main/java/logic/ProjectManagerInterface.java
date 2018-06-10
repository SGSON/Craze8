package logic;

import domain.DatabaseObject;

public interface ProjectManagerInterface {

    //This function determines if the project fields meet the requirements.
    //If information entered is valid, the project is added to the databases.
    //If the information entered is invalid, the function returns false.

    boolean processNewProjectRequest(DatabaseObject project);
  
    boolean validateUUID(DatabaseObject project) throws Exception;
  
    boolean validateName(DatabaseObject project) throws Exception;
    
    boolean validateDescription(DatabaseObject project) throws Exception;
    
    boolean validateCredentials(DatabaseObject project) throws Exception;
}
