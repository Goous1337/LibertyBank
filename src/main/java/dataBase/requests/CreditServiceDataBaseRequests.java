package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import pojo.creditService.CreditInfo;

import java.util.List;

import static constant.LibertyServiceName.CREDIT_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class CreditServiceDataBaseRequests {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static CreditInfo getCreditInfoByName(String creditName) {
        String sql = String.format("SELECT credit_product.id,credit_product.min_sum,credit_product.max_sum,credit_product.min_period_months,credit_product.max_period_months FROM credit_product WHERE name=?");
        CreditInfo creditInfo = (CreditInfo) getDBConnection(CREDIT_SERVICE).queryForObject(sql, new Object[]{creditName}, new BeanPropertyRowMapper(CreditInfo.class));
        LOG.info(String.format("Получены данные по кредиту %s", creditName));
        return creditInfo;
    }

    public static void deleteCreditById(int id) {
        String sql = "DELETE FROM credit_order WHERE id=?";
        getDBConnection(CREDIT_SERVICE).update(sql, id);
        LOG.info(String.format("Заказ по кредиту с id = %s был удален", id));
    }

    public static List<Integer> getCreditsOrders() {
        String sql = "SELECT id FROM credit_order";
        getDBConnection(CREDIT_SERVICE).queryForList(sql, Integer.class);
        LOG.info("Получен список id заказов на кредит");
        return getDBConnection(CREDIT_SERVICE).queryForList(sql, Integer.class);
    }

    public static Integer getCreditId(String creditName) {
        String sql = "SELECT id FROM credit_product WHERE name =?";
        LOG.info(String.format("Получен id кредита %s.", creditName));
        return getDBConnection(CREDIT_SERVICE).queryForObject(sql, Integer.class, creditName);
    }
}
