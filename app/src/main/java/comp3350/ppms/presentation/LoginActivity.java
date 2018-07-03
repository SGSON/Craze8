package comp3350.ppms.presentation;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.test.ppms.R;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import comp3350.ppms.domain.User;
import comp3350.ppms.logic.UserManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener {

    private Button mLoginButton;
    private Button mSignUpButton;
    private String userNickname;
    private EditText userNicknameEdit;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
        setContentView(R.layout.activity_login);

        //setup elements
        mLoginButton = (Button)findViewById(R.id.login_button);
        mSignUpButton = (Button)findViewById(R.id.signup_button);
        userNicknameEdit = (EditText) findViewById(R.id.user_nickname);

        //setup listeners
        mSignUpButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        userNicknameEdit.setOnEditorActionListener(this);
    }


    public void onClick(View view){

            //Will be used for the next Activity we go to
        Intent intent;

        //If it's the login button being pressed, get the username entered, validate
        if(view.getId() == R.id.login_button){

            userNickname = userNicknameEdit.getText().toString();


            //Validate account name (will be a valid User or null)
            User validUser = userManager.validateUserName(userNickname);

            //Pass userName to next Activity and start the intent
            if (validUser != null){
                intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userName", userNickname);

                startActivity(intent);
            }else{
                userNicknameEdit.setError("Invalid Account Name");
            }


        }else if(view.getId() == R.id.signup_button){
            intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }



    }

    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        boolean actionDone = false;
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            userNickname = textView.getText().toString();
            actionDone = true;
        }
        return actionDone;
    }



}
