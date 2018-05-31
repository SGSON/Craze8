# __Application Architecture__  
## Presentation package
###### All users  
    * MainActivity.java/xml
        - Activity for first screen the user will encounter
    * LoginActivity.java/xml
        - Login Actvity screen for all users     
    * SignUpActivity.java/xml  
        - Sign up Activity screen for all users
###### General user view  
    * UserSearchForProjectActivity.java/xml  
        - Activity for general users to find projects
    * UserInterestedProjectsListActivity.java/xml
        - Activity for users to view the projects they expressed interest in  
    * UserProjectSummaryViewActivity.java/xml  
        - Shorter project summary view meant to get the general user interested in viewing more about the project  
    * UserProjectDetailViewActivity.java/xml  
        - Detailed project view for general users (after they have clicked on the summary, they are brought here)
    * UserProfileCreateActivity.java/xml
        - Activity used for general users to complete their profile
    * UserProfileEditActivity.java/xml
        - Activty used for general users to update/edit their profile
###### Project owner view  
    * OwnerProjectCreateActivity.java/xml 
        - Activity for project owners to create their project  
    * OwnerProjectEditActivity.java/xml
        - Activity for project owners to edit/update their project listing
    * OwnerViewMatchesActivity.java/xml
        - Activity for project owners to view their mathes for the project
    * OwnerViewMatchContactActivity.java/xml
        - Activity for project owners to view the contact details of confirmed matches  
# Logic package  
    * Determine user profiles that match project specifications  
    * Determine projects that match user qualifiticaions  
    * AccountManager  
    * ProjectManager  
    * UserManager  
    * MatchManager  
# Persistence package  
    * Store/Retrieve project in DB  
    * Store/Retrieve user profile in DB  
# Domain package  
    * Users  
    * Projects  
    * List of matches  
    * Project owners  
