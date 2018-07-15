package comp3350.ppms.presentation.projectowner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.MatchManager;
import comp3350.ppms.logic.MatchManagerInterface;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.ProjectManagerInterface;
import comp3350.ppms.presentation.generaluser.UserAdapter;

public class MatchedUsersListActivity extends UserListActivity
        implements AdapterView.OnItemClickListener{

    private List<User> matchedUsers;
    private MatchManagerInterface matchManager;

    private Project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        matchManager = new MatchManager();
        mProject = initiateProjectFromIntent();
        populateMatchedUsersList();
    }

    private void populateMatchedUsersList() {
        matchedUsers = matchManager.getMatchedUsersForProject(mProject);
        populateUserList(matchedUsers);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = matchedUsers.get(position);
        navigateToUserInfo(user);
    }
}
