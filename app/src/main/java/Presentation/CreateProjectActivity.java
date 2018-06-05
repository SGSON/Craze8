package Presentation;

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

public class CreateProjectActivity extends AppCompatActivity {

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
        mCreateProjectButton = (Button) findViewById(R.id.create_course_button);
        mCancelButton = (Button) findViewById(R.id.cancel_course_button);

        mProjectNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                boolean actionDone = false;
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    mProjectName = textView.getText().toString();
                    actionDone = true;
                }
                return actionDone;
            }
        });

        mCreateProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mProjectNameEdit.getText().toString().equals("") ||
                        mProjectDescriptionEdit.getText().toString().equals("")) {
                    Toast.makeText(CreateProjectActivity.this, R.string.enter_all_fields_prompt,
                            Toast.LENGTH_SHORT).show();
                } else {
                    mProjectName = mProjectNameEdit.getText().toString();
                    mProjectDescr = mProjectDescriptionEdit.getText().toString();

                    /**
                     * TO-DO: Change this
                     * For now just send the new Project info to be added to the DB
                     */

                    //Close the Acivity after the Project is created
                    finish();
                }
            }
        });



    }
}
