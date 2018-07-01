package comp3350.ppms.presentation; /**
 * Create Project Activity
 * PURPOSE: Activity that contains the UI for creating a project
 */

import com.example.test.ppms.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import comp3350.ppms.domain.Project;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.CustomException;


public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener {

    //For the UI
    private EditText projectNameEdit;
    private EditText projectDescriptionEdit;
    private ArrayList<EditText> projectCredentialsEditList;

    private Button createProjectButton;
    private Button cancelButton;
    private Button increaseCredNumButton;
    private Button decreaseCredNumButton;

    //For the new Project
    private String projectName;
    private String projectDescr;
    private ArrayList<String> credentials;

    private LinearLayout credential_layout;

    private ProjectManager projectManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        projectManager = new ProjectManager();
        credentials = new ArrayList<>();
        projectCredentialsEditList = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        projectNameEdit = (EditText) findViewById(R.id.project_name);
        projectDescriptionEdit = (EditText) findViewById(R.id.project_description);
        credential_layout = (LinearLayout) findViewById(R.id.credential_layout);

        //add first credential to credential_layout
        increaseNumCredential();

        createProjectButton = (Button) findViewById(R.id.create_project_button);
        cancelButton = (Button) findViewById(R.id.cancel_project_button);

        createProjectButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        increaseCredNumButton = (Button) findViewById(R.id.increase_credential_button);
        increaseCredNumButton.setOnClickListener(this);

        decreaseCredNumButton = (Button) findViewById(R.id.decrease_credential_button);
        decreaseCredNumButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Project project = createNewProjectFromEditText();
        String result;

        result = validateProjectData(project, true);
        if(view.getId() == R.id.create_project_button) {
            if (result == null) {
                try {
                    projectManager.insertProject(project);
                } catch (CustomException e) {
                    Messages.fatalError(this, e.getErrorMsg());
                }
            } else {
                Messages.warning(this, result);
            }
        }else if(view.getId() == R.id.increase_credential_button) {
            increaseNumCredential();
        }else if(view.getId() == R.id.decrease_credential_button) {
            decreaseNumCredential();
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
        for (int i=0; i < projectCredentialsEditList.size(); i++)
            credentials.add(projectCredentialsEditList.get(i).getText().toString());

        final Project project = new Project(projectName, projectDescr, credentials);
        credentials = new ArrayList<>();
        return project;
    }

    public void decreaseNumCredential()
    {
        //TODO
    }

    public void increaseNumCredential()
    {
        EditText credential = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(24, 0, 24, 8);
        credential.setLayoutParams(params);
        credential.setHint("Please Enter Project Credential");
        credential.setEms(10);
        credential.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        credential.setOnEditorActionListener(this);
        credential_layout.addView(credential);
        projectCredentialsEditList.add(credential);
    }

    public void viewCreatedProjects(View view){
        Intent intent = new Intent(this, ProjectListActivity.class);
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
