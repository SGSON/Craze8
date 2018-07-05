package comp3350.ppms.tests.logic;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.ppms.domain.User;
import comp3350.ppms.logic.CustomException;
import comp3350.ppms.logic.ValidateUser;

/**
 * The ValidateProjectTest class will test
 * some of the ways that customers will input data
 * when creating a project.
 */

public class ValidateUserTest extends TestCase {

    public ValidateUserTest(String arg0) {super(arg0);}

    @Test (expected = CustomException.class)
    public void testMissingPassword(){
        System.out.println("\nStarting testValidateUser: null Password");
        User newUser = new User("username", "");
        try {
            ValidateUser.validatePassword(newUser);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Password requires at least 5 characters.", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateUser: null Password");
    }

    @Test (expected = CustomException.class)
    public void testLongPassword(){
        System.out.println("\nStarting testValidateUser: Long password");
        User newUser = new User("username", "abcdefghijklmnopqrstu");
        try {
            ValidateUser.validatePassword(newUser);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Password cannot exceed 20 characters.", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateUser: Long password");
    }
}
