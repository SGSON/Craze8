package comp3350.ppms.presentation.projectowner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.test.ppms.R;

import java.util.ArrayList;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.ProjectManager;

public class InterestedUsersListActivity extends AppCompatActivity {

    private static final String PROJECT_ID = "projectID";

    private ListView mListView;
    private ArrayList<User> interestedUsers;

    private ProjectManager mProjectManager;
    private Project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_users_list);

        mListView = (ListView) findViewById(R.id.interestedUsersList);
        mProjectManager = new ProjectManager();

        getIntentValues();

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle.get(PROJECT_ID) != null) {
            mProject = mProjectManager.getProject(bundle.getString(PROJECT_ID));
        }
    }

    private void populateInterestedUsersList() {
        interestedUsers =(ArrayList<User>) mProjectManager.getInterestedUsers(mProject);
    }
}
