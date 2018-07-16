package comp3350.ppms.logic;

import java.util.ArrayList;
import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;

public class MatchManager implements MatchManagerInterface {

    private ProjectManagerInterface projectManager;
    private UserManagerInterface userManager;

    public MatchManager() {

        projectManager = new ProjectManager();
        userManager = new UserManager();
    }

    @Override
    public boolean isUserProjectMatch(User user, Project proj) {
        List<String> projectSelectedUsers;
        List<String> userInterestedProjects;

        projectSelectedUsers = projectManager.getSelectedUsersForProject(proj);
        userInterestedProjects = userManager.getUsersInterestedProjects(user);

        return projectSelectedUsers.contains(user.getUserID()) &&
                userInterestedProjects.contains(proj.getProjectID());
    }

    @Override
    public List<User> getMatchedUsersForProject(Project project) {
        List<User> matchedUsers = new ArrayList<>();
        List<String> projectSelectedUsers;

        projectSelectedUsers = projectManager.getSelectedUsersForProject(project);

        /**
         * For each user that the Project Owner has selected:
         * If that User has shown interest to the project they were selected for - its a match!
         */
        for(int i = 0; i < projectSelectedUsers.size(); i++) {
            String userID = projectSelectedUsers.get(i);
            User currUser = userManager.getUserByID(userID);
            if(isUserProjectMatch(currUser, project)) {
                matchedUsers.add(currUser);
            }
        }
        return matchedUsers;
    }

    /**
     *
     * @param user
     * @return The list of projects this user has been matched with
     *
     * Uses the matching algorithm from the users perspective
     */
    @Override
    public List<Project> getMatchedProjectsForUser(User user) {
        List<Project> matchedProjects = new ArrayList<>();
        List<String> userLikedProjects;

        userLikedProjects = userManager.getUsersInterestedProjects(user);
        /**
         * For each Project that the user is interested in
         * If that User has been selected for the project - it's a match
         */
        for(int i = 0; i < userLikedProjects.size(); i++) {
            String projectID = userLikedProjects.get(i);
            Project currProject = projectManager.getProject(projectID);
            if(isUserProjectMatch(user, currProject)) {
                matchedProjects.add(currProject);
            }
        }

        return matchedProjects;
    }
}
