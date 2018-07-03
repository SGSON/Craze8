package comp3350.ppms.persistence;

public interface LoginInterface {

    //pass a username in text, return a uid or an error
    //This will submit a userNickname, the database will go through and check for it's nickname, and return the id if it's there
    public String checkForValidLogin(String nickName);


}
