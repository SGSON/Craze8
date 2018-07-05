package comp3350.ppms.presentation.allusers;

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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.ppms.application.Main;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.UserManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener {

    private static final String USER_NAME = "userName";
    private Button mLoginButton;
    private Button mSignUpButton;
    private String userNickname;
    private EditText userNicknameEdit;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
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
            try{

                User validUser = userManager.loginRequest(userNickname);
                //Pass userName to next Activity and start the intent

                intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(USER_NAME, userNickname);

                startActivity(intent);

            } catch (CustomException e){

                userNicknameEdit.setError(e.getErrorMsg());
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

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";
        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }



}
