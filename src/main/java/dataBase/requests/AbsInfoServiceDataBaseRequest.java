package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.ABS_INFO_SERVICE;

import static dataBase.DataBaseConnector.getDBConnection;

public class AbsInfoServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static void deleteNewAtm(String atm_number) {
        String sql = "DELETE FROM atm WHERE atm_number=?";
        getDBConnection(ABS_INFO_SERVICE).update(sql, atm_number);
        LOG.info(String.format("Удален банкомат номером: %s", atm_number));
    }
}
