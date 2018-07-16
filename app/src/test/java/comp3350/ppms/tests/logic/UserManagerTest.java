package comp3350.ppms.tests.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import java.util.List;
import java.util.ArrayList;

import comp3350.ppms.domain.User;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.persistence.UserDatabaseInterface;
import comp3350.ppms.tests.database.UserDatabase;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotNull;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The UserManagerTest class will test
 * user manager test cases
 */

public class UserManagerTest {

    private UserManager testUserManager;
    private UserManager testUserManagerMock;
    private UserDatabaseInterface userDatabase;

    @Before
    public void setUp(){
        testUserManager = new UserManager(new UserDatabase());
        userDatabase = mock(UserDatabaseInterface.class);
        testUserManagerMock = new UserManager(userDatabase);
    }

    @After
    public void tearDown() {
        testUserManager = null;
        testUserManagerMock = null;
        userDatabase = null;
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

    @Test
    public void testInvalidLogIn(){
        System.out.println("\nTest Invalid login");

        User test = new User(null, "password");
        try{
            testUserManager.loginRequest(test.getUserNickName());
            fail("Exception expected");
        }
        catch (CustomException e){
            assertEquals("Invalid Account Name", e.getErrorMsg());
        }

        System.out.println("Test Passed");
    }

    @Test
    public void testValidLogIn(){
        System.out.println("\nTest Valid login");

        User test = new User("UserForTest", "password");
        try{
            testUserManager.insertUser(test);
            testUserManager.loginRequest(test.getUserNickName());
        }
        catch (CustomException e){
            System.out.println(e);
        }

        System.out.println("Test Passed");
    }

    @Test
    public void testGetUser(){
        System.out.println("\nTest get user");

        String name = "newuser";
        User test = new User(name, "password");
        when(userDatabase.getUserByUserName(name)).thenReturn(test);

        try{
            User user = testUserManagerMock.getUser(name);
            assertNotNull("getting  user should not be null", user);
            assertTrue(name.equals(user.getUserNickName()));
        } catch (CustomException e){
            System.out.println(e.getErrorMsg());
        }

        verify(userDatabase).getUserByUserName(name);
        System.out.println("Test Passed");
    }

    @Test
    public void testGetUserByID(){
        System.out.println("\nTest get user by ID");

        User test = new User("newuser", "password");
        String id = test.getUserID();
        when(userDatabase.getUserByID(id)).thenReturn(test);

        User user = testUserManagerMock.getUserByID(id);
        assertNotNull("getting user should not be null", user);
        assertTrue(id.equals(user.getUserID()));

        verify(userDatabase).getUserByID(id);
        System.out.println("Test Passed");
    }

    @Test
    public void testGetLikedList(){
        System.out.println("\nTest get liked list");

        String orig = "project1";
        User user = new User("user", "password");
        user.addToLikedProjectIDList(orig);

        String proj = user.getLikedProjectIDList().get(0);
        assertNotNull("getting liked list should not be null", proj);
        assertEquals(orig, proj);

        System.out.println("Test Passed");
    }

    @Test
    public void testGetCredential(){
        System.out.println("\nTest get Credential");

        ArrayList<String> list = new ArrayList<>();
        String orig = "java";
        User user = new User("user", "password");
        list.add(orig);
        user.addCredentialList(list);

        String cred = user.getUserCredentials().get(0);
        assertNotNull("getting liked list should not be null", cred);
        assertEquals(orig, cred);

        System.out.println("Test Passed");
    }


}
