package comp3350.ppms.persistence.database;

import java.util.Hashtable;
import java.util.UUID;

import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UserDatabaseInterface;

public class UserDatabase implements UserDatabaseInterface{
    private Hashtable<UUID, User> userStub;

    public UserDatabase(){
        userStub = new Hashtable<>();
    }

    public void insertUser(User currentUser){
        userStub.put(currentUser.getUserID(), currentUser);
    }

    public void deleteUser(UUID currentUserId){
        userStub.remove(currentUserId);
    }

    //Hopefully this will return if this is contained in the database
    public Boolean getUserID(String userName){
        boolean result = false;
        userStub.contains(userName);

        return result;

    }
}
