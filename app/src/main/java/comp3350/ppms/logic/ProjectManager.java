package comp3350.ppms.logic;

import java.util.ArrayList;
import java.util.UUID;

import comp3350.ppms.application.Service;
import comp3350.ppms.domain.Project;

import comp3350.ppms.persistence.ProjectDatabaseInterface;

/**
 * Here is the Project Manager class that will handle
 * project input to the ProjectProjectDatabase as well as project retrieval from
 * the ProjectProjectDatabase.
 */
public class ProjectManager implements ProjectManagerInterface{

    private ProjectDatabaseInterface projectsStub;

    public ProjectManager() {
        projectsStub = Service.getProjectDatabaseInterface();
    }

    public void insertProject(Project project) throws CustomException{
        ValidateProject.validateAll(project);
        final String credentials = generateListString(project.getProjectCredentials());
        final String interestedUsers = generateListString(project.getInterestedUsers());
        final String selectedUsers =  generateListString(project.getSelectedUsers());

        projectsStub.addProject(project.getProjectID().toString(), project, credentials, interestedUsers, selectedUsers);
    }

    public ArrayList<Project> getProjects(){
        return projectsStub.getProjectSequential();
    }

    public Project getProject(UUID id) { return projectsStub.getProject(id); }

    private String generateListString(ArrayList<String> credentials) {
        String currCredential;
        String listString = "";

        for(int i = 0; i < credentials.size(); i++) {
            currCredential = credentials.get(i);
            listString += currCredential;
            if(i != credentials.size() - 1) {
                listString += ",";
            }
        }

        return listString;
    }
}
