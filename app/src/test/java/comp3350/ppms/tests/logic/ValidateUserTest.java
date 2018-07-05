package comp3350.ppms.tests.logic;

import org.junit.Test;
import junit.framework.TestCase;

import comp3350.ppms.logic.ValidateUser;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.User;

public class ValidateUserTest extends TestCase {
    public ValidateUserTest(String arg0) {super(arg0);}

    @Test (expected = CustomException.class)
    public void testMissingName(){
        System.out.println("\nStarting testValidateUser: null Name");
        User newUser = new User("", "qweqwe");
        try {
            ValidateUser.validateName(newUser);
            fail("Exception expected");
        } catch (CustomException expected) {
            assertEquals("Name requires at least 5 characters.", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: null Name");
    }

    @Test (expected = CustomException.class)
    public void testTooLongName(){
        System.out.println("\nStarting testValidateUser: long Name");
        User newUser = new User("012345678901234567890", "qweqwe");
        try {
            ValidateUser.validateName(newUser);
            fail("Exception Expected");
        } catch (CustomException expected) {
            assertEquals("Name cannot exceed 20 characters.", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: long Name");
    }


    @Test
    public void testNormalName(){
        System.out.println("\nStarting testValidateUser: normal Name");
        User newUser = new User("01234567890", "qweqwe");
        try {
            ValidateUser.validateName(newUser);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testValidateProject: normal Name");
    }


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

    public void testNormalPassword(){
        System.out.println("\nStarting testValidateUser: Normal password");
        User newUser = new User("username", "abcdefghijkl");
        try {
            ValidateUser.validatePassword(newUser);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testValidateUser: Normal password");
    }

    @Test (expected = CustomException.class)
    public void testInValidSignup(){
        System.out.println("\nStarting testValidateUser: InValidSignup");
        User newUser = new User("ASDASD", "adsad");
        try {
            ValidateUser.validateUserSignUp(newUser);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Invalid Account Name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateUser: InValidSignup");
    }

    @Test
    public void testValidSignup(){
        System.out.println("\nStarting testValidateUser: ValidSignup");
        User newUser = null;
        try {
            ValidateUser.validateUserSignUp(newUser);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testValidateUser: ValidSignup");
    }

    @Test (expected = CustomException.class)
    public void testInvalidLogin(){
        System.out.println("\nStarting testValidateUser: InvalidLogin");
        User newUser = new User("ASDASD", "adsad");
        try {
            ValidateUser.validateUserSignUp(newUser);
            fail("CustomException expected.");
        } catch (CustomException expected) {
            assertEquals("Invalid Account Name", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateUser: InvalidLogin");
    }

    @Test
    public void testValidLogin(){
        System.out.println("\nStarting testValidateUser: ValidLogin");
        User newUser = null;
        try {
            ValidateUser.validateUserSignUp(newUser);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testValidateUser: ValidLogin");
    }
}
