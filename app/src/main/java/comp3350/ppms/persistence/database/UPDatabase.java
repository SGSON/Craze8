package comp3350.ppms.persistence.database;

import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.domain.UP;
import comp3350.ppms.persistence.UPDatabaseInterface;

public class UPDatabase implements UPDatabaseInterface {
    private ArrayList<UP> updb;

    public UPDatabase(){
        this.updb = new ArrayList<>();
    }

    public ArrayList<UP> getUP(UUID userID) {
        ArrayList<UP> newUPs;
        UP up;

        newUPs = new ArrayList<>();

        for(int i = 0; i < updb.size(); i ++){
            up = updb.get(i);
            if(up.getUserID().equals(userID)){
                newUPs.add(updb.get(i));
            }
        }
        return newUPs;
    }
}
