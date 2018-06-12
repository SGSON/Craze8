package application;

import persistence.Database;
import persistence.DatabaseInterface;

public class Service {
    private static DatabaseInterface databaseInterface = null;

    public static synchronized DatabaseInterface getdatabaseInterface()
    {
        if (databaseInterface == null)
        {
            databaseInterface = new Database();
        }

        return databaseInterface;
    }
}
