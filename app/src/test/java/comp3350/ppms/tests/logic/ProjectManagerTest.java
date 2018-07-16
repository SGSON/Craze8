package comp3350.ppms.tests.logic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

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
import comp3350.ppms.tests.database.ProjectDatabase;

/**
 * The ProjectManagerTest class will test
 * different cases that might occur when
 * the user is trying to access projects in the database
 * or retrieving data from the database
 */

public class ProjectManagerTest{

    private ProjectManager projectManager;

    @Before
    public void setUp() {
        projectManager = new ProjectManager(new ProjectDatabase());
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

}
