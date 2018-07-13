package comp3350.ppms.presentation.generaluser;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.ppms.R;
import java.util.ArrayList;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.domain.Project;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.domain.User;
import comp3350.ppms.presentation.allusers.Messages;
import comp3350.ppms.presentation.projectowner.InterestedUsersListActivity;


public class UserProjectDetailedViewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USER_NAME = "userName";
    private static final String PROJECT_ID = "projectID";
    private Button interestButton;
    private Button viewIntUsersButton;
    private ProjectManager mProjectManager;
    private ListView mListView;
    private ArrayList<String> mProjectCredentialList;
    private ProjectCredentialAdapter mProjectCredentialAdapter;
    private String projectID;
    private Project project;
    private UserManager userManager;
    private String userNickname;
    private String userID;
    private User currAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detailed_view);

        interestButton = (Button) findViewById(R.id.like_button);
        interestButton.setOnClickListener(this);

        viewIntUsersButton = (Button) findViewById(R.id.view_interested_users_button);
        viewIntUsersButton.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.project_credentials);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle.getSerializable(PROJECT_ID) != null) {


            projectID = bundle.getString(PROJECT_ID);
            userNickname = getIntent().getStringExtra(USER_NAME);

            mProjectManager = new ProjectManager();
            userManager = new UserManager();

            project = mProjectManager.getProject(projectID);
            getUserInfo();

            TextView textView_project_name = (TextView) findViewById(R.id.project_name);
            TextView textView_project_description = (TextView) findViewById(R.id.project_description);

            textView_project_name.setText(mProjectManager.getProjectName(project));
            textView_project_description.setText(mProjectManager.getProjectDescription(project));

            if (currAccount.getLikedProjectIDList().contains(projectID)) //TODO: This is a code smell
            {
                interestButton.setText("Liked");
                interestButton.setEnabled(false);
            }
        }
        populateProjectCredentialList();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.like_button) {
            likeProject(project, currAccount, projectID, userNickname);
            Toast.makeText(this, R.string.success_message, Toast.LENGTH_LONG).show();
        }

        if(v.getId() == R.id.view_interested_users_button) {
            navigateToInterestedUsersList();
        }

    }


    public void getUserInfo(){
        userNickname = getIntent().getStringExtra(USER_NAME);
        if (userNickname != null) {
            try {
                currAccount = userManager.getUser(userNickname);
            }
            catch (CustomException e){
                Messages.warning(this, e.getErrorMsg());
            }
        }
    }

    private void likeProject(Project proj, User user, String projectID, String userNickname) {
        userManager.addProjectToUserInterestedList(user, projectID);
        mProjectManager.addInterestedUser(proj, currAccount.getUserID());
        interestButton.setText("Liked");
        interestButton.setEnabled(false);

    }

    private void navigateToInterestedUsersList() {
        Intent intent = new Intent(UserProjectDetailedViewActivity.this, InterestedUsersListActivity.class);
        intent.putExtra(PROJECT_ID, projectID);
        UserProjectDetailedViewActivity.this.startActivity(intent);
    }

    private void populateProjectCredentialList() {
        //clear the list if this activity has been called before
        if (mProjectCredentialList != null)
            mProjectCredentialList.clear();
        mProjectCredentialList = mProjectManager.getProjectCredentials(project);
        mProjectCredentialAdapter = new ProjectCredentialAdapter(this, mProjectCredentialList);
        mListView.setAdapter(mProjectCredentialAdapter);
        ((ProjectCredentialAdapter) mListView.getAdapter()).notifyDataSetChanged();
    }

    private class ProjectCredentialAdapter extends ArrayAdapter<String> {

        private String mProjectCredential;

        public ProjectCredentialAdapter(Context context, ArrayList<String> projectCredentials) {
            super(context, 0, projectCredentials);
        }


        @Override
        public View getView(int index, View view, ViewGroup parent){

            // Check if an existing view is being reused, otherwise inflate the view
            if(view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_project_credential, parent,
                        false);
            }

            // Get data from project and set up the views
            mProjectCredential = getItem(index);
            TextView textView_project_credential = (TextView)view.findViewById(R.id.project_credential);

            textView_project_credential.setText(mProjectCredential);

            return view;
        }


    }
}