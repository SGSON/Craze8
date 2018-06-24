package comp3350.ppms.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.ppms.tests.logic.ProjectManagerTest;
import comp3350.ppms.tests.logic.ValidateProjectTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProjectManagerTest.class,
        ValidateProjectTest.class
})

public class AllTests {
}
