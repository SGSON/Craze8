package comp3350.ppms.presentation.generaluser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.test.ppms.R;

import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.domain.Project;

public class ProjectAdapter extends ArrayAdapter<Project> {

    private Project project;

    public ProjectAdapter(Context context, List<Project> projects) {
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