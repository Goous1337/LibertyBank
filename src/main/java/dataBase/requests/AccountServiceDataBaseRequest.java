package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.ACCOUNT_SERVICE_DB;
import static dataBase.DataBaseConnector.getDBConnection;

public class AccountServiceDataBaseRequest {

    public static final String CUSTOMER_ID = "c1c12bfb-ee07-42ee-9171-b705cebd6309";

    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getAccountId(String accountStatus) {
        String sql = "SELECT id FROM account WHERE customer_id = ' " + CUSTOMER_ID + " ' AND status = '" + accountStatus + "' AND balance = 0 LIMIT 1";
        String accountId = getDBConnection(ACCOUNT_SERVICE_DB).queryForObject(sql, String.class);
        LOG.info(String.format("Получен id счета: %s", accountId));
        return accountId;
    }

    public static String getAccountStatus(String accountId) {
        String sql = "SELECT status FROM account WHERE id = '" + accountId + "';";
        String accountStatus = getDBConnection(ACCOUNT_SERVICE_DB).queryForObject(sql, String.class);
        LOG.info(String.format("Получен статус счета: %s", accountStatus));
        return accountStatus;
    }
}
