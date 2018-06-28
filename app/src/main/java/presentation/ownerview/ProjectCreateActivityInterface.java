package presentation.ownerview;

import android.os.Bundle;

import domain.Project;

public interface ProjectCreateActivityInterface {
    void onCreate(Bundle savedInstanceState);

    //Creates a new Project object and returns it.
    Project createNewProjectFromEditText();
}