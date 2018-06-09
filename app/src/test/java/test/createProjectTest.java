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
            ProjectManager.validateName(newProj);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Cannot have empty name", expected.getErrorMsg());
        }
    }
}
