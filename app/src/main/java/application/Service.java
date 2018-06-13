package application;

import persistence.DatabaseInterface;

import static persistence.Database.getDatabaseInstance;

public class Service {
    private static DatabaseInterface databaseInterface = null;

    public static synchronized DatabaseInterface getDatabaseInterface()
    {
        if (databaseInterface == null)
        {
            databaseInterface = getDatabaseInstance();
        }

        return databaseInterface;
    }
}
