package comp3350.ppms.presentation;

import android.content.Context;
import android.content.res.AssetManager;
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

import comp3350.ppms.logic.CustomException;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.UserManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener{

    private EditText userNicknameEdit;
    private EditText userPasswordEdit;

    private Button createUserButton;

    private String userNickname;
    private String userPassword;

    private UserManager userManager;

    private final String USER_ERROR= "Must enter user name";
    private final String PASSWORD_ERROR = "Must enter valid password";
    private final String USER_EXISTS_ERROR = "User name already exists!";

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
        User user = createNewUserFromEditText();
        String result;

        //result = validateUserData(user);
        if(view.getId() == R.id.create_user_button){
            //if(result == null){
                try{
                    userManager.insertUser(user);
                    Intent intent = new Intent(this,  MainActivity.class);
                    intent.putExtra("userName", userNickname);

                    startActivity(intent);
                } catch (CustomException e){
                    Messages.fatalError(this, e.getErrorMsg());
                }
                
            /*}else{
                //Messages.warning(this,result);
                if(result.equals(USER_ERROR) || result.equals(USER_EXISTS_ERROR)){
                    userNicknameEdit.setError(result);
                }else if(result.equals(PASSWORD_ERROR)){
                    userPasswordEdit.setError(result);
                }


            }*/
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

//TODO move this into a different class
  /*  private String validateUserData(User user) {

        String result = null;

        if (user.getUserNickName().length() == 0) {
            result = USER_ERROR;
        }
        else if(userManager.getUser(user.getUserNickName()) != null){
            result =  USER_EXISTS_ERROR;
        }
        else if (user.getUserPassword().length() == 0){
            result = PASSWORD_ERROR;
        }

        return result;
    }

}*/
