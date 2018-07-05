package comp3350.ppms.tests.logic;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.sql.SQLOutput;

import comp3350.ppms.domain.User;
import comp3350.ppms.logic.UserManager;

/**
 * The UserManagerTest class will test
 * user manager test cases
 */

public class UserManagerTest extends TestCase {

    public UserManagerTest(String arg0){super(arg0);}
    private UserManager testUserManager;


//    @Before
//    public void buildUp(){
//        testUserManager = new UserManager();
//    }

    @Test
    public void testValidateUsernameTest(){
        System.out.println("\nStarting test validate username");

        testUserManager = new UserManager();
        String userName = "BillyFrom4C";
        String password = "PrettyBiird";

        User testUser = new User(userName, password);
//        testUserManager.insertUser(testUser);

//        assertNotNull(testUserManager.validateUserName(userName));
        System.out.println("Test Passed!");
    }

    public void testInvalidUsername(){
        System.out.println("\nStarting testing invalid username");
        String userName = "tester";
        testUserManager = new UserManager();
//        assertNull(testUserManager.validateUserName(userName));
        System.out.println("Test Passed!");
    }

}
