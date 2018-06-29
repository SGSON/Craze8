package comp3350.ppms.persistence;

import java.util.UUID;
import java.util.ArrayList;


import comp3350.ppms.domain.User;

public interface UserDatabaseInterface {

    ArrayList<User> getUserSequential();

    ArrayList<User> getUserInfo(User currentUser);

    User insertUser(User currentUser);

    User updateUser(User currentUser);

    void deleteUser(User currentUser);
}
