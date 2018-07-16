package comp3350.ppms.tests.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import java.util.List;
import java.util.ArrayList;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.MatchManager;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.persistence.ProjectDatabaseInterface;
import comp3350.ppms.persistence.UserDatabaseInterface;
import comp3350.ppms.tests.database.ProjectDatabase;
import comp3350.ppms.tests.database.UserDatabase;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotNull;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatchManagerTest {

    private UserManager testUserManager;
    private UserManager testUserManagerMock;

    private ProjectManager testProjectManager;
    private ProjectManager testProjectManagerMock;

    private MatchManager testMatchManager;
    private UserDatabaseInterface userDatabase;
    private ProjectDatabaseInterface projectDatabase;

    @Before
    public void setUp(){
        testUserManager = new UserManager(new UserDatabase());
        userDatabase = mock(UserDatabaseInterface.class);
        testUserManagerMock = new UserManager(userDatabase);

        testProjectManager = new ProjectManager(new ProjectDatabase());
        projectDatabase = mock(ProjectDatabaseInterface.class);
        testProjectManagerMock = new ProjectManager(projectDatabase);

        testMatchManager = new MatchManager(testProjectManager, testUserManager);
    }

    @After
    public void tearDown() {
        testUserManager = null;
        testUserManagerMock = null;
        userDatabase = null;

        testProjectManager = null;
        testProjectManagerMock = null;
        projectDatabase = null;

        testMatchManager = null;
    }

    @Test
    public void testMatchProject(){
        int i = 0;
        List<Project> testProjects = new ArrayList<>();

        User user = new User("testing", "testing");
        try {
            testUserManager.insertUser(user);
        } catch (CustomException expected){

        }

        Project testProject = null;
        testProjects = testProjectManager.getProjects();

        while (i < testProjects.size()){
            if (testProjects.get(i).getProjectName().equals("InternetFlix")){
                testProject = testProjects.get(i);
                break;
            } else {
                i++;
            }
        }
        user.addToLikedProjectIDList(testProject.getProjectID());
        testProject.addSelectedUser(user.getUserID());
        testProjects = testProjectManager.getProjects();
        while (i < testProjects.size()){
            if (testProjects.get(i).getProjectName().equals("InternetFlix")){
                testProject = testProjects.get(i);
                break;
            } else {
                i++;
            }
        }
        try {
           user = testUserManager.getUser("testing");
        }catch (CustomException expected){

        }
        assertTrue(testMatchManager.isUserProjectMatch(user,testProject));
    }

    @Test
    public void testNotMatchProject(){
        int i = 0;
        List<Project> testProjects = new ArrayList<>();

        User user = new User("testing", "testing");
        try {
            testUserManager.insertUser(user);
        } catch (CustomException expected){

        }

        Project testProject = null;
        testProjects = testProjectManager.getProjects();

        while (i < testProjects.size()){
            if (testProjects.get(i).getProjectName().equals("InternetFlix")){
                testProject = testProjects.get(i);
                break;
            } else {
                i++;
            }
        }
        assertFalse(testMatchManager.isUserProjectMatch(user,testProject));
    }

    /*@Test
    public void testGetMatchedUsersForProjects() {
        int i = 0;
        String projectIDTrack;
        List<Project> testProjects = new ArrayList<>();

        User user = new User("testing", "testing");
        try {
            testUserManager.insertUser(user);
        } catch (CustomException expected){

        }

        Project testProject = null;
        testProjects = testProjectManager.getProjects();

        while (i < testProjects.size()){
            if (testProjects.get(i).getProjectName().equals("InternetFlix")){
                testProject = testProjects.get(i);
                break;
            } else {
                i++;
            }
        }
        projectIDTrack = testProject.getProjectID();
        user.addToLikedProjectIDList(projectIDTrack);
        testProject.addSelectedUser(user.getUserID());

        testProject = testProjectManager.getProject(projectIDTrack);
        testMatchManager = new MatchManager(testProjectManager, testUserManager);
        assertEquals(1, testMatchManager.getMatchedUsersForProject(testProject).size());
        testMatchManager.getMatchedUsersForProject(testProject);
        assertEquals("testing", user.getUserNickName());
        assertEquals("testing", user.getUserPassword());
    }*/
}
