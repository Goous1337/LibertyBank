package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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

    public static Float getDepositAmountByProductId(int productId, String amount) {
        String sql = String.format("SELECT %s FROM deposit_product WHERE id = ?", amount);
        LOG.info(String.format("Получен %s срок вклада по id = %s", amount, productId));
        return getDBConnection(DEPOSIT_SERVICE_DB).queryForObject(sql, Float.class, productId);
    }

    public static String getDepositDurationByProductId(int productId, String duration) {
        String sql = String.format("SELECT %s FROM deposit_product WHERE id = ?", duration);
        LOG.info(String.format("Получен %s срок вклада по id = %s", duration, productId));
        return getDBConnection(DEPOSIT_SERVICE_DB).queryForObject(sql, String.class, productId);
    }

    public static void clearUserDepositProductsById(String customerId, int productId) {
        String sql = "DELETE FROM deposit WHERE customer_id = ?::uuid AND deposit_product_id =?";
        getDBConnection(DEPOSIT_SERVICE_DB).update(sql, customerId, productId);
        LOG.info(String.format("Депозиты пользователя по id = %s были удалены", customerId));
    }

    public static Float getInitialAmountById(String customerId, int productId) {
        String sql = "SELECT initial_amount FROM deposit WHERE customer_id = ?::uuid AND deposit_product_id = ?";
        LOG.info(String.format("Получена сумма вклада по id = %s ", customerId));
        return getDBConnection(DEPOSIT_SERVICE_DB).queryForObject(sql, Float.class, customerId, productId);
    }

    public static List<String> getAllCustomersId(int productId) {
        String sql = "SELECT customer_id FROM deposit WHERE deposit_product_id = ?";
        LOG.info(String.format("Получен список customer_id по id продукта = %s", productId));
        return getDBConnection(DEPOSIT_SERVICE_DB).queryForList(sql, String.class, productId);
    }
}
