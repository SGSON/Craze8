package comp3350.ppms.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test.ppms.R;

public class LoginActivity extends LoginSignUpParent {

    private Button mLoginButton;
    private Button mSignUpButton;
    private String username;
    private EditText userNicknameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void onClick(View view){
        if(view.getId() == R.id.submit_button){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        //username =
    }

}
