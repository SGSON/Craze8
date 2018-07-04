package comp3350.ppms.presentation;

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

import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.domain.Project;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.domain.User;


public class UserProjectDetailedViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAllProjectsButton;
    private Button interestButton;
    private ProjectManager mProjectManager;
    private ListView mListView;
    private ArrayList<String> mProjectCredentialList;
    private ProjectCredentialAdapter mProjectCredentialAdapter;
    private String projectID;
    private Project project;
    private UserManager userManager;
    private String userNickname;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detailed_view);

        interestButton = (Button) findViewById(R.id.like_button);
        interestButton.setOnClickListener(this);

        mAllProjectsButton = (Button) findViewById(R.id.all_projects_button);
        mAllProjectsButton.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.project_credentials);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle.getSerializable("projectID") != null) {


            projectID = bundle.getString("projectID");
            userNickname = getIntent().getStringExtra("userName");

            mProjectManager = new ProjectManager();
            userManager = new UserManager();

            project = mProjectManager.getProject(projectID);
            user = userManager.validateUserName(userNickname);

            TextView textView_project_name = (TextView) findViewById(R.id.project_name);
            TextView textView_project_description = (TextView) findViewById(R.id.project_description);

            textView_project_name.setText(project.getProjectName());
            textView_project_description.setText(project.getProjectDescription());
        }
        populateProjectCredentialList();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.like_button) {
            user.addToLikedProjectIDList(projectID);
            //TODO move this implementation to the logic layer
            project.addInterestedUser(user.getUserID());
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        }

        Intent scIntent = new Intent(UserProjectDetailedViewActivity.this, ProjectListActivity.class);

        scIntent.putExtra("userName", userNickname);

        UserProjectDetailedViewActivity.this.startActivity(scIntent);
    }

    private void populateProjectCredentialList() {
        //clear the list if this activity has been called before
        if (mProjectCredentialList != null)
            mProjectCredentialList.clear();
        mProjectCredentialList = mProjectManager.getProject(projectID).getProjectCredentials();
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