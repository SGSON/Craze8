package comp3350.ppms.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test.ppms.R;

import comp3350.ppms.domain.User;
import comp3350.ppms.logic.UserManager;

public abstract class LoginSignUpParent extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener{

    private EditText userNicknameEdit;
    //private EditText userPasswordEdit;

    private Button submitButton;

    private String userNickname;
    //private String userPassword;

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userManager = new UserManager();

        super.onCreate(savedInstanceState);


        userNicknameEdit = findViewById(R.id.user_nickname);
        //userPasswordEdit = findViewById(R.id.user_password);
        submitButton = findViewById(R.id.create_user_button);

//        userNicknameEdit.setOnEditorActionListener(this);
//        submitButton.setOnClickListener(this);

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

//    public User createNewUserFromEditText()
//    {
//        userNickname = userNicknameEdit.getText().toString();
//        //userPassword = userPasswordEdit.getText().toString();
//
//        //return new User(userNickname, userPassword);
//    }

//    private String validateUserData(User user) {
//
//        if (user.getUserNickName().length() == 0) {
//            return "user nickname required";
//        }
//        if (user.getUserPassword().length() == 0){
//            return "user password required";
//        }
//        return null;
//    }

    public void userManagerInsert(User user){
        userManager.insertUser(user);
    }

    public void setUserNickname(String nickname){
        userNickname = nickname;
    }

    public String getUserNickname(){
        return userNickname;
    }

    public EditText getUserNicknameEdit(){
        return userNicknameEdit;
    }
}
