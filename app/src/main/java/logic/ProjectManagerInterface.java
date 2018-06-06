package logic;
import domain.DatabaseObject;

public interface ProjectManagerInterface {
    Boolean validateProject(DatabaseObject project); //validates whether the project meets the requirements
}
