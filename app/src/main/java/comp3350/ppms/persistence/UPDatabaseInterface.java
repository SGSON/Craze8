package comp3350.ppms.persistence;

import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.domain.UP;

public interface UPDatabaseInterface {

    ArrayList<UP> getUP(UUID userID);
}
