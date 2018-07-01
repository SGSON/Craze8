package comp3350.ppms.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.example.test.ppms.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener {

//    private Button mLoginButton;
    private Button mSignUpButton;
//    private String username;
//    private EditText userNicknameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSignUpButton = (Button)findViewById(R.id.signup_button);
        mSignUpButton.setOnClickListener(this);
    }


    public void onClick(View view){
        Intent intent;
        if(view.getId() == R.id.createUser_button){
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.signup_button){
            intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }

        //username =
    }

    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        boolean actionDone = false;
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            //userNickname = textView.getText().toString();
            actionDone = true;
        }
        return actionDone;
    }

}
