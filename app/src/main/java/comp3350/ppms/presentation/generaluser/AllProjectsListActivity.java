package comp3350.ppms.presentation.generaluser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.AdapterView;
import android.view.View;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.presentation.projectowner.CreateProjectActivity;

public class AllProjectsListActivity extends ProjectListActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private int selectedProjectPosition;
    private ProjectManager mProjectManager;
    private Button mReturnToPreviousButton;
    private String userNickname;
    private List<Project> mProjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProjectManager = new ProjectManager();
        selectedProjectPosition = -1;
        mReturnToPreviousButton = (Button) findViewById(R.id.return_button);
        mReturnToPreviousButton.setText(R.string.project_creator);
        mReturnToPreviousButton.setOnClickListener(this);
        userNickname = getIntent().getStringExtra(this.getString(R.string.user_key));
        getUserInfo(userNickname);
        populateAllProjectList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == selectedProjectPosition) {
            selectedProjectPosition = deselectListItem(position);
        } else {
            selectedProjectPosition = selectListItem(position, mProjectList);
            onCreateDialog().show();
        }
    }

    private void populateAllProjectList()
    {
        mProjectList = mProjectManager.getProjects();
        populateProjectList(mProjectList);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.project_details_button){
            Intent scIntent = new Intent(AllProjectsListActivity.this, UserProjectDetailedViewActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString(this.getString(R.string.project_id), getCurrProjectID());

            scIntent.putExtra(this.getString(R.string.user_key), userNickname);

            scIntent.putExtras(bundle);
            AllProjectsListActivity.this.startActivity(scIntent);
        }
        else if (v.getId() == R.id.return_button) {
            Intent scIntent = new Intent(AllProjectsListActivity.this, CreateProjectActivity.class);
            scIntent.putExtra(this.getString(R.string.user_key), userNickname);
            AllProjectsListActivity.this.startActivity(scIntent);
        }

    }
}
