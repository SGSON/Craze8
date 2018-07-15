package comp3350.ppms.presentation.projectowner; /**
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
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CredentialError;
import comp3350.ppms.domain.ProjectDescriptionError;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.ProjectNameError;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.presentation.generaluser.ProjectListActivity;
import comp3350.ppms.presentation.allusers.Messages;


public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener {

    private static final String USER_NAME = "userName";
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

    private UserManager userManager;
    private User currAccount;
    private String userNickname;

    private final int credentialLeftMargin = 24;
    private final int credentialRightMargin = 24;
    private final int credentialTopMargin = 0;
    private final int credentialBottomMargin = 0;

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

        projectNameEdit.setOnEditorActionListener(this);

        createProjectButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        increaseCredNumButton = (Button) findViewById(R.id.increase_credential_button);
        increaseCredNumButton.setOnClickListener(this);

        decreaseCredNumButton = (Button) findViewById(R.id.decrease_credential_button);
        decreaseCredNumButton.setOnClickListener(this);
        decreaseCredNumButton.setEnabled(false);

        getUserInfo();

    }

    @Override
    public void onClick(View view) {
        Project project = createNewProjectFromEditText();

        if(view.getId() == R.id.create_project_button) {
            try {
                projectManager.insertProject(project);
                currAccount.addToCreatedProjectIDList(project.getProjectID());
                Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
            } catch (CustomException e){
                //Messages.fatalError(this, e.getErrorMsg());

                if(e instanceof ProjectNameError){
                    projectNameEdit.setError(e.getErrorMsg());
                }else if(e instanceof ProjectDescriptionError){
                    projectDescriptionEdit.setError(e.getErrorMsg());
                }else if(e instanceof CredentialError){
                    projectCredentialsEditList.get(0).setError(e.getErrorMsg());
                }
            }

        }else if(view.getId() == R.id.increase_credential_button) {
            increaseNumCredential();
            Toast.makeText(this, "Add Credential", Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.decrease_credential_button) {
            decreaseNumCredential();
            Toast.makeText(this, "Delete Credential", Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.cancel_project_button){
            finish();
        }else{

        }



    }

    public void getUserInfo(){
        userManager = new UserManager();
        userNickname = getIntent().getStringExtra(USER_NAME);
        if (userNickname != null) {
            try {
                currAccount = userManager.getUser(userNickname);
            }
            catch (CustomException e) {
                Messages.warning(this, e.getErrorMsg());
            }
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

        final Project project = new Project(projectName, currAccount.getUserID(), projectDescr, credentials);
        credentials = new ArrayList<>();
        return project;
    }

    public void decreaseNumCredential()
    {
        int index = projectCredentialsEditList.size() - 1;
        if (index > 0)
        {
            EditText credToDelete = (EditText)projectCredentialsEditList.remove(index);
            credential_layout.removeView(credToDelete);
        }
        if (projectCredentialsEditList.size() == 1)
            decreaseCredNumButton.setEnabled(false);
    }

    public void increaseNumCredential()
    {
        EditText credential = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(credentialLeftMargin, credentialTopMargin, credentialRightMargin, credentialBottomMargin);
        credential.setLayoutParams(params);
        credential.setHint(R.string.project_credential_input);
        credential.setEms(10);
        credential.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        credential.setOnEditorActionListener(this);
        credential_layout.addView(credential);
        projectCredentialsEditList.add(credential);
        if (projectCredentialsEditList.size() == 2 && !decreaseCredNumButton.isEnabled())
            decreaseCredNumButton.setEnabled(true);
    }

    public void viewCreatedProjects(View view){
        Intent intent = new Intent(this, ProjectListActivity.class);

        intent.putExtra(this.getString(R.string.user_key), userNickname);

        startActivity(intent);
    }


}
