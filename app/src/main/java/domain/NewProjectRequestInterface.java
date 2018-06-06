package domain;

import java.util.ArrayList;

public interface NewProjectRequestInterface {

    Boolean processProjectRequest(String mProjectName, String mProjectDescr, ArrayList<String> credentials);
    //This function determines if the project information entered meets the requirements.
    //If information entered is valid, the function creates a new Project object and
    //passes it to the ProjectManager, then returns true.
    //If the information entered is invalid, the function returns false.
}
