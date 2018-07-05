package comp3350.ppms.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.ppms.tests.logic.ProjectManagerTest;
import comp3350.ppms.tests.logic.UserManagerTest;
import comp3350.ppms.tests.logic.ValidateProjectTest;
import comp3350.ppms.tests.logic.ValidateUserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProjectManagerTest.class,
        ValidateProjectTest.class,
        UserManagerTest.class,
        ValidateUserTest.class
})

public class AllTests {
}
