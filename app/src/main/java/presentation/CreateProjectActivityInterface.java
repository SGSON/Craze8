package presentation;

import android.os.Bundle;

public interface CreateProjectActivityInterface {
    void onCreate(Bundle savedInstanceState);

    //Creates a new Project object and calls ProjectManager's processNewProjectRequest method
    //to determine whether the Project object attributes are valid. Returns true if the project is
    // valid and false if it is invalid
    Boolean createNewProjectFromEditText();
}