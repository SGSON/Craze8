package comp3350.ppms.persistence.hsqldb;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class HSQLDatabase {

    protected List<String> stringArrayConversion(Array input) {
        Object[] values;
        List<String> result = new ArrayList<>();

        if (input == null){
            return null;
        } else {
            try {
                values = (Object[]) input.getArray();
                for(int i = 0; i < values.length; i++) {
                    result.add(values[i].toString());
                }
                return result;
            } catch (SQLException e){
                throw new DatabaseException(e);
            }
        }
    }
}
