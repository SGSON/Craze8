package test;

import org.junit.Test;
import junit.framework.TestCase;

import logic.ProjectManager;
import logic.CustomException;
import domain.Project;

import java.util.List;
import java.util.ArrayList;

public class createProjectTest extends TestCase {
    public createProjectTest(String arg0) {super(arg0);}

    @Test (expected = CustomException.class)
    public void testMissingName() throws CustomException{
        System.out.println("\nStarting testCreateProject: null Name");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("", "hello world", cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testCreateProject: null Name");
    }

    @Test (expected = CustomException.class)
    public void testLongName() throws CustomException{
        System.out.println("\nStarting testCreateProject: Long name");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcdefghijklmnopqrst", "hello world", cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Name exceed required amount of characters", expected.getErrorMsg());
        }
        System.out.println("\nFinished testCreateProject: Long name");
    }

    @Test
    public void testAcceptableName(){
        System.out.println("\nStarting testCreateProject: Normal name");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcdef", "hello world", cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testCreateProject: Normal name");
    }

    @Test (expected = CustomException.class)
    public void testMissingDesc() throws CustomException{
        System.out.println("\nStarting testCreateProject: null Description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcde", "", cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty description", expected.getErrorMsg());
        }
        System.out.println("\nFinished testCreateProject: null Description");
    }

    @Test (expected = CustomException.class)
    public void testLongDesc() throws CustomException{
        System.out.println("\nStarting testCreateProject: Long description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        String tooLong = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop" +
                "qrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        Project newProj = new Project("abcde", tooLong, cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Description exceed required amount of characters", expected.getErrorMsg());
        }
        System.out.println("\nFinished testCreateProject: Long description");
    }

    @Test (expected = CustomException.class)
    public void testNormalDesc() throws CustomException{
        System.out.println("\nStarting testCreateProject: Normal description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcde", "Hello World", cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
        } catch (CustomException expected) {
            assertEquals(expected, null);
        }
        System.out.println("\nFinished testCreateProject: Normal description");
    }

    //Since project name is validated first, then the exception for empty name will be thrown before empty description.
    @Test (expected = CustomException.class)
    public void testAllEmpty() throws CustomException{
        System.out.println("\nStarting testCreateProject: Normal description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("", "", cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testCreateProject: Normal description");
    }

    //Since project name is validated first, then the exception for empty name will be thrown before empty description.
    @Test (expected = CustomException.class)
    public void testAllLimitBreak() throws CustomException{
        System.out.println("\nStarting testCreateProject: Normal description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        String tooLongDesc = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop" +
                "qrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        String tooLongName = "abcdefghijklmnopqr";
        Project newProj = new Project(tooLongName, tooLongDesc, cred);
        try {
            ProjectManager.processNewProjectRequest(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Name exceed required amount of characters", expected.getErrorMsg());
        }
        System.out.println("\nFinished testCreateProject: Normal description");
    }
}
