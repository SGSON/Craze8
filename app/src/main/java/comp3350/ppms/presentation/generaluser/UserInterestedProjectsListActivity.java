package comp3350.ppms.presentation.generaluser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;

import com.example.test.ppms.R;

import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.presentation.allusers.Messages;
import comp3350.ppms.presentation.projectowner.CreateProjectActivity;
import comp3350.ppms.presentation.allusers.MainActivity;

public class UserInterestedProjectsListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener  {

    private ProjectManager mProjectManager;
    private ListView mListView;
    private ProjectAdapter mProjectAdapter;
    private List<String> mProjIDList;
    private ArrayList<Project> mProjectList;
    private Button mViewDetailsButton;
    private Button mReturnToPreviousButton;
    private int selectedProjectPosition;

    private UserManager userManager;
    private User currAccount;
    private String userNickname;
    private String currProjectID;
    private Project currProject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_liked_list);
        mListView = (ListView) findViewById(R.id.listProjects);
        mViewDetailsButton = (Button) findViewById(R.id.project_details_button);
        mReturnToPreviousButton = (Button) findViewById(R.id.return_button);
        mViewDetailsButton.setOnClickListener(this);
        mReturnToPreviousButton.setOnClickListener(this);
        mProjectManager = new ProjectManager();
        selectedProjectPosition = -1;
        userManager = new UserManager();

        getUserInfo();
        populateInterestedProjectList();

    }

    public void getUserInfo(){
        userNickname = getIntent().getStringExtra(this.getString(R.string.user_key));
        if (userNickname != null) {
            try {
                currAccount = userManager.getUser(userNickname);
            }
            catch (CustomException e){
                Messages.warning(this, e.getErrorMsg());
            }
        }
    }

    private void populateInterestedProjectList() {
        //TODO - change this

        mProjIDList = currAccount.getLikedProjectIDList();
        mProjectList = new ArrayList<>();
        for (int i=0; i < mProjIDList.size(); i++)
            mProjectList.add(mProjectManager.getProject(mProjIDList.get(i)));
        mProjectAdapter = new ProjectAdapter(this, mProjectList);
        mListView.setAdapter(mProjectAdapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == selectedProjectPosition) {
            deselectListItem(position);
        } else {
            selectListItem(position);
        }
    }

    private void deselectListItem(int position)
    {
        mListView.setItemChecked(position, true);
        mViewDetailsButton.setEnabled(false);
        selectedProjectPosition = -1;
        mListView.clearChoices();
        mProjectAdapter.notifyDataSetChanged();
    }

    private void selectListItem(int position)
    {
        mListView.setItemChecked(position, true);
        mViewDetailsButton.setEnabled(true);
        selectedProjectPosition = position;
        currProject = mProjectList.get(position);
        currProjectID = currProject.getProjectID();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.project_details_button){
            Intent scIntent = new Intent(UserInterestedProjectsListActivity.this, UserProjectDetailedViewActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString(this.getString(R.string.project_id), currProjectID);

            scIntent.putExtra(this.getString(R.string.user_key), userNickname);

            scIntent.putExtras(bundle);
            UserInterestedProjectsListActivity.this.startActivity(scIntent);
        }
        else if (v.getId() == R.id.return_button) {
            Intent scIntent = new Intent(UserInterestedProjectsListActivity.this, MainActivity.class);
            scIntent.putExtra(this.getString(R.string.user_key), userNickname);
            UserInterestedProjectsListActivity.this.startActivity(scIntent);
        }
    }
}
