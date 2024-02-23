package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.DEPOSIT_SERVICE_DB;
import static dataBase.DataBaseConnector.getDBConnection;

public class DepositServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getCapitalisationValue(int id, int termTime) {
        String sql = "SELECT interest_rate FROM months_percent_borders WHERE deposit_product_id = %s AND %s BETWEEN lower_months_border AND upper_months_border";
        return getDBConnection(DEPOSIT_SERVICE_DB).queryForObject(String.format(sql, id, termTime), String.class);
    }

    public static Integer getDepositProductId(String currencyCode) {
        String sql = "SELECT id FROM deposit_product WHERE currency_code = ?::varchar LIMIT 1";
        LOG.info(String.format("Получен id продукта по currencyCode %s", currencyCode));
        return getDBConnection(DEPOSIT_SERVICE_DB).queryForObject(sql, Integer.class, currencyCode);
    }
}
