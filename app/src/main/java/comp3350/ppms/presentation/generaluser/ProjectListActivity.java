package comp3350.ppms.presentation.generaluser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.example.test.ppms.R;

import java.util.List;

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.presentation.allusers.Messages;

public abstract class ProjectListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mListView;
    private ProjectAdapter mProjectAdapter;
    private String currProjectID;
    private Project currProject;
    private Button mViewDetailsButton;
    private User currAccount;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project_list);
        mListView = (ListView) findViewById(R.id.listProjects);
        mViewDetailsButton = (Button) findViewById(R.id.project_details_button);
        mViewDetailsButton.setOnClickListener(this);

        userManager = new UserManager();
    }

    public void getUserInfo(String userNickname){
        if (userNickname != null) {
            try {
                currAccount = userManager.getUser(userNickname);
            }
            catch (CustomException e){
                Messages.warning(this, e.getErrorMsg());
            }
        }
    }

    protected String getCurrProjectID() {
        return currProjectID;
    }

    protected User getCurrAccount() {
        return currAccount;
    }

    protected int deselectListItem(int position)
    {
        mListView.setItemChecked(position, true);
        mViewDetailsButton.setEnabled(false);
        mListView.clearChoices();
        mProjectAdapter.notifyDataSetChanged();
        return -1;
    }

    protected int selectListItem(int position, List<Project> mProjectList)
    {
        mListView.setItemChecked(position, true);
        mViewDetailsButton.setEnabled(true);
        currProject = mProjectList.get(position);
        currProjectID = currProject.getProjectID();
        return position;
    }

    protected void populateProjectList(List<Project> mProjectList) {
        mProjectAdapter = new ProjectAdapter(this, mProjectList);
        mListView.setAdapter(mProjectAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}

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

}
