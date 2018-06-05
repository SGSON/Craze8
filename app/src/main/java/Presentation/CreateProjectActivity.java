package Presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.test.ppms.R;

public class CreateProjectActivity extends AppCompatActivity {

    private EditText mProjectNameEdit;
    private EditText mProjectDescriptionEdit;
    private Button mCreateProjectButton;
    private Button mCancelButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_project_layout);

        mProjectNameEdit = (EditText) findViewById(R.id.enter_project_name);
        mProjectDescriptionEdit = (EditText) findViewById(R.id.project_description);
        mCreateProjectButton = (Button) findViewById(R.id.create_course_button);
        mCancelButton = (Button) findViewById(R.id.cancel_course_button);

    }
}
