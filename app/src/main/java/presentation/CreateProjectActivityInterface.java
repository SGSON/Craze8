package presentation;

        import android.os.Bundle;

public interface CreateProjectActivityInterface {
    void onCreate(Bundle savedInstanceState);
    String getmProjectName(); //this is called by business logic to get project name(?)
    String getmProjectDescr(); //this is called by business logic to get project name(?)
}