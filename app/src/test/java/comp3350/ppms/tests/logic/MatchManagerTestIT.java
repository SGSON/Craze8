package comp3350.ppms.tests.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.MatchManager;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.tests.util.TestUtils;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MatchManagerTestIT {
    private ProjectManager newProjectManager;
    private UserManager newUserManager;
    private MatchManager newMatchManager;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.newProjectManager = new ProjectManager();
        this.newUserManager = new UserManager();
        this.newMatchManager = new MatchManager();
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
        Service.clean();
    }

    @Test
    public void testMatchProject() {
        User user = null;
        Project project;
        project = newProjectManager.getProject("044f1390-8a73-4150-8d10-ba183d1aa76d");
        newProjectManager.addSelectedUser(project, "b24c0c9f-8307-4e80-961e-43a14a95f504");
        project = newProjectManager.getProject("044f1390-8a73-4150-8d10-ba183d1aa76d");
        try{
            user = newUserManager.getUser("LeBronJames");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        assertTrue(newMatchManager.isUserProjectMatch(user,project));
    }

    @Test
    public void testNotMatchProject() {
        User user = null;
        Project project;
        project = newProjectManager.getProject("044f1390-8a73-4150-8d10-ba183d1aa76d");
        try{
            user = newUserManager.getUser("LeBronJames");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        assertFalse(newMatchManager.isUserProjectMatch(user,project));
    }

    @Test
    public void testGetMatchedUsersForProjects() {
        User user = null;
        Project project;
        project = newProjectManager.getProject("044f1390-8a73-4150-8d10-ba183d1aa76d");
        newProjectManager.addSelectedUser(project, "b24c0c9f-8307-4e80-961e-43a14a95f504");
        project = newProjectManager.getProject("044f1390-8a73-4150-8d10-ba183d1aa76d");
        assertEquals(1, newMatchManager.getMatchedUsersForProject(project).size());
        user = newMatchManager.getMatchedUsersForProject(project).get(0);
        assertEquals("LeBronJames", user.getUserNickName());
        assertEquals("crazy", user.getUserPassword());
    }
}
