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
    public boolean isMatch(String projectID, User user) {
        List<String> userInterestedProjects;

        userInterestedProjects = userManager.getUsersInterestedProjects(user);

        return userInterestedProjects.contains(projectID);
    }

    @Override
    public List<User> getMatchedUsersForProject(Project project) {
        List<User> matchedUsers = new ArrayList<>();
        List<String> projectSelectedUsers;

        projectSelectedUsers = projectManager.getSelectedUsersForProject(project);

        for(int i = 0; i < projectSelectedUsers.size(); i++) {
            String userID = projectSelectedUsers.get(i);
            User currUser = userManager.getUserByID(userID);
            if(isMatch(project.getProjectID(), currUser)) {
                matchedUsers.add(currUser);
            }
        }

        return matchedUsers;
    }
}
