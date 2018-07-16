package comp3350.ppms.logic;

import comp3350.ppms.domain.CredentialError;
import comp3350.ppms.domain.CustomException;
import comp3350.ppms.domain.Project;
import comp3350.ppms.domain.ProjectDescriptionError;
import comp3350.ppms.domain.ProjectNameError;
import comp3350.ppms.presentation.allusers.ErrorMessaging;

/**
 * The validate project class implements all the methods required
 * to verify if the project input by users fit in a certain criteria.
 */
public class ValidateProject {

    private static final int NAME_LENGTH = 16;
    private static final int DESCRIPTION_LENGTH = 256;
    private static final int CREDENTIAL_LENGTH = 1;

    public static boolean validateAll(Project project) throws CustomException {
        validateUUID(project);
        validateOwner(project);
        validateName(project);
        validateDescription(project);
        validateCredentials(project);
        return true;
    }

    //@Override
    public static boolean validateUUID(Project project) throws CustomException{
        if (project.getProjectID() == null){
            throw new CustomException(ErrorMessaging.NO_PROJECT_ID_ERROR);
        } else {
            return true;
        }
    }

    public static boolean validateOwner(Project project) throws CustomException {
        if(project.getProjectOwner() == null) {
            throw new CustomException(ErrorMessaging.NO_PROJECT_OWNER_NAME);
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateName(Project project) throws CustomException {
        if (project.getProjectName().length() > NAME_LENGTH){
            throw new ProjectNameError(ErrorMessaging.EXCEED_CHARACTER_MAX_ERROR);
        } else if (project.getProjectName() == null || project.getProjectName().equals("")){
            throw new ProjectNameError(ErrorMessaging.EMPTY_NAME_ERROR);
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateDescription(Project project) throws CustomException {
        if (project.getProjectDescription().length() > DESCRIPTION_LENGTH){
            throw new ProjectDescriptionError(ErrorMessaging.EXCEEDED_DESCRIPTION_ERROR);
        } else if (project.getProjectDescription() == null || project.getProjectDescription().isEmpty()){
            throw new ProjectDescriptionError(ErrorMessaging.EMPTY_DESCRIPTION_ERROR);
        } else {
            return true;
        }
    }

    //@Override
    public static boolean validateCredentials(Project project) throws CustomException {
        int i = 0;
        if (project.getProjectCredentials().isEmpty()){
            throw new CredentialError(ErrorMessaging.PROJECT_CREDENTIALS_ERROR);
        } else {
            while (i < project.getProjectCredentials().size()){
                if (project.getProjectCredentials().get(i).length() < CREDENTIAL_LENGTH){
                    throw new CredentialError(ErrorMessaging.CRED_TOO_SHORT_PT1 + CREDENTIAL_LENGTH + ErrorMessaging.CRED_TOO_SHORT_PT2);
                }
                i++;
            }
            return true;
        }
    }
}
