# __Application Architecture__  

![Image of achritecture diagram](/ApplicationArchitecture.png)

##   Presentation package  
###### All users views  
*Currently Implemented*  

    * MainActivity.java/xml
        - Activity for first screen the user will encounter
        
*Not yet implemented*  

    * LoginActivity.java/xml
        - Login Actvity screen for all users     
    * SignUpActivity.java/xml  
        - Sign up Activity screen for all users
###### General user views  
*Currently implemented*  

    * ProjectListActivity.java/xml
        - Activity used for project owners to view a list of projects that have been created.

*Not yet implemented*  

    * UserProjectSummaryViewActivity.java/xml  
        - Shorter project summary view meant to get the general user interested in viewing more about the project  
    * UserSearchForProjectActivity.java/xml  
        - Activity for general users to find projects
    * UserInterestedProjectsListActivity.java/xml
        - Activity for users to view the projects they expressed interest in  
    * UserProjectDetailViewActivity.java/xml  
        - Detailed project view for general users (after they have clicked on the summary, they are brought here)
    * UserProfileCreateActivity.java/xml
        - Activity used for general users to complete their profile
    * UserProfileEditActivity.java/xml
        - Activty used for general users to update/edit their profile
###### Project owner views  
*Currently Implemented*  

    * CreateProjectActivity.java/xml 
        - Activity for project owners to create their project  

*Not yet implemented*  

    * OwnerProjectEditActivity.java/xml
        - Activity for project owners to edit/update their project listing
    * OwnerViewMatchesActivity.java/xml
        - Activity for project owners to view their mathes for the project
    * OwnerViewMatchContactActivity.java/xml
        - Activity for project owners to view the contact details of confirmed matches  
# Logic package  
*Currently implemented*  

    * ProjectManager.java  
        - Takes care of the project processes
             - Project creation/editing
             - Project retrieval
             
    * CustomException.java
        - Handles custom exceptions thrown from methods with specific messages.
        
    * ValidateProject.java
        - Verifies if the project input by users fits into a certain criteria.
        
*Yet to be implemented*

    * MatchingEngine.java  
        - Determine user profiles that match project specifications  
        - Determine projects that match user qualifiticaions  
    * AccountManager.java  
        - Takes care of the general user and project owner account processes
            - account creation
            - logging in
    * UserProfileManager.java  
        - Takes care of the general user profile related processes
# Persistence package 
*Currently implemented*  

    * Database.java
        - Store/Retrieve project in DB  
*Yet to be implemented*

    * UserDatabase.java  
        - Store/Retrieve user profile in DB  
# Domain package  
*Currently implemented*  

    * DatabaseObject.java
        - Superclass for Project and Account objects
    * Project.java  
        - passed between presentation, logic and persistence layer  
*Yet to be implemented*  

    * Account(parent).java  
        - parent abstract account  
        + AccountUser(child).java  
            - An account for general users  
        + AccountOwner(child).java  
            - An account for project owners  
    * Interest.java  
        - Used when there is a potential match (an expression of interest by the user, yet needs to be reviewed by project owner)  
    * Match.java  
        - Used when both general user and project owner have expressed interest  

