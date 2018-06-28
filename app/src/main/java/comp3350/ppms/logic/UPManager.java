package comp3350.ppms.logic;

import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.UP;
import comp3350.ppms.persistence.UPDatabaseInterface;

public class UPManager implements UPManagerInterface
{
    private UPDatabaseInterface dataAccess;
    private ArrayList<UP> elements;

    private UP userProject;
    private int currentUP;

    public UPManager()
    {
        dataAccess = Service.getUPDatabaseInterface();
        elements = null;
        currentUP = 0;
    }

    public UP getUP(UUID userID)
    {
        if(elements == null)
        {
            elements = dataAccess.getUP(userID);
            currentUP = 0;
        }
        if(currentUP < elements.size())
        {
            userProject = elements.get(currentUP);
            currentUP ++;
        }
        else
        {
            elements = null;
            userProject = null;
            currentUP = 0;
        }
        return userProject;
    }
}
