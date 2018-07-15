package comp3350.ppms.logic;

import java.util.List;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;

public interface MatchManagerInterface {

    boolean isUserProjectMatch(User user, Project proj);

    List<User> getMatchedUsersForProject(Project project);

}
