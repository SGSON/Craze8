package comp3350.ppms.presentation; /**
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.CustomException;
import comp3350.ppms.logic.UserManager;


public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener {

    //For the UI
    private EditText projectNameEdit;
    private EditText projectDescriptionEdit;
    private EditText projectCredentialEdit;

    private Button createProjectButton;
    private Button cancelButton;

    //For the new Project
    private String projectName;
    private String projectDescr;
    private ArrayList<String> credentials;

    private ProjectManager projectManager;

    private UserManager userManager;
    private User user;
    private UUID userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        projectManager = new ProjectManager();
        credentials = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        projectNameEdit = (EditText) findViewById(R.id.project_name);
        projectDescriptionEdit = (EditText) findViewById(R.id.project_description);
        projectCredentialEdit = (EditText) findViewById(R.id.project_credential);

        createProjectButton = (Button) findViewById(R.id.create_project_button);
        cancelButton = (Button) findViewById(R.id.cancel_project_button);

        projectNameEdit.setOnEditorActionListener(this);

        createProjectButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle.getSerializable("userID") != null){
            userId = (UUID)bundle.getSerializable("userID");
            userManager = new UserManager();
            user = userManager.getUser(userId);
        }
    }

    @Override
    public void onClick(View view) {
        Project project = createNewProjectFromEditText();
        String result;

        result = validateProjectData(project, true);
        if(view.getId() == R.id.create_project_button){
            if(result == null){
                try{
                    projectManager.insertProject(project);
                    user.addToCreatedProjectIDList(project.getProjectID());
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
            projectName = textView.getText().toString();
            actionDone = true;
        }
        return actionDone;
    }

    //Creates a new Project object and returns it.
    public Project createNewProjectFromEditText()
    {
        projectName = projectNameEdit.getText().toString();
        projectDescr = projectDescriptionEdit.getText().toString();
        credentials.add(projectCredentialEdit.getText().toString());

        final Project project = new Project(projectName, projectDescr, credentials);
        credentials = new ArrayList<>();
        return project;
    }

    public void viewCreatedProjects(View view){
        Intent intent = new Intent(this, ProjectListActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("userID", userId);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private String validateProjectData(Project project, boolean isNewProject) {

        if (project.getProjectName().length() == 0) {
            return "project name required";
        }
        if (project.getProjectDescription().length() == 0){
            return "project description required";
        }
        if (project.getProjectCredentials().size() == 0){
            return "project credential required";
        }
        return null;
    }

}
