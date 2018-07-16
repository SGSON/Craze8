package comp3350.ppms.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.ppms.tests.logic.MatchManagerTestIT;
import comp3350.ppms.tests.logic.ProjectManagerTestIT;
import comp3350.ppms.tests.logic.UserManagerTestIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MatchManagerTestIT.class,
        ProjectManagerTestIT.class,
        UserManagerTestIT.class
})
public class AllIntegrationTests {
}
