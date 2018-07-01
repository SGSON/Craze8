package comp3350.ppms.presentation;

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
import comp3350.ppms.domain.User;

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
        userManager.insertUser(new User("test","fart"));
        Intent intent;
        if(view.getId() == R.id.login_button){
            intent = new Intent(LoginActivity.this, MainActivity.class);
            userNickname = userNicknameEdit.getText().toString();
            getUserIDFromDB(userNickname);
            //if it's valid, start the next activity

            User validUser = userManager.getUserByString(userNickname);

            if (validUser != null){
                startActivity(intent);
                System.out.println("VALID USERNAME");
            }
            System.out.println("THIS IS A TEST " + validUser);

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

    public void getUserIDFromDB(String userNickname){
        //talk to the database and get the valid userID if there is one

    }

}
