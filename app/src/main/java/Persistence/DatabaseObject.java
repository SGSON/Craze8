package Persistence;

import java.util.UUID;

/**
 * The purpose of this class is to serve as a superclass
 * of all the classes that will be stored in the database
 * We may be able to remove this class in the future, but given how
 * Our foundation DB uses a simple hashtable we will need this class for now
 */
public abstract class DatabaseObject {

    public abstract UUID getId();
}



