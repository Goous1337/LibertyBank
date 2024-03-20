package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.CREDIT_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class CreditServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static Integer getFirstCreditProductId() {
        String sql = "SELECT id FROM credit_product LIMIT 1";
        Integer productId = getDBConnection(CREDIT_SERVICE).queryForObject(sql, Integer.class);
        LOG.info(String.format("Получен первый id в credit_product: %s", productId));
        return productId;
    }

    public static Integer getCountOfProducts() {
        String sql = "SELECT Count(id) FROM credit_product";
        Integer count = getDBConnection(CREDIT_SERVICE).queryForObject(sql, Integer.class);
        LOG.info(String.format("Получено количество записей в credit_product: %s", count));
        return count;
    }
}
