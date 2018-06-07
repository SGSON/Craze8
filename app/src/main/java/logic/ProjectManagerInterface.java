package logic;

import domain.DatabaseObject;

public interface ProjectManagerInterface {

    //This function determines if the project fields meet the requirements.
    //If information entered is valid, the project is added to the databases.
    //If the information entered is invalid, the function returns false.
    Boolean processNewProjectRequest(DatabaseObject project);
  
    Boolean validateName(DatabaseObject project);  
    
    Boolean validateDescription(DatabaseObject project);
    
    Boolean validateCredentials(DatabaseObject project);
}
