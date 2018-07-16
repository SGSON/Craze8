package comp3350.ppms.tests.logic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Test;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.logic.MatchManager;
import comp3350.ppms.logic.ProjectManager;
import comp3350.ppms.logic.ProjectManagerInterface;
import comp3350.ppms.logic.UserManager;
import comp3350.ppms.logic.UserManagerInterface;
import comp3350.ppms.persistence.ProjectDatabaseInterface;
import comp3350.ppms.persistence.UserDatabaseInterface;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatchManagerTest {

    private ProjectManagerInterface projectManagerMock;
    private UserManagerInterface userManagerMock;
    private MatchManager matchManagerMock;

    @Before
    public void setUp(){
        projectManagerMock = mock(ProjectManagerInterface.class);
        userManagerMock = mock(UserManagerInterface.class);
        matchManagerMock = new MatchManager(projectManagerMock, userManagerMock);
    }

    @Test
    public void testIsUserProjectMatch(){
        System.out.println("\nTest IsUserProjectMatch");

        String uName = "uName";
        User user = new User(uName, "password");
        ArrayList<String> cred = new ArrayList<>();
        String pName =  "pName";
        cred.add("Java");
        Project proj = new Project(pName, "owner", "description", cred);

        List<String> uList = new ArrayList<>();
        uList.add(user.getUserID());
        List<String> pList = new ArrayList<>();
        pList.add(proj.getProjectID());

        when(userManagerMock.getUsersInterestedProjects(user)).thenReturn(pList);
        when(projectManagerMock.getSelectedUsersForProject(proj)).thenReturn(uList);

        assertTrue(matchManagerMock.isUserProjectMatch(user, proj));

        verify(userManagerMock).getUsersInterestedProjects(user);
        verify(projectManagerMock).getSelectedUsersForProject(proj);

        System.out.println("\nTest Passed");
    }

    @Test
    public void testGetMatchedUsersForProject(){
        System.out.println("\nTest GetMatchedUsersForProject");

        String uName = "uName";
        User user = new User(uName, "password");
        ArrayList<String> cred = new ArrayList<>();
        String pName =  "pName";
        cred.add("Java");
        Project proj = new Project(pName, "owner", "description", cred);

        List<String> uList = new ArrayList<>();
        uList.add(user.getUserID());
        List<String> pList = new ArrayList<>();
        pList.add(proj.getProjectID());

        when(projectManagerMock.getSelectedUsersForProject(proj)).thenReturn(uList);
        when(userManagerMock.getUserByID(user.getUserID())).thenReturn(user);
        when(userManagerMock.getUsersInterestedProjects(user)).thenReturn(pList);

        List<User> newUsers = matchManagerMock.getMatchedUsersForProject(proj);
        assertEquals(user.getUserID(), newUsers.get(0).getUserID());

        verify(projectManagerMock, times(2)).getSelectedUsersForProject(proj);
        verify(userManagerMock).getUserByID(user.getUserID());
        verify(userManagerMock).getUsersInterestedProjects(user);

        System.out.println("\nTest Passed");
    }

    @Test
    public void testGetMatchedProjectsForUser(){
        System.out.println("\nTest GetMatchedProjectsForUser");

        String uName = "uName";
        User user = new User(uName, "password");
        ArrayList<String> cred = new ArrayList<>();
        String pName =  "pName";
        cred.add("Java");
        Project proj = new Project(pName, "owner", "description", cred);

        List<String> uList = new ArrayList<>();
        uList.add(user.getUserID());
        List<String> pList = new ArrayList<>();
        pList.add(proj.getProjectID());

        when(userManagerMock.getUsersInterestedProjects(user)).thenReturn(pList);
        when(projectManagerMock.getProject(proj.getProjectID())).thenReturn(proj);
        when(projectManagerMock.getSelectedUsersForProject(proj)).thenReturn(uList);

        List<Project> projs = matchManagerMock.getMatchedProjectsForUser(user);
        assertEquals(proj.getProjectID(), projs.get(0).getProjectID());

        verify(userManagerMock, times(2)).getUsersInterestedProjects(user);
        verify(projectManagerMock).getProject(proj.getProjectID());
        verify(projectManagerMock).getSelectedUsersForProject(proj);


        System.out.println("\nTest Passed");
    }
}
