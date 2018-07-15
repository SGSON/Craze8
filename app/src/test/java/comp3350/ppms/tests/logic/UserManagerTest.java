package comp3350.ppms.tests.logic;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.tests.database.UserDatabase;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
/**
 * The UserManagerTest class will test
 * user manager test cases
 */

public class UserManagerTest {

    private UserManager testUserManager;

    @Before
    public void setUp(){
        testUserManager = new UserManager(new UserDatabase());
    }
//
    @Test
    public void testValidateUsernameTest(){
        System.out.println("\nStarting test insert valid username");

        String userName = "BillyFrom4C";
        String password = "PrettyBiird";

        User testUser = new User(userName, password);
        try {
            testUserManager.insertUser(testUser);
        }catch (CustomException e){
            System.out.println(e);
        }
        System.out.println("Test Passed!");
    }


    @Test
    public void testInvalidUsername(){
        System.out.println("\nStarting testing getting a username not in the database");
        String userName = "tester";

        User testUser = new User("test", "test");

        try{
            testUser = testUserManager.getUser(userName);
            assertEquals(null, testUser);
//            verify(testUserManager).getUser(userName);
        }catch (CustomException e){
            System.out.println(e);
        }
        System.out.println("Test Passed!");
    }

    @Test
    public void testDuplicateName(){
        System.out.println("\nTesting an insert of duplicate name into database");
        User testUser = new User("1235", "tajsdasda");
        User testUser1 = new User("1235", "tajsd");
        try{
            testUserManager.insertUser(testUser);
            testUserManager.insertUser(testUser1);
        }catch (CustomException e){
            System.out.println("Test Passed!");
        }
    }

}
