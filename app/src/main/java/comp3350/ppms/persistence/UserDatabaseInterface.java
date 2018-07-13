package comp3350.ppms.persistence;

import java.util.UUID;
import java.util.ArrayList;


import comp3350.ppms.domain.User;

public interface UserDatabaseInterface {

    void insertUser(User currentUser);

    void updateUser(User user);

    void deleteUser(User currentUser);


    User getUserByUserName(String userNickname);

    User getUserByID(String ID);



}
