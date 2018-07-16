# __Application Architecture__  

![Image of achritecture diagram](/ApplicationArchitecture.png)

##   Presentation package  

###### All users views  
        
*Currently implemented*  

    * ErrorMessaging.java
        - Responsible for strings to display for error handling
    * LoginActivity.java/xml
        - Login Activity screen for all users   
    * MainActivity.java/xml
        - Landing page activity after a User signs up or logs in
    * SignUpActivity.java/xml  
        - Sign up Activity screen for all users
    
###### General user views  
*Currently implemented*  

    * ProjectListActivity.java/xml
        - Activity used to view a list of projects that have been created
    * UserInfoActivity.java/xml
        - Activity used to display details about a particular user
    * UserInterestedProjectsListActivity.java/xml
        - Activity to display to the user the projests they've expressed interest in
    * UserProjectDetailedViewActivity.java/xml
        - Detailed project view for general users (after they have clicked on the summary, they are brought here)

*Not yet implemented*  

    * UserProjectSummaryViewActivity.java/xml  
        - Shorter project summary view meant to get the general user interested in viewing more about the project  
    * UserProfileCreateActivity.java/xml
        - Activity used for general users to complete their profile
    * UserProfileEditActivity.java/xml
        - Activity used for general users to update/edit their profile
        
###### Project owner views  
*Currently Implemented*  

    * CreateProjectActivity.java/xml 
        - Activity for project owners to create their project  
    * InterestedUsersListActivity.java/xml
        - Activity used to display users interested in a project
    * MatchedUsersListActivity.java/xml
        - Activity used to display the list of mutually selected users for a project

*Not yet implemented*  

    * OwnerProjectEditActivity.java/xml
        - Activity for project owners to edit/update their project listing
    * OwnerViewMatchContactActivity.java/xml
        - Activity for project owners to view the contact details of confirmed matches  
    
# Logic package  
*Currently implemented*  

    * MatchManager.java
        - Takes care of matching processes
    * ProjectManager.java  
        - Takes care of the project processes
             - Project creation/editing
             - Project retrieval
    * UserManager.java
        - Takes care of user processes
            - User account creation/editing
    * ValidateProject.java
        - Verifies if the project input by users fits into a certain criteria
    * ValidateUser.java
        - Verifies valid User account data is entered
*Yet to be implemented*

    * MatchingEngine.java  
        - Determine user profiles that match project specifications  
        - Determine projects that match user qualifications  
    * AccountManager.java  
        - Takes care of the general user and project owner account processes
            - account creation
            - logging in
            - any profile related processes
# Persistence package 
*Currently implemented*  

    * ProjectDatabase.java
        - Store/Retrieve project in DB 
        - Works with **ProjectPersistenceHSQLDB.java** to persist Project data
    * UserDatabase.java  
        - Store/Retrieve user account details in DB  
        - Works with **UserDatabaseHSQLDB.java** to persist User data
*Yet to be implemented*

    * MatchDatabase.java
        - Store/Retrieve match related details
# Domain package  
*Currently implemented*

    * Messages.java
        - Shows an error message dialog if an exception or warning occurs
    * Project.java  
        - passed between comp3350.ppms.presentation, comp3350.ppms.logic and comp3350.ppms.persistence layer
    * CustomException.java
        - used in conjuction with Messages.java to provide appropraite error messaging
        + UsernameError.java
            - Used for Username related errors
        + PasswordError.java
            - Used for Password related errors
        + ProjectNameError.java
            - Used for Project name related rerrors
        + ProjectDescriptionError.java
            - Used for Project Description related errors
        + CredentialsError.java
            - Used for Credential related errors
*Yet to be implemented*  

    * User(parent).java  
        - parent abstract account  
        + UserGeneral(child).java  
            - An account for general users  
        + UserOwner(child).java  
            - An account for project owners  
    * Interest.java  
        - Used when there is a potential match (an expression of interest by the user, yet needs to be reviewed by project owner)  
    * Match.java  
        - Used when both general user and project owner have expressed interest  

