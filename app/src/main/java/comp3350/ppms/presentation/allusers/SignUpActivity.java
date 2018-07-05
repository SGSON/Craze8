package comp3350.ppms.presentation.allusers;

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

import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.PasswordError;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.domain.UsernameError;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener{

    private static final String USER_NAME = "userName";
    private EditText userNicknameEdit;
    private EditText userPasswordEdit;

    private Button createUserButton;

    private String userNickname;
    private String userPassword;

    private UserManager userManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userManager = new UserManager();

        userNicknameEdit = (EditText) findViewById(R.id.user_nickname);
        userPasswordEdit = (EditText) findViewById(R.id.user_password);
        createUserButton = (Button) findViewById(R.id.create_user_button);

        userNicknameEdit.setOnEditorActionListener(this);
        createUserButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        User user = createNewUserFromEditText();;

        if(view.getId() == R.id.create_user_button){

            try{

                userManager.insertUser(user);
                Intent intent = new Intent(this,  MainActivity.class);
                intent.putExtra(USER_NAME, userNickname);

                startActivity(intent);
            } catch (CustomException e){
                if(e instanceof UsernameError) {
                    userNicknameEdit.setError(e.getErrorMsg());
                }else if(e instanceof PasswordError){
                    userPasswordEdit.setError(e.getErrorMsg());
                }

            }

        }

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        boolean actionDone = false;
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            userNickname = textView.getText().toString();
            actionDone = true;
        }
        return actionDone;
    }

    public User createNewUserFromEditText()
    {
        userNickname = userNicknameEdit.getText().toString();
        userPassword = userPasswordEdit.getText().toString();

        return new User(userNickname, userPassword);
    }


}
