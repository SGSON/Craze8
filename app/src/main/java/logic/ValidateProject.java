package logic;

import domain.DatabaseObject;

public class ValidateProject {
    private static final int nameLength = 16;
    private static final int descLength = 256;
    private static final int credLength = 5;

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
        if (project.getName().length() > nameLength){
            throw new CustomException("Name exceed required amount of characters");
        } else if (project.getName() == null || project.getName() == ""){
            throw new CustomException("Cannot have empty name");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateDescription(DatabaseObject project) throws CustomException {
        if (project.getDescription().length() > descLength){
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
                if (project.getCredentials().get(i).length() < credLength){
                    throw new CustomException("Credentials must exceed " + credLength + " characters.");
                }
                i++;
            }
            return true;
        }
    }
}
