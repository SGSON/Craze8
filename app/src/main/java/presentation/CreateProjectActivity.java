package presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.ppms.R;

public class CreateProjectActivity extends AppCompatActivity implements CreateProjectActivityInterface,
        View.OnClickListener, TextView.OnEditorActionListener {

    //For the UI
    private EditText mProjectNameEdit;
    private EditText mProjectDescriptionEdit;
    private Button mCreateProjectButton;
    private Button mCancelButton;


    //For the new Project
    private String mProjectName;
    private String mProjectDescr;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_project_layout);

        mProjectNameEdit = (EditText) findViewById(R.id.enter_project_name);
        mProjectDescriptionEdit = (EditText) findViewById(R.id.project_description);
        mCreateProjectButton = (Button) findViewById(R.id.create_project_button);
        mCancelButton = (Button) findViewById(R.id.cancel_project_button);


        mProjectNameEdit.setOnEditorActionListener(this);

        mCreateProjectButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.create_project_button) {
            if(!createNewProjectFromEditText()) {
                Toast.makeText(CreateProjectActivity.this, R.string.invalid_data, Toast.LENGTH_SHORT).show();
            }
        } else if(view.getId() == R.id.cancel_project_button) {
            finish();
        }

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        boolean actionDone = false;
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            mProjectName = textView.getText().toString();
            actionDone = true;
        }
        return actionDone;
    }

    //Creates a new Project object and calls ProjectManager's processNewProjectRequest method
    //to determine whether the Project object attributes are valid. Returns true if the project is
    // valid and false if it is invalid
    public boolean createNewProjectFromEditText()
    {

        return true;
    }

}
