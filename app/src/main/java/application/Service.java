package application;

import persistence.ProjectDatabaseInterface;

import static persistence.ProjectProjectDatabase.getDatabaseInstance;

public class Service {
    private static ProjectDatabaseInterface projectDatabaseInterface = null;

    public static synchronized ProjectDatabaseInterface getProjectDatabaseInterface()
    {
        if (projectDatabaseInterface == null)
        {
            projectDatabaseInterface = getDatabaseInstance();
        }

        return projectDatabaseInterface;
    }
}
