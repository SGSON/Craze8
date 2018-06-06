package presentation;

        import android.os.Bundle;

public interface CreateProjectActivityInterface {
    void onCreate(Bundle savedInstanceState);
    bool createNewProjectRequestFromEditText(); //creates a new NewProjectRequest object
}