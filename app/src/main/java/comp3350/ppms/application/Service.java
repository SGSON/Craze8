package comp3350.ppms.application;

import comp3350.ppms.domain.User;
import comp3350.ppms.persistence.UPDatabaseInterface;
import comp3350.ppms.persistence.UserDatabaseInterface;
import comp3350.ppms.persistence.database.ProjectDatabase;
import comp3350.ppms.persistence.ProjectDatabaseInterface;
import comp3350.ppms.persistence.database.UPDatabase;
import comp3350.ppms.persistence.database.UserDatabase;

public class Service {

    private static ProjectDatabaseInterface projectDatabaseInterface = null;
    private static UserDatabaseInterface userDatabaseInterface = null;
    private static UPDatabaseInterface upDatabaseInterface = null;
    private User account;

    public Service(){
        account = null;
    }

    public static synchronized ProjectDatabaseInterface getProjectDatabaseInterface()
    {
        if (projectDatabaseInterface == null)
        {
            projectDatabaseInterface = new ProjectDatabase();
        }

        return projectDatabaseInterface;
    }

    public static synchronized UserDatabaseInterface getUserDatabaseInterface()
    {
        if(userDatabaseInterface == null)
        {
            userDatabaseInterface = new UserDatabase();
        }

        return userDatabaseInterface;
    }

    public static synchronized UPDatabaseInterface getUPDatabaseInterface()
    {
        if(upDatabaseInterface == null)
        {
            upDatabaseInterface = new UPDatabase();
        }

        return upDatabaseInterface;
    }

    public void setAccount(User account){
        this.account = account;
    }

    public User getAccount(){
        return account;
    }
}
