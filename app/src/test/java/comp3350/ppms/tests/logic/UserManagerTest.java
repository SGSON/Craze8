package comp3350.ppms.tests.logic;

import android.test.suitebuilder.annotation.Smoke;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import comp3350.ppms.domain.User;
import comp3350.ppms.logic.CustomException;
import comp3350.ppms.logic.UserManager;

import static org.mockito.Mockito.*;
/**
 * The UserManagerTest class will test
 * user manager test cases
 */

public class UserManagerTest extends TestCase {

    public UserManagerTest(String arg0){super(arg0);}
    private UserManager testUserManager;


    @Before
    public void setUp(){
        testUserManager = mock(UserManager.class);
    }

    @Test
    public void testValidateUsernameTest(){
        System.out.println("\nStarting test validate username");

        String userName = "BillyFrom4C";
        String password = "PrettyBiird";

        User testUser = new User(userName, password);
        try {
            testUserManager.insertUser(testUser);
            verify(testUserManager).insertUser(testUser);

        }catch (CustomException e){
            System.out.println(e);
        }

        System.out.println("Test Passed!");
    }

    @Before void inavlidUsernameTestSetUp(){
        testUserManager = mock(UserManager.class);
    }
    @Test(expected = CustomException.class)
    public void testInvalidUsernameSubmit(){
        System.out.println("\nStarting test of an invalid username");
//        testUserManager = mock(UserManager.class);
        User testUser = new User("","sasdasd");
        try {
            doThrow(CustomException.class).when(testUserManager).insertUser(testUser);
            testUserManager.insertUser(testUser);
        } catch (CustomException e) {
            System.out.println("Test Passed!");
        }
    }

    @Before
    public void setUpBefore(){
        testUserManager = mock(UserManager.class);
    }
    @Test
    public void testInvalidUsername(){
        System.out.println("\nStarting testing invalid username");
        String userName = "tester";
        User testUser = new User("test", "test");

        try{
            testUser = testUserManager.getUser(userName);
            verify(testUserManager).getUser(userName);
        }catch (CustomException e){
            System.out.println(e);
        }
        assertNull(testUser);
        System.out.println("Test Passed!");
    }

}
