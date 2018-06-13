package presentation;

import android.os.Bundle;

import domain.Project;

public interface OwnerProjectCreateActivityInterface {
    void onCreate(Bundle savedInstanceState);

    //Creates a new Project object and calls ProjectManager's processNewProjectRequest method
    //to determine whether the Project object attributes are valid. Returns true if the project is
    // valid and false if it is invalid
    Project createNewProjectFromEditText();
}