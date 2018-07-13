package comp3350.ppms.presentation.projectowner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.ProjectManager;

public class InterestedUsersListActivity extends UserListActivity
        implements AdapterView.OnItemClickListener {

    private List<User> interestedUsers;

    private ProjectManager mProjectManager;
    private Project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProjectManager = new ProjectManager();
        mProject = initiateProjectFromIntent();
        populateInterestedUsersList();

    }

    private void populateInterestedUsersList() {
        interestedUsers = mProjectManager.getInterestedUsers(mProject);
        populateUserList(interestedUsers);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = interestedUsers.get(position);
        navigateToUserInfo(user);
    }
}
