package logic;

import domain.DatabaseObject;
/**
 * The validate project class implements all the methods required
 * to verify if the project input by users fit in a certain criteria.
 */
public class ValidateProject {

    private static final int NAME_LENGTH = 16;
    private static final int DESCRIPTION_LENGTH = 256;
    private static final int CREDENTIALS_LENGTH = 5;

    public static boolean validateAll(DatabaseObject project) throws CustomException{
        validateUUID(project);
        validateName(project);
        validateDescription(project);
        validateCredentials(project);
        return true;
    }

    //@Override
    public static boolean validateUUID(DatabaseObject project) throws CustomException{
        if (project.getId() == null){
            throw new CustomException("No Id for project");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateName(DatabaseObject project) throws CustomException {
        if (project.getName().length() > NAME_LENGTH){
            throw new CustomException("Name exceed required amount of characters");
        } else if (project.getName() == null || project.getName() == ""){
            throw new CustomException("Cannot have empty name");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateDescription(DatabaseObject project) throws CustomException {
        if (project.getDescription().length() > DESCRIPTION_LENGTH){
            throw new CustomException("Description exceed required amount of characters");
        } else if (project.getDescription() == null || project.getDescription().isEmpty()){
            throw new CustomException("Cannot have empty description");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateCredentials(DatabaseObject project) throws CustomException {
        int i = 0;
        if (project.getCredentials().isEmpty()){
            throw new CustomException("Credentials must not be empty.");
        } else {
            while (i < project.getCredentials().size()){
                if (project.getCredentials().get(i).length() < CREDENTIALS_LENGTH){
                    throw new CustomException("Credentials must exceed " + CREDENTIALS_LENGTH + " characters.");
                }
                i++;
            }
            return true;
        }
    }
}