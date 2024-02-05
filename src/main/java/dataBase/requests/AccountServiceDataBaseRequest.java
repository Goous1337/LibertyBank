package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.AccountServiceConstants.VALID_CUSTOMER_ID;
import static constant.LibertyServiceName.ACCOUNT_SERVICE_DB;
import static dataBase.DataBaseConnector.getDBConnection;

public class AccountServiceDataBaseRequest {

    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getAccountId(String accountStatus) {
        String sql = "SELECT id FROM account WHERE customer_id = '" + VALID_CUSTOMER_ID + "' AND status = '" + accountStatus + "' AND balance = 0 LIMIT 1";
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

    public static String getAccountName(String accountId) {
        String sql = "SELECT account_name FROM account WHERE id = '" + accountId + "';";
        String accountName = getDBConnection(ACCOUNT_SERVICE_DB).queryForObject(sql, String.class);
        LOG.info(String.format("Получено название счета: %s", accountName));
        return accountName;
    }
}
