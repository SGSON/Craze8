package comp3350.ppms.presentation.generaluser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.test.ppms.R;

import comp3350.ppms.logic.MatchManager;
import comp3350.ppms.logic.MatchManagerInterface;

public class UserMatchedProjectsListActivity extends AppCompatActivity {

    private static final String USER_NAME = "userName";

    private MatchManagerInterface mMatchManager;
    private ListView mListView;
    private ProjectAdapter mProjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_matched_projects_list);

        mListView = (ListView) findViewById(R.id.matched_projects_list_view);
    }
}
