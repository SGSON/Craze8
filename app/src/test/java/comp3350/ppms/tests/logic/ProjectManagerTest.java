package comp3350.ppms.tests.logic;

import junit.framework.TestCase;

import java.util.List;
import java.util.UUID;

import org.junit.Test;


import java.util.ArrayList;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.ProjectManager;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * The ProjectManagerTest class will test
 * different cases that might occur when
 * the user is trying to access projects in the database
 * or retrieving data from the database
 */

public class ProjectManagerTest extends TestCase{

    public ProjectManagerTest(String arg0) {super(arg0);}
    private ProjectManager projectManager;

//    @Before
//    public void buildUp(){
//        projectManager = mock(ProjectManager.class);
//    }

    @Test (expected = CustomException.class)
    public void testInvalidProjectName(){
        System.out.println("\nStarting testInsertProject: Invalid project name");
        projectManager = mock(ProjectManager.class);
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("", "4564", "hello world", cred);

        try {
            doThrow(new CustomException(CustomException.EMPTY_NAME_ERROR)).when(projectManager).insertProject(newProj);
            projectManager.insertProject(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testInsertProject: Invalid project name");
    }

    @Test (expected = CustomException.class)
    public void testInvalidProjectDesc(){
        System.out.println("\nStarting testInsertProject: Invalid project desc");
        projectManager = mock(ProjectManager.class);
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "48746","", cred);
        try {
            doThrow(new CustomException(CustomException.EMPTY_DESCRIPTION_ERROR)).when(projectManager).insertProject(newProj);
            projectManager.insertProject(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty description", expected.getErrorMsg());
        }
        System.out.println("\nFinished testInsertProject: Invalid project desc");
    }

    @Test (expected = CustomException.class)
    public void testValidProject(){
        System.out.println("\nStarting testInsertProject: Valid project");
        projectManager = mock(ProjectManager.class);
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

    @Test (expected = CustomException.class)
    public void testSingleProject(){
        System.out.println("\nStarting testRetrieveProject: Single project");
        projectManager = mock(ProjectManager.class);
        ArrayList<String> cred = new ArrayList<String>();
        ArrayList<Project> projectList;
        cred.add("eight");
        Project newProj = new Project("hello", "456465", "hello world", cred);
        try {
            projectManager.insertProject(newProj);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        projectManager.getProject(newProj.getProjectID());
        verify(projectManager, times(1)).getProject(newProj.getProjectID());

        System.out.println("\nFinished testRetrieveProject: Single project");
    }

    @Test (expected = CustomException.class)
    public void testMultipleProjectList(){
        System.out.println("\nStarting testRetrieveProject: Multiple projects");
        projectManager = mock(ProjectManager.class);
        ArrayList<String> cred = new ArrayList<String>();
        List<Project> projectList;
        cred.add("eight");
        Project newProj = new Project("hello", "46545", "hello world", cred);
        Project newProj1 = new Project("hello", "4654", "hello world", cred);
        try {
            projectManager.insertProject(newProj);
            projectManager.insertProject(newProj1);

        } catch (CustomException expected) {
            assertEquals(null, expected);
        }

        projectManager.getProjects();

        verify(projectManager, times(1)).getProjects();

        System.out.println("\nFinished testRetrieveProject: Multiple projects");
    }

    @Test (expected = CustomException.class)
    public void testInterestedUsers() {
        System.out.println("\n Starting testRetrieveProject: Interested Users");
        projectManager = mock(ProjectManager.class);

        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "4658", "hello world", cred);
        User user = new User("Lebron James", "Cavs");

        try {
            projectManager.insertProject(newProj);
            projectManager.addInterestedUser(newProj, user.getUserNickName());

        } catch (CustomException expected) {
            assertEquals(null, expected);
        }

    }



}
