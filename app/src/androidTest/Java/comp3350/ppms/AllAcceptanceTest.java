package comp3350.ppms;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.test.ppms.R;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import comp3350.ppms.presentation.allusers.LoginActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class AllAcceptanceTest {

    private File tempDB;



    @Before
    public void setUP() throws IOException {
        //tempDB = TestUtils.copyDB();
    }

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    public static Matcher<View> withListSize (final int size) {
        return new TypeSafeMatcher<View> () {
            @Override
            public boolean matchesSafely (final View view) {
                return ((ListView) view).getCount () == size;
            }

            @Override
            public void describeTo (org.hamcrest.Description description) {
                description.appendText ("ListView should have " + size + " items");
            }
        };
    }

    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("position " + childPosition + " of parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    @Test
    //tests issue #17
    public void accountLogin() {
        //type username
        onView(withId(R.id.user_nickname)).perform(typeText("BMwanza"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());

        // verify if login is successful
        onView(withId(R.id.create_project_button)).check(matches(isDisplayed()));
    }

    @Test
    //tests issue #65
    public void userSignUp() {
        //type username
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.user_nickname)).perform(typeText("testing"));
        closeSoftKeyboard();
        onView(withId(R.id.user_password)).perform(typeText("testing"));
        closeSoftKeyboard();
        onView(withId(R.id.create_user_button)).perform(click());

        // verify if login is successful
        onView(withId(R.id.create_project_button)).check(matches(isDisplayed()));
    }

    @Test
    //test issues #19 and #20 and #21
    public void createProjectWithMultipleCredentials() {
        //type username
        onView(withId(R.id.user_nickname)).perform(typeText("BMwanza"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.create_project_button)).perform(click());
        onView(withId(R.id.project_name)).perform(replaceText("newTestProject"));
        onView(withId(R.id.project_description)).perform(replaceText("project used for testing"));
        closeSoftKeyboard();
        onView(withId(R.id.increase_credential_button)).perform(click());
        onView(nthChildOf(withId(R.id.credential_layout),0)).perform(replaceText("credTest1"));
        onView(nthChildOf(withId(R.id.credential_layout),1)).perform(replaceText("credTest2"));
        closeSoftKeyboard();
        pressBack();
        onView(withId(R.id.create_project_button)).perform(click());
        closeSoftKeyboard();
        //verify creation is successful, since there are only 6 projects in the database, adding a 7th will make sure there is something at position 6 and click into view project details
        //Also by doing so, you can see all the projects that's created and that you can show interest in
        onView(withId(R.id.view_created_projects)).perform(click());
        onView(withId(R.id.listProjects)).check(ViewAssertions.matches(withListSize(7)));

    }

    @Test
    //test issues #22 and #23
    public void expressInterest() {
        //type username
        onView(withId(R.id.user_nickname)).perform(typeText("BMwanza"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.view_projects_button)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listProjects)).atPosition(0).perform(click());
        pressBack();
        onView(withId(R.id.project_details_button)).perform(click());
        onView(withId(R.id.like_button)).perform(click());

        //verify that the project is liked.
        pressBack();
        pressBack();
        closeSoftKeyboard();
        onView(withId(R.id.view_liked_projects_button)).perform(click());
        onView(withId(R.id.listProjects)).check(ViewAssertions.matches(withListSize(2)));
    }

    @Test
    //test issues #69
    public void viewInterestedProject() {
        //login to see the liked projects
        onView(withId(R.id.user_nickname)).perform(typeText("BMwanza"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.view_liked_projects_button)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listProjects)).atPosition(0).perform(click());

        //verify the liked project exist
        onView(withId(R.id.project_details_button)).perform(click());
        onView(withId(R.id.project_credentials)).check(ViewAssertions.matches(withListSize(5)));
    }

    @Test
    //test issues #105 and #106
    public void viewInterestedUsers(){
        onView(withId(R.id.user_nickname)).perform(typeText("8"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.view_projects_button)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listProjects)).atPosition(1).perform(click());
        pressBack();
        onView(withId(R.id.project_details_button)).perform(click());
        onView(withId(R.id.like_button)).perform(click());
        pressBack();
        pressBack();
        pressBack();

        onView(withId(R.id.user_nickname)).perform(clearText());
        onView(withId(R.id.user_nickname)).perform(typeText("BMwanza"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.view_projects_button)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listProjects)).atPosition(1).perform(click());
        pressBack();
        onView(withId(R.id.project_details_button)).perform(click());
        onView(withId(R.id.view_interested_users_button)).perform(click());
        //checks that 8 is now an interested user of this project.
        onView(withId(R.id.interestedUsersList)).check(ViewAssertions.matches(withListSize(1)));

        //onto testing issue 106
        onData(anything()).inAdapterView(withId(R.id.interestedUsersList)).atPosition(0).perform(click());
        onView(withId(R.id.select_button)).perform(click());
        pressBack();
        pressBack();
        onView(withId(R.id.view_matched_users_button)).perform(click());
        //verify that the user is now being displayed as a user that has been selected for the project.
        onView(withId(R.id.interestedUsersList)).check(ViewAssertions.matches(withListSize(1)));
    }
}
