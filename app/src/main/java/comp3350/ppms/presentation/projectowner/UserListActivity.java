package comp3350.ppms.presentation.projectowner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.presentation.generaluser.UserAdapter;
import comp3350.ppms.presentation.generaluser.UserInfoActivity;

public abstract class UserListActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    private static final String USER_ID = "userID";
    private static final String PROJECT_ID = "projectID";

    private ListView mListView;
    private UserAdapter mUserAdapter;

    private ProjectManager mProjectManager;
    private Project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_users_list);

        mListView = (ListView) findViewById(R.id.interestedUsersList);
        mProjectManager = new ProjectManager();
    }

    protected Project initiateProjectFromIntent() {
        Project projectFromIntent = null;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle.get(PROJECT_ID) != null) {
            mProject = mProjectManager.getProject(bundle.getString(PROJECT_ID));
        }

        return mProject;
    }

    protected void populateUserList(List<User> userList) {
        mUserAdapter = new UserAdapter(this, userList);
        mListView.setAdapter(mUserAdapter);
        mListView.setOnItemClickListener(this);

    }

    protected void navigateToUserInfo(User user) {

        final String UserID = user.getUserID();
        final String ProjectID = mProject.getProjectID();

        Intent intent = new Intent(this, UserInfoActivity.class);
        intent.putExtra(USER_ID, UserID);
        intent.putExtra(PROJECT_ID, ProjectID);
        startActivity(intent);
    }

    @Override
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
