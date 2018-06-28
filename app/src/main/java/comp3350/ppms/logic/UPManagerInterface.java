package comp3350.ppms.logic;

import java.util.UUID;

import comp3350.ppms.domain.UP;

public interface UPManagerInterface {
    UP getUP(UUID userID);
}
