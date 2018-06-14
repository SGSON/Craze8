package logicTest;

import org.junit.Test;
import junit.framework.TestCase;

import logic.ValidateProject;
import logic.CustomException;
import domain.Project;

import java.util.ArrayList;

/**
 * The ValidateProjectTest class will test
 * some of the ways that customers will input data
 * when creating a project.
 */

public class ValidateProjectTest extends TestCase {
    public ValidateProjectTest(String arg0) {super(arg0);}

    @Test (expected = CustomException.class)
    public void testMissingName(){
        System.out.println("\nStarting testValidateProject: null Name");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("", "hello world", cred);
        try {
            ValidateProject.validateName(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: null Name");
    }

    @Test (expected = CustomException.class)
    public void testLongName(){
        System.out.println("\nStarting testValidateProject: Long name");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcdefghijklmnopqrst", "hello world", cred);
        try {
            ValidateProject.validateName(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Name exceed required amount of characters", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: Long name");
    }

    @Test
    public void testAcceptableName(){
        System.out.println("\nStarting testValidateProject: Normal name");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcdef", "hello world", cred);
        try {
            ValidateProject.validateName(newProj);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testValidateProject: Normal name");
    }

    @Test (expected = CustomException.class)
    public void testMissingDesc() {
        System.out.println("\nStarting testValidateProject: null Description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcde", "", cred);
        try {
            ValidateProject.validateDescription(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty description", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: null Description");
    }

    @Test (expected = CustomException.class)
    public void testLongDesc() {
        System.out.println("\nStarting testValidateProject: Long description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        String tooLong = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop" +
                "qrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        Project newProj = new Project("abcde", tooLong, cred);
        try {
            ValidateProject.validateDescription(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Description exceed required amount of characters", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: Long description");
    }

    @Test (expected = CustomException.class)
    public void testNormalDesc() {
        System.out.println("\nStarting testValidateProject: Normal description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        Project newProj = new Project("abcde", "Hello World", cred);
        try {
            ValidateProject.validateDescription(newProj);
        } catch (CustomException expected) {
            assertEquals(expected, null);
        }
        System.out.println("\nFinished testValidateProject: Normal description");
    }

    //Since project name is validated first, then the exception for empty name will be thrown before empty description.
    @Test (expected = CustomException.class)
    public void testAllEmpty() {
        System.out.println("\nStarting testValidateProject: Normal description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("");
        Project newProj = new Project("", "", cred);
        try {
            ValidateProject.validateAll(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: Normal description");
    }

    //Since project name is validated first, then the exception for empty name will be thrown before empty description.
    @Test (expected = CustomException.class)
    public void testAllLimitBreak() {
        System.out.println("\nStarting testValidateProject: Normal description");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        String tooLongDesc = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnop" +
                "qrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        String tooLongName = "abcdefghijklmnopqr";
        Project newProj = new Project(tooLongName, tooLongDesc, cred);
        try {
            ValidateProject.validateAll(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Name exceed required amount of characters", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: Normal description");
    }

    @Test (expected = CustomException.class)
    public void testShortCredentials() {
        System.out.println("\nStarting testValidateProject: Short credential");
        ArrayList<String> cred = new ArrayList<String>();
        cred.add("eight");
        cred.add("five");
        String desc = "abcdefgh";
        String name = "abcdefg";
        Project newProj = new Project(name, desc, cred);
        try {
            ValidateProject.validateCredentials(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Credentials must exceed 5 characters.", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: Short credentials");
    }

    @Test (expected = CustomException.class)
    public void testEmptyCredentials() {
        System.out.println("\nStarting testValidateProject: Empty credential");
        ArrayList<String> cred = new ArrayList<String>();
        String desc = "abcdefgh";
        String name = "abcdefg";
        Project newProj = new Project(name, desc, cred);
        try {
            ValidateProject.validateCredentials(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Credentials must not be empty.", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: Empty credentials");
    }

    @Test (expected = CustomException.class)
    public void testProjectId() {
        System.out.println("\nStarting testValidateProject: Checking id");
        ArrayList<String> cred = new ArrayList<String>();
        String desc = "abcdefgh";
        String name = "abcdefg";
        cred.add("hello");
        Project newProj = new Project(name, desc, cred);
        try {
            ValidateProject.validateUUID(newProj);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testValidateProject: Checking id");
    }
}
