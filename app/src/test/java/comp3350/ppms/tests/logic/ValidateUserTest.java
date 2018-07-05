package comp3350.ppms.tests.logic;


import org.junit.Test;
import junit.framework.TestCase;

import comp3350.ppms.logic.ValidateUser;
import comp3350.ppms.logic.CustomException;
import comp3350.ppms.domain.User;

import java.util.ArrayList;

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
        System.out.println("\nStarting testValidateUser: null Name");
        User newUser = new User("012345678901234567890", "qweqwe");
        try {
            ValidateUser.validateName(newUser);
            fail("Exception Expected");
        } catch (CustomException expected) {
            assertEquals("Name cannot exceed 20 characters.", expected.getErrorMsg());
        }
        System.out.println("\nFinished testValidateProject: null Name");
    }


/*    @Test
    public void testNormalName(){
        System.out.println("\nStarting testValidateUser: normal Name");
        User newUser = new User("01234567890", "qweqwe");
        try {
            ValidateUser.validateName(newUser);
        } catch (CustomException expected) {
            assertEquals(null, expected);
        }
        System.out.println("\nFinished testValidateProject: normal Name");
    }*/
}
