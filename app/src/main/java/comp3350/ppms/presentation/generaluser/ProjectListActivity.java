package comp3350.ppms.presentation.generaluser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.AdapterView;
import android.view.View;
import android.widget.TextView;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.test.ppms.R;

import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.logic.CustomException;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.domain.User;
import comp3350.ppms.presentation.allusers.Messages;
import comp3350.ppms.presentation.projectowner.CreateProjectActivity;

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String USER_NAME = "userName";
    private static final String PROJECT_ID = "projectID";
    private ProjectManager mProjectManager;
    private ListView mListView;
    private ProjectAdapter mProjectAdapter;
    private List<Project> mProjectList;
    private Button mViewDetailsButton;
    private Button mReturnToPreviousButton;
    private String currProjectID;
    private int selectedProjectPosition;

    private UserManager userManager;
    private User currAccount;
    private String userNickname;
    private Project currProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        mListView = (ListView) findViewById(R.id.listProjects);
        mViewDetailsButton = (Button) findViewById(R.id.project_details_button);
        mReturnToPreviousButton = (Button) findViewById(R.id.return_button);
        mViewDetailsButton.setOnClickListener(this);
        mReturnToPreviousButton.setOnClickListener(this);
        mProjectManager = new ProjectManager();
        populateProjectList();
        selectedProjectPosition = -1;

        userManager = new UserManager();

        getUserInfo();

    }

    public void getUserInfo(){
        userNickname = getIntent().getStringExtra(USER_NAME);
        if (userNickname != null) {
            try {
                currAccount = userManager.getUser(userNickname);
            }
            catch (CustomException e){
                Messages.warning(this, e.getErrorMsg());
            }
        }
    }

    private void populateProjectList() {
        mProjectList = mProjectManager.getProjects();
        mProjectAdapter = new ProjectAdapter(this, mProjectList);
        mListView.setAdapter(mProjectAdapter);
        mListView.setOnItemClickListener(this);

    }

    public Dialog onCreateDialog() {
        final CharSequence[] dialogList = (currProject.getProjectCredentials())
                                            .toArray(new CharSequence[(currProject.getProjectCredentials()).size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.credentials_label)
                .setItems(dialogList, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == selectedProjectPosition) {
            deselectListItem(position);
        } else {
            selectListItem(position);
            onCreateDialog().show();
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
            Intent scIntent = new Intent(ProjectListActivity.this, UserProjectDetailedViewActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString(PROJECT_ID, currProjectID);

            scIntent.putExtra(USER_NAME, userNickname);

            scIntent.putExtras(bundle);
            ProjectListActivity.this.startActivity(scIntent);
        }
        else if (v.getId() == R.id.return_button) {
            Intent scIntent = new Intent(ProjectListActivity.this, CreateProjectActivity.class);
            scIntent.putExtra(USER_NAME, userNickname);
            ProjectListActivity.this.startActivity(scIntent);
        }
    }

    private class ProjectAdapter extends ArrayAdapter<Project> {

        private Project project;

        private ProjectAdapter(Context context, List<Project> projects) {
            super(context, 0, projects);
        }


        @Override
        public View getView(int index, View view, ViewGroup parent){

            // Check if an existing view is being reused, otherwise inflate the view
            if(view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_project, parent,
                        false);
            }

            // Get data from project and set up the views
            project = getItem(index);
            TextView textView_project_name = (TextView) view.findViewById(R.id.project_list_item_name);
            TextView textView_project_description = (TextView) view.findViewById(R.id.project_list_item_description);

            textView_project_name.setText(project.getProjectName());
            textView_project_description.setText(project.getProjectDescription());

            return view;
        }
    }

}
