package comp3350.ppms.logic;

import comp3350.ppms.domain.Project;
/**
 * The validate project class implements all the methods required
 * to verify if the project input by users fit in a certain criteria.
 */
public class ValidateProject {

    private static final int NAME_LENGTH = 16;
    private static final int DESCRIPTION_LENGTH = 256;
    private static final int CREDENTIAL_LENGTH = 2;

    public static boolean validateAll(Project project) throws CustomException{
        validateUUID(project);
        validateName(project);
        validateDescription(project);
        validateCredentials(project);
        return true;
    }

    //@Override
    public static boolean validateUUID(Project project) throws CustomException{
        if (project.getProjectID() == null){
            throw new CustomException("No Id for project");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateName(Project project) throws CustomException {
        if (project.getProjectName().length() > NAME_LENGTH){
            throw new CustomException("Name exceed required amount of characters");
        } else if (project.getProjectName() == null || project.getProjectName() == ""){
            throw new CustomException("Cannot have empty name");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateDescription(Project project) throws CustomException {
        if (project.getProjectDescription().length() > DESCRIPTION_LENGTH){
            throw new CustomException("Description exceed required amount of characters");
        } else if (project.getProjectDescription() == null || project.getProjectDescription().isEmpty()){
            throw new CustomException("Cannot have empty description");
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateCredentials(Project project) throws CustomException {
        int i = 0;
        if (project.getProjectCredentials().isEmpty()){
            throw new CustomException("projects must list credentials required");
        } else {
            while (i < project.getProjectCredentials().size()){
                if (project.getProjectCredentials().get(i).length() < CREDENTIAL_LENGTH){
                    throw new CustomException("Credentials must exceed " + CREDENTIAL_LENGTH + " characters.");
                }
                i++;
            }
            return true;
        }
    }
}
