package logicTest;

import org.junit.Test;
import junit.framework.TestCase;
import logic.ProjectManager;
import logic.CustomException;
import domain.Project;
import java.util.UUID;

import java.util.ArrayList;

public class ProjectManagerTest extends TestCase{

    public ProjectManagerTest(String arg0) {super(arg0);}

    @Test (expected = CustomException.class)
    public void testInvalidProjectName(){
        System.out.println("\nStarting testInsertProject: Invalid project name");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("", "hello world", cred);
        ProjectManager newManager = new ProjectManager();
        try {
            newManager.insertProject(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testInsertProject: Invalid project name");
    }

    @Test (expected = CustomException.class)
    public void testInvalidProjectDesc(){
        System.out.println("\nStarting testInsertProject: Invalid project desc");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "", cred);
        ProjectManager newManager = new ProjectManager();
        try {
            newManager.insertProject(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty description", expected.getErrorMsg());
        }
        System.out.println("\nFinished testInsertProject: Invalid project desc");
    }

    @Test (expected = CustomException.class)
    public void testValidProject(){
        System.out.println("\nStarting testInsertProject: Valid project");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("hello", "hello world", cred);
        ProjectManager newManager = new ProjectManager();
        try {
            newManager.insertProject(newProj);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testInsertProject: Valid project");
    }

    @Test (expected = CustomException.class)
    public void testMultipleProjectList(){
        UUID id;
        System.out.println("\nStarting testRetrieveProject: Multiple projects");
        ArrayList<String> cred = new ArrayList<String>();
        ArrayList<Project> projectList;
        cred.add("eight");
        Project newProj = new Project("hello", "hello world", cred);
        Project newProj1 = new Project("hello", "hello world", cred);
        ProjectManager newManager = new ProjectManager();
        try {
            newManager.insertProject(newProj);
            newManager.insertProject(newProj1);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        projectList = newManager.getProjects();
        assertEquals(6, projectList.size());

        System.out.println("\nFinished testRetrieveProject: Multiple projects");
    }

}
