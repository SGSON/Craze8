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

public class SignUpActivity extends LoginSignUpParent implements View.OnClickListener,
        TextView.OnEditorActionListener{

    private EditText userPasswordEdit;

    private String userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userPasswordEdit = findViewById(R.id.user_password);

    }

    @Override
    public void onClick(View view) {
        User user = createNewUserFromEditText();
        String result;

        result = validateUserData(user);
        if(view.getId() == R.id.create_user_button){
            if(result == null){
                userManagerInsert(user);
                Intent intent = new Intent(this,  CreateProjectActivity.class);
                startActivity(intent);
//TODO: Throw CustomException after fix insertUser
//                try{
//                    userManager.insertUser(user);
//                }catch (CustomException e){
//                    Messages.fatalError(this, e.getErrorMsg());
//                }
            }else{
                Messages.warning(this,result);
            }
        }

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        boolean actionDone = false;
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            setUserNickname(textView.getText().toString());
            actionDone = true;
        }
        return actionDone;
    }

    public User createNewUserFromEditText()
    {
        setUserNickname(getUserNicknameEdit().getText().toString());
        userPassword = userPasswordEdit.getText().toString();

        return new User(getUserNickname(), userPassword);
    }

    private String validateUserData(User user) {

        if (user.getUserNickName().length() == 0) {
            return "user nickname required";
        }
        if (user.getUserPassword().length() == 0){
            return "user password required";
        }
        return null;
    }

}
