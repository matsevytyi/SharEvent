package DATA_ACCESS;

import java.sql.SQLException;

public interface LoadEventsDataAccessInterface {
    public LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException;
}
