package comp3350.ppms.persistence;

import java.util.UUID;
import java.util.ArrayList;


import comp3350.ppms.domain.User;

public interface UserDatabaseInterface {

    void insertUser(User currentUser);

    void deleteUser(UUID currentUserId);

    User getUserByString(String userNickname);git
}
