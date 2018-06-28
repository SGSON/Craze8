package allTest;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import logicTest.ProjectManagerTest;
import logicTest.ValidateProjectTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProjectManagerTest.class,
        ValidateProjectTest.class
})

public class AllTests {
}
