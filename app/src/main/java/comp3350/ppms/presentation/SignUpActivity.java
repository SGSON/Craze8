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


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.ppms.application.Main;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userManager = new UserManager();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        copyDatabaseToDevice();

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

        result = validateUserData(user);
        if(view.getId() == R.id.create_user_button){
            if(result == null){
                userManager.insertUser(user);
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
