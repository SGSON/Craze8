package comp3350.ppms.presentation.generaluser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.presentation.allusers.CredentialsAdapter;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USER_ID = "userID";
    private static final String PROJECT_ID = "projectID";

    TextView mUserNameTextView;
    Button mSelectUserButton;

    ListView mUserCredListView;
    CredentialsAdapter mCredAdapter;

    ProjectManager mProjectManager;
    Project mProject;

    UserManager mUserManager;
    User mUser;
    String mUserID;
    List<String> mUserCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mUserNameTextView = (TextView) findViewById(R.id.user_name_value);
        mUserCredListView = (ListView) findViewById((R.id.user_credentials));

        mSelectUserButton = (Button) findViewById((R.id.select_button));
        mSelectUserButton.setOnClickListener(this);

        mProjectManager = new ProjectManager();
        mUserManager = new UserManager();

        initiateIntentValues();
        mUserNameTextView.setText(mUser.getUserNickName());

        populateUserCredentialsList();
        setUI();
    }

    private void initiateIntentValues() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mProject = mProjectManager.getProject(bundle.getString(PROJECT_ID));
        mUserID = bundle.getString(USER_ID);
        mUser = mUserManager.getUserByID(mUserID);
    }

    private void populateUserCredentialsList(){
        mUserCredentials = mUserManager.getUserCredentials(mUser);
        mCredAdapter = new CredentialsAdapter(this, mUserCredentials);
        mUserCredListView.setAdapter(mCredAdapter);
    }

    private void selectUser() {
        mProjectManager.addSelectedUser(mProject, mUserID);
        Toast.makeText(this, R.string.success_message, Toast.LENGTH_LONG).show();
        setUI();
    }

    private void setUI() {
        List<String> selectedUsers;

        selectedUsers = mProjectManager.getSelectedUsersForProject(mProject);
        if(selectedUsers.contains(mUserID)) {
            mSelectUserButton.setText(R.string.user_selected);
            mSelectUserButton.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.select_button) {
            selectUser();
        }
    }
}
