package comp3350.ppms.presentation.projectowner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test.ppms.R;

import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.presentation.generaluser.UserAdapter;
import comp3350.ppms.presentation.generaluser.UserInfoActivity;

public class InterestedUsersListActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private static final String USER_ID = "userID";
    private static final String PROJECT_ID = "projectID";

    private ListView mListView;
    private UserAdapter mUserAdapter;
    private List<User> interestedUsers;

    private ProjectManager mProjectManager;
    private Project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_users_list);

        mListView = (ListView) findViewById(R.id.interestedUsersList);
        mProjectManager = new ProjectManager();

        getIntentValues();
        populateInterestedUsersList();

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle.get(PROJECT_ID) != null) {
            mProject = mProjectManager.getProject(bundle.getString(PROJECT_ID));
        }
    }

    private void populateInterestedUsersList() {
        interestedUsers = mProjectManager.getInterestedUsers(mProject);
        mUserAdapter = new UserAdapter(this, interestedUsers);
        mListView.setAdapter(mUserAdapter);
        mListView.setOnItemClickListener(this);

    }

    private void viewUserInfo(int position) {
        User user = interestedUsers.get(position);
        final String UserID = user.getUserID();
        final String ProjectID = mProject.getProjectID();

        Intent intent = new Intent(InterestedUsersListActivity.this, UserInfoActivity.class);
        intent.putExtra(USER_ID, UserID);
        intent.putExtra(PROJECT_ID, ProjectID);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        viewUserInfo(position);
    }
}
