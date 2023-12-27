package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.DEPOSIT_SERVICE_DB;
import static dataBase.DataBaseConnector.getDBConnection;

public class DepositServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getCapitalisationValue(int id, int termTime) {
        String sql = "SELECT interest_rate FROM months_percent_borders WHERE deposit_product_id = " + id + "  AND " + termTime + " BETWEEN lower_months_border AND upper_months_border";
        String value = getDBConnection(DEPOSIT_SERVICE_DB).queryForObject(sql, String.class);
        return value;
    }
}
