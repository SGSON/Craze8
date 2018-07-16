package comp3350.ppms.tests.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.tests.util.TestUtils;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserManagerTestIT {
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
    public void testInsertValidUser(){
        User user = new User("testName1", "testPassWord");
        try {
            newUserManager.insertUser(user);
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        try {
            user = newUserManager.getUser("testName1");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        assertEquals("testPassWord", user.getUserPassword());
    }

    @Test
    public void testInsertInvalidUser(){
        User user = new User("LeBronJames", "testPassWord");
        try {
            newUserManager.insertUser(user);
            fail("exception expected");
        } catch (CustomException expected){

        }
    }

    @Test
    public void testGetUser(){
        User user = null;
        try {
            user = newUserManager.getUser("8");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        assertEquals("crazy", user.getUserPassword());
    }

    @Test
    public void testGetUserByID(){
        User user = null;
        user = newUserManager.getUserByID("a0583573-7ed7-495a-be2d-16229af56411");
        assertEquals("8", user.getUserNickName());
    }

    @Test
    public void testAddProjectToUserInterestedList(){
        User user = null;
        try {
            user = newUserManager.getUser("8");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        newUserManager.addProjectToUserInterestedList(user, "4c17df09-ceac-4041-8572-7aa0905aaa13");
        try {
            user = newUserManager.getUser("8");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        assertEquals(1, user.getLikedProjectIDList().size());
        assertEquals("4c17df09-ceac-4041-8572-7aa0905aaa13", user.getLikedProjectIDList().get(0));
    }

    @Test
    public void testGetUserCredentials(){
        User user = null;
        List<String> creds = new ArrayList<>();
        try {
            user = newUserManager.getUser("8");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        creds = newUserManager.getUserCredentials(user);

        assertEquals(4, creds.size());
        assertEquals("C++", creds.get(0));
    }

    @Test
    public void testGetUsersInterestedProjects(){
        User user = null;
        List<String> interestedProjects = new ArrayList<>();
        try {
            user = newUserManager.getUser("BMwanza");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        interestedProjects = newUserManager.getUsersInterestedProjects(user);

        assertEquals(1, interestedProjects.size());
        assertEquals("692bd732-0048-4b78-a682-a93510feda87", interestedProjects.get(0));
    }

    @Test
    public void testifUserIsProjectOwner(){
        User user = null;
        Project project;
        try {
            user = newUserManager.getUser("BMwanza");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        project = newManager.getProject("90cd9b07-4c6d-4b01-8edd-5891c5be9847");
        assertTrue(newUserManager.userIsProjectOwner(user, project));
    }

    @Test
    public void testIfUserIsNotProjectOwner(){
        User user = null;
        Project project;
        try {
            user = newUserManager.getUser("8");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        project = newManager.getProject("90cd9b07-4c6d-4b01-8edd-5891c5be9847");
        assertTrue(!newUserManager.userIsProjectOwner(user, project));
    }

    @Test
    public void testLoginRequest(){
        User user = null;
        try {
            user = newUserManager.loginRequest("8");
        } catch (CustomException expected){
            assertEquals(null, expected);
        }
        assertEquals("crazy", user.getUserPassword());
    }
}
