package comp3350.ppms.presentation.generaluser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Messages;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.MatchManager;
import comp3350.ppms.logic.MatchManagerInterface;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.logic.UserManagerInterface;


public class UserMatchedProjectsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String USER_NAME = "userName";
    private static final String PROJECT_ID = "projectID";

    private ListView mListView;
    private ProjectAdapter mProjectAdapter;
    private List<Project> matchedProjects;
    private MatchManagerInterface mMatchManager;
    private UserManagerInterface mUserManager;
    private String mUserName;
    private User mCurrAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_matched_projects_list);

        mListView = (ListView) findViewById(R.id.matched_projects_list_view);
        mMatchManager = new MatchManager();
        mUserManager = new UserManager();
        getUserInfo();
        populateMatchedProjectList();
    }

    private void getUserInfo(){
        mUserName = getIntent().getStringExtra(USER_NAME);
        if (mUserName != null) {
            try {
                mCurrAccount = mUserManager.getUser(mUserName);
            }
            catch (CustomException e){
                Messages.warning(this, e.getErrorMsg());
            }
        }
    }

    private void populateMatchedProjectList(){
        matchedProjects = mMatchManager.getMatchedProjectsForUser(mCurrAccount);
        mProjectAdapter = new ProjectAdapter(this, matchedProjects);
        mListView.setAdapter(mProjectAdapter);
        mListView.setOnItemClickListener(this);
    }


    private void navigateToProjectDetail(Project project) {
        final String UserNickName = mCurrAccount.getUserNickName();
        final String ProjectID = project.getProjectID();

        Intent intent = new Intent(this, UserProjectDetailedViewActivity.class);
        intent.putExtra(USER_NAME, UserNickName);
        intent.putExtra(PROJECT_ID, ProjectID);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Project viewProject = matchedProjects.get(position);
        navigateToProjectDetail(viewProject);
    }
}
