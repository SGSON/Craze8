package domain;

import java.util.UUID;
import java.util.ArrayList;

public interface ProjectInterface {

    //returns the identification number associated with the project
    public UUID getId();

    //returns the name associated with the project
    public String getName();

    //returns the description associated with the project
    public String getDescription();

    //returns the list of credentials associated with the project
    public ArrayList<String> getCredentials();
}
