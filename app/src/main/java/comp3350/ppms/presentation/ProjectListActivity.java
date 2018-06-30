package comp3350.ppms.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Button;
import android.widget.AdapterView;

import android.view.View;

import com.example.test.ppms.R;

import comp3350.ppms.domain.Project;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.presentation.ProjectAdapter;

import java.util.ArrayList;

public class ProjectListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ProjectManager mProjectManager;
    private ListView mListView;
    private ProjectAdapter mProjectAdapter;
    private ArrayList<Project> mProjectList;
    private Button mViewDetailsButton;
    private int selectedProjectPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        mListView = (ListView)findViewById(R.id.listProjects);
        mViewDetailsButton = findViewById(R.id.project_details_button);
        mProjectManager = new ProjectManager();
        populateProjectList();


    }

    private void populateProjectList() {
        mProjectList = mProjectManager.getProjects();
        mProjectAdapter = new ProjectAdapter(this, mProjectList);
        mListView.setAdapter(mProjectAdapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == selectedProjectPosition) {
            mListView.setItemChecked(position, true);
            mViewDetailsButton.setEnabled(false);
            selectedProjectPosition = -1;
            mListView.clearChoices();
            mProjectAdapter.notifyDataSetChanged();
        } else {
            mListView.setItemChecked(position, true);
            mViewDetailsButton.setEnabled(true);
            selectedProjectPosition = position;
        }
    }
}
