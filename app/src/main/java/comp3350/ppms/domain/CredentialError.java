package comp3350.ppms.domain;

public class CredentialError extends CustomException {
    public CredentialError(String errorMsg){
        super(errorMsg);
    }
}
