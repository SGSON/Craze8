package presentation.ownerview; /**
 * Create Project Activity
 * PURPOSE: Activity that contains the UI for creating a project
 */

import com.example.test.ppms.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import domain.Project;
import logic.ProjectManager;
import logic.CustomException;
import presentation.Messages;
import presentation.ProjectCreateActivityInterface;
import presentation.ProjectListActivity;


public class CreateProjectActivity extends AppCompatActivity implements ProjectCreateActivityInterface,
        View.OnClickListener, TextView.OnEditorActionListener {

    //For the UI
    private EditText mProjectNameEdit;
    private EditText mProjectDescriptionEdit;
    private EditText mProjectCredentialEdit;

    private Button mCreateProjectButton;
    private Button mCancelButton;

    //For the new Project
    private String mProjectName;
    private String mProjectDescr;
    private ArrayList<String> mCredentials;

    private ProjectManager mProjectManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        mProjectManager = new ProjectManager();
        mCredentials = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        mProjectNameEdit = (EditText) findViewById(R.id.project_name);
        mProjectDescriptionEdit = (EditText) findViewById(R.id.project_description);
        mProjectCredentialEdit = (EditText) findViewById(R.id.project_credential);

        mCreateProjectButton = (Button) findViewById(R.id.create_project_button);
        mCancelButton = (Button) findViewById(R.id.cancel_project_button);

        mProjectNameEdit.setOnEditorActionListener(this);

        mCreateProjectButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Project project = createNewProjectFromEditText();
        String result;

        result = validateProjectData(project, true);
        if(view.getId() == R.id.create_project_button){
            if(result == null){
                try{
                    mProjectManager.insertProject(project);
                }catch (CustomException e){
                    Messages.fatalError(this, e.getErrorMsg());
                }
            }else{
                Messages.warning(this,result);
            }
        }else if(view.getId() == R.id.cancel_project_button){
            finish();
        }

    }


    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        boolean actionDone = false;
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            mProjectName = textView.getText().toString();
            actionDone = true;
        }
        return actionDone;
    }

    //Creates a new Project object and calls ProjectManager's processNewProjectRequest method
    //to determine whether the Project object attributes are valid. Returns true if the project is
    // valid and false if it is invalid
    public Project createNewProjectFromEditText()
    {
        //TO-DO add the list of skills to the UI and pass it to the new project being made
        mProjectName = mProjectNameEdit.getText().toString();
        mProjectDescr = mProjectDescriptionEdit.getText().toString();
        mCredentials.add(mProjectCredentialEdit.getText().toString());

        final Project project = new Project(mProjectName, mProjectDescr, mCredentials);

        //ProjectManager.ValidateProject(newProject);

        return project;
    }

    public void viewCreatedProjects(View view){
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }

    private String validateProjectData(Project project, boolean isNewProject) {

        if (project.getName().length() == 0) {
            return "project name required";
        }
        if (project.getDescription().length() == 0){
            return "project description required";
        }
        if (project.getCredentials().size() == 0){
            return "project credential required";
        }
        return null;
    }

}
