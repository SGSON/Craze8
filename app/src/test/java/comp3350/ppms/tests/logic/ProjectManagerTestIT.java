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
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.tests.util.TestUtils;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProjectManagerTestIT {
    private ProjectManager newManager;
    private UserManager newUserManager;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.newManager = new ProjectManager();
        this.newUserManager = new UserManager();
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
        Service.clean();
    }

    @Test
    public void testGetProjectByID() {
        final Project project;

        project = newManager.getProject("044f1390-8a73-4150-8d10-ba183d1aa76d");
        assertNotNull("the porject should not be null", project);
        assertTrue("Internet Flix".equals(project.getProjectName()));

        System.out.println("Finished test getProjectByID");
    }

    @Test
    public void testInsertValidProject() {
        final Project project;
        int i = 0;
        int found = -1;
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");

        project = new Project("testProject1", "a0583573-7ed7-495a-be2d-16229af56411", "this is a test", cred);
        try {
            newManager.insertProject(project);
        } catch (CustomException expected){
            assertEquals(null, expected);
            assertEquals(7, newManager.getProjects().size());
            while(i < 7){
                if (newManager.getProjects().get(i).getProjectName().equals("testProject1")){
                    found = i;
                    break;
                } else {
                    i++;
                }
            }
            assertTrue(found > -1);
            assertEquals(newManager.getProjects().get(i).getProjectDescription(), "this is a test");
        }
        System.out.println("Finished test InsertValidProject");
    }

    @Test
    public void testInsertInvalidProject() {
        final Project project;

        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");

        project = new Project("", "a0583573-7ed7-495a-be2d-16229af56411", "this is a test", cred);
        try {
            newManager.insertProject(project);
        } catch (CustomException expected){
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("Finished test InsertValidProject");
    }

    @Test
    public void testAddInterestedUser() {
        final Project project;
        project = newManager.getProject("4c17df09-ceac-4041-8572-7aa0905aaa13");
        newManager.addInterestedUser(project, "a0583573-7ed7-495a-be2d-16229af56411");
        assertEquals(1, newManager.getProject("4c17df09-ceac-4041-8572-7aa0905aaa13").getInterestedUsers().size());
        assertEquals("a0583573-7ed7-495a-be2d-16229af56411", newManager.getProject("4c17df09-ceac-4041-8572-7aa0905aaa13").getInterestedUsers().get(0));
        System.out.println("Finished test add interested user");
    }

    @Test
    public void testAddSelectedUser() {
        final Project project;
        project = newManager.getProject("4c17df09-ceac-4041-8572-7aa0905aaa13");
        newManager.addSelectedUser(project, "a0583573-7ed7-495a-be2d-16229af56411");
        assertEquals(1, newManager.getProject("4c17df09-ceac-4041-8572-7aa0905aaa13").getSelectedUsers().size());
        assertEquals("a0583573-7ed7-495a-be2d-16229af56411", newManager.getSelectedUsersForProject(project).get(0));
        System.out.println("Finished test add selected User");
    }

    @Test
    public void getInterestedUsers() {
        final Project project;
        User user = null;
        project = newManager.getProject("044f1390-8a73-4150-8d10-ba183d1aa76d");
        assertEquals(1, newManager.getInterestedUsers(project).size());
        try {
            user = newUserManager.getUser("LeBronJames");
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        assertEquals(user.getUserNickName(), newManager.getInterestedUsers(project).get(0).getUserNickName());
        assertEquals(user.getLikedProjectIDList().get(0), newManager.getInterestedUsers(project).get(0).getLikedProjectIDList().get(0));
        assertEquals(user.getLikedProjectIDList().get(1), newManager.getInterestedUsers(project).get(0).getLikedProjectIDList().get(1));
    }
}
