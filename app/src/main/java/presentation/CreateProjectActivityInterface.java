package presentation;

        import android.os.Bundle;

public interface CreateProjectActivityInterface {
    void onCreate(Bundle savedInstanceState);
    Boolean createNewProjectRequestFromEditText(); //creates a new NewProjectRequest object
}