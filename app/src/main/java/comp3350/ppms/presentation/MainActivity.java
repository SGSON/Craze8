package comp3350.ppms.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import comp3350.ppms.logic.UserManager;
import com.example.test.ppms.R;
import comp3350.ppms.domain.User;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mCreateProjectButton;
    private Button mViewProjectsButton;
    private User currAccount;
    private String userNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //______________//
        //Splash Screen
        //______________//
        // The short explanation:
        // Replace the splash screen image to change the splash screen
        //
        // Place-holder Image being used: res/drawable/splash_screen.png
        // Place holder image taken from:
        // https://www.insperity.com/blog/managers-guide-matching-employees-right-mentors/
        //
        // The long explanation
        // Sets a theme to AppTheme (that is <style name="AppTheme.Launcher"> in styles.xml)
        // before/while the app is loading. Once the app loads, it loads the default theme (which is
        // our actual theme, not the splash screen theme).
        // The splash screen that is being displayed is splash_screen_xml (in res/drawable), which
        // contains a call for the actual image to display which is found res/drawable/splash_screen.png
        // It needs to be displayed as an xml file because you need be able to set the fill type for
        // the image, otherwise(without the xml file) it just stretches the image in an ugly way
        //
        // Splash screen solution found here:
        // https://plus.google.com/+AndroidDevelopers/posts/Z1Wwainpjhd
        setTheme(R.style.AppTheme);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateProjectButton = (Button) findViewById(R.id.create_project_button);
        mViewProjectsButton = (Button) findViewById(R.id.view_projects_button);

        mCreateProjectButton.setOnClickListener(this);
        mViewProjectsButton.setOnClickListener(this);

        UserManager userManager = new UserManager();

        //get the username from the last intent (login)
        userNickname = getIntent().getStringExtra("userName");

        //set the account
        if(userNickname != null){
            currAccount = userManager.getUser(userNickname);
        }


    }


    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId() == R.id.create_project_button) {
            intent = new Intent(MainActivity.this, CreateProjectActivity.class);
            intent.putExtra("userName", userNickname);
            startActivity(intent);
        } else if (view.getId() == R.id.view_projects_button) {
            intent = new Intent(MainActivity.this, comp3350.ppms.presentation.ProjectListActivity.class);
            intent.putExtra("userName", userNickname);
            startActivity(intent);
        }
    }

}
