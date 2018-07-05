package comp3350.ppms.application;

import comp3350.ppms.persistence.UserDatabaseInterface;
import comp3350.ppms.persistence.ProjectDatabaseInterface;
//import comp3350.ppms.persistence.database.UPDatabase;
import comp3350.ppms.persistence.database.UserDatabase;
import comp3350.ppms.persistence.hsqldb.ProjectPersistenceHSQLDB;
import comp3350.ppms.persistence.hsqldb.UserDatabaseHSQLDB;

public class Service {

    private static ProjectDatabaseInterface projectDatabaseInterface = null;
    private static UserDatabaseInterface userDatabaseInterface = null;
    private static UserDatabaseInterface testUserDatabaseInterface = null;


    public static synchronized ProjectDatabaseInterface getProjectDatabaseInterface()
    {
        if (projectDatabaseInterface == null)
        {
            projectDatabaseInterface = new ProjectPersistenceHSQLDB(Main.getDBPathName());
        }

        return projectDatabaseInterface;
    }

    public static synchronized UserDatabaseInterface getUserDatabaseInterface()
    {
        if(userDatabaseInterface == null)
        {
            //Here is where I changed Service to get an Instance of the the HSQL DB you implemented
            userDatabaseInterface = new UserDatabaseHSQLDB(Main.getDBPathName());
        }

        return userDatabaseInterface;
    }


}
