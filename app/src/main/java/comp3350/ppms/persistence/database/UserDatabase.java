package comp3350.ppms.persistence.database;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;
import comp3350.ppms.domain.User;

public class UserDatabase implements UserDatabaseInterface{
    private Hashtable<String, User> userStub;

    public UserDatabase(){
        userStub = new Hashtable<>();
    }

    public void insertUser(User currentUser){
        userStub.put(currentUser.getUserID(), currentUser);
    }

    public void deleteUser(User currentUser){
        userStub.remove(currentUser.getUserID());
    }
    //TODO I can probably remove this
//    //Hopefully this will return if this is contained in the database
//    public Boolean getUserID(String userName){
//        boolean result = false;
//        userStub.contains(userName);
//
//        return result;
//
//    }

    //Given a user account name, will return the User
    public User getUserByString(String userNickname){
        User result = null;

        Set<String> hashKeys = userStub.keySet();

        for(String key: hashKeys) {
            User user = userStub.get(key);
            if(user.getUserNickName().equals(userNickname)){
                result = user;
            }
        }

        return result;

    }

}
