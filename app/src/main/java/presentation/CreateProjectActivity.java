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

public class CreateProjectActivity extends AppCompatActivity implements CreateProjectActivityInterface {

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


        /**
         * This chunk of code tells Android Studio what we want a button or some UI element
         * to do once it has been clicked or some type of action. There is two ways of doing this
         * I chose to do it this way because this is what I used in my previous android app.
         * The other way to do it is to have the code outside of this OnCreate method
         * I noticed that in the sample Project he gave us they do it in the way where the code
         * telling Android studio what to do is outside of this OnCreate method. I plan to go and see
         * him tomorrow if they would prefer we do it that way. - Buhle
         */
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
                     * TO-DO: Send the Project Information to Other Layers for further processing
                     */



                    //Close the Acivity after the Project is created
                    finish();
                }
            }
        });

        //If Cancel Button is hit, close the activity
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

    }

    String getmProjectName() //this is called by business logic to get project name(?)
    {
        return mProjectName;
    }
    String getmProjectDescr(); //this is called by business logic to get project name(?)
    {
        return mProjectDescr;
    }

}
