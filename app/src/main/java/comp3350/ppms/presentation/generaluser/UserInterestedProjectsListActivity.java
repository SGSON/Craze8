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
import comp3350.ppms.domain.Messages;
import comp3350.ppms.presentation.allusers.MainActivity;

public class UserInterestedProjectsListActivity extends ProjectListActivity implements View.OnClickListener, AdapterView.OnItemClickListener  {

    private ProjectManager mProjectManager;
    private List<String> mProjIDList;
    private List<Project> mProjectList;
    private int selectedProjectPosition;
    private String userNickname;
    private Button mReturnToPreviousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProjectManager = new ProjectManager();
        selectedProjectPosition = -1;
        mReturnToPreviousButton = (Button) findViewById(R.id.return_button);
        mReturnToPreviousButton.setText(R.string.return_to_main);
        mReturnToPreviousButton.setOnClickListener(this);
        userNickname = getIntent().getStringExtra(this.getString(R.string.user_key));
        getUserInfo(userNickname);
        populateInterestedProjectList();

    }

    private void populateInterestedProjectList() {
        mProjIDList = getCurrAccount().getLikedProjectIDList();
        mProjectList = new ArrayList<>();
        for (int i=0; i < mProjIDList.size(); i++)
            mProjectList.add(mProjectManager.getProject(mProjIDList.get(i)));
        populateProjectList(mProjectList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == selectedProjectPosition) {
            selectedProjectPosition = deselectListItem(position);
        } else {
            selectedProjectPosition = selectListItem(position, mProjectList);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.project_details_button){
            Intent scIntent = new Intent(UserInterestedProjectsListActivity.this, UserProjectDetailedViewActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString(this.getString(R.string.project_id), getCurrProjectID());

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
