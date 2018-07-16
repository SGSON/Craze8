package comp3350.ppms.tests.logic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import java.util.List;
import java.util.UUID;
import org.junit.Test;


import java.util.ArrayList;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.ProjectNameError;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.persistence.ProjectDatabaseInterface;
import comp3350.ppms.tests.database.ProjectDatabase;

/**
 * The ProjectManagerTest class will test
 * different cases that might occur when
 * the user is trying to access projects in the database
 * or retrieving data from the database
 */

public class ProjectManagerTest{

    private ProjectManager projectManager;
    private ProjectManager projectManagerMock;
    private ProjectDatabaseInterface projectDatabase;

    @Before
    public void setUp() {
        projectManager = new ProjectManager(new ProjectDatabase());
        projectDatabase = mock(ProjectDatabaseInterface.class);
        projectManagerMock = new ProjectManager(projectDatabase);
    }


    @Test
    public void testInvalidProjectName(){
        System.out.println("\nStarting testInsertProject: Invalid project name");

        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("", "4564", "hello world", cred);

        try {
            projectManager.insertProject(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testInsertProject: Invalid project name");
    }

    @Test
    public void testInvalidProjectDesc(){
        System.out.println("\nStarting testInsertProject: Invalid project desc");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "48746","", cred);
        try {
            projectManager.insertProject(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty description", expected.getErrorMsg());
        }
        System.out.println("\nFinished testInsertProject: Invalid project desc");
    }

    @Test
    public void testValidProject(){
        System.out.println("\nStarting testInsertProject: Valid project");

        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "45645", "hello world", cred);
        try {
            projectManager.insertProject(newProj);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testInsertProject: Valid project");
    }

    @Test
    public void testSingleProject(){
        System.out.println("\nStarting testRetrieveProject: Single project");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "456465", "hello world", cred);
        try {
            projectManager.insertProject(newProj);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        assertEquals(newProj, projectManager.getProject(newProj.getProjectID()));

        System.out.println("\nFinished testRetrieveProject: Single project");
    }

    @Test
    public void testMultipleProjectList(){
        System.out.println("\nStarting testRetrieveProject: Multiple projects");
        ArrayList<String> cred = new ArrayList<String>();

        List<Project> projectList;

        cred.add("eight");
        Project newProj = new Project("hello", "1234", "hello world", cred);
        Project newProj1 = new Project("hello2", "12345", "hello world", cred);
        try {
            projectManager.insertProject(newProj);
            projectManager.insertProject(newProj1);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        assertEquals(newProj, projectManager.getProject(newProj.getProjectID()));
        assertEquals(newProj1, projectManager.getProject(newProj1.getProjectID()));

        System.out.println("\nFinished testRetrieveProject: Multiple projects");
    }

    @Test
    public void testInterestedUsers() {
        System.out.println("\n Starting testRetrieveProject: Interested Users");

        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "1234", "hello world", cred);
        User user = new User("Lebron James", "Cavs");

        try {
            projectManager.insertProject(newProj);
            projectManager.addInterestedUser(newProj, user.getUserNickName());

        } catch (CustomException expected) {
            assertEquals(null, expected);
        }

    }


    @Test
    public void testGetProjects(){
        System.out.println("\nTest get projects");

        ArrayList<String> cred = new ArrayList<>();
        cred.add("Java");
        List<Project> list = new ArrayList<>();
        list.add(new Project("pName", "owner", "decr", cred));
        when(projectDatabase.getProjectSequential()).thenReturn(list);

        List<Project> proj = projectManagerMock.getProjects();
        assertNotNull("getting projects should not be null", proj);
        assertEquals(list.get(0).getProjectName(), proj.get(0).getProjectName());

        verify(projectDatabase).getProjectSequential();

        System.out.println("\nTest Passed");
    }

    @Test
    public void testGetProject(){
        System.out.println("\nTest get project");

        ArrayList<String> cred = new ArrayList<>();
        cred.add("Java");
        Project proj = new Project("pName", "owner", "descr", cred);

        when(projectDatabase.getProject(proj.getProjectID())).thenReturn(proj);

        Project newProj = projectManagerMock.getProject(proj.getProjectID());

        assertNotNull("project should not be null", newProj);
        assertEquals(proj.getProjectID(), newProj.getProjectID());

        verify(projectDatabase).getProject(proj.getProjectID());

        System.out.println("\nTest Passed");
    }

    @Test
    public void testGetName(){
        System.out.println("\nTest get project name");

        ArrayList<String> cred = new ArrayList<>();
        cred.add("Java");
        String pName = "pName";
        Project proj = new Project(pName, "owner", "descr", cred);

        assertNotNull(proj.getProjectName());
        assertEquals(pName, proj.getProjectName());

        System.out.println("\nTest Passed");
    }

    @Test
    public void testGetDesc(){
        System.out.println("\nTest get project description");

        ArrayList<String> cred = new ArrayList<>();
        cred.add("Java");
        String desc = "Description";
        Project proj = new Project("pName", "owner", desc, cred);

        assertNotNull(proj.getProjectDescription());
        assertEquals(desc, proj.getProjectDescription());

        System.out.println("\nTest Passed");
    }

    @Test
    public void testGetCredential(){
        System.out.println("\nTest get project credential");

        ArrayList<String> cred = new ArrayList<>();
        String lang = "Java";
        cred.add(lang);
        Project proj = new Project("pName", "owner", "description", cred);

        assertNotNull(proj.getProjectCredentials());
        assertNotNull(proj.getProjectCredentials().get(0));
        assertEquals(lang, proj.getProjectCredentials().get(0));

        System.out.println("\nTest Passed");
    }

    @Test
    public void testAddSelectedUser(){
        System.out.println("\nTest add selected user");

        ArrayList<String> cred = new ArrayList<>();
        String lang = "Java";
        cred.add(lang);
        Project proj = new Project("pName", "owner", "description", cred);

        boolean res = projectManagerMock.addSelectedUser(proj, "user");
        assertTrue(res);

        System.out.println("\nTest Passed");
    }

}
