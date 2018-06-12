package presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.ppms.R;

import domain.Project;
import logic.ProjectManager;

import java.util.List;
import java.util.ArrayList;

public class ProjectListActivity extends AppCompatActivity {

    private ProjectManager projectManager;
    private ArrayList<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        projectManager = new ProjectManager();
        projectList = projectManager.getProjects();

        ListView listView = (ListView)findViewById(R.id.listProjects);

        ProjectAdapter projectAdapter = new ProjectAdapter(this, projectList);

        listView.setAdapter(projectAdapter);

    }

    private class ProjectAdapter extends ArrayAdapter{

        public ProjectAdapter(Context context, ArrayList<Project> projects) {
            super(context, 0, projects);
        }


        @Override
        public int getCount(){
            return projectList.size();
        }

        @Override
        public Project getItem(int i){
            return null;
        }

        @Override
        public View getView(int index, View view, ViewGroup parent){
            view = getLayoutInflater().inflate(R.layout.activity_project_list, null);
//            view = LayoutInflater.from(getContext()).inflate(R.layout.item_project, parent, false);
            TextView textView_project_name = (TextView)view.findViewById(R.id.project_name);
            TextView textView_project_description = (TextView)view.findViewById(R.id.project_description);

            textView_project_name.setText(projectList.get(index).getName());
            textView_project_description.setText(projectList.get(index).getDescription());

            return null;
        }

    }
}
