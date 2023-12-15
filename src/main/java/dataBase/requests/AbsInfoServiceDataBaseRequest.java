package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.*;
import static dataBase.DataBaseConnector.getDBConnection;

public class AbsInfoServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static void deleteNewAtm(String atm_number) {
        String sql = "DELETE FROM atm WHERE atm_number=?";
        getDBConnection(ABS_INFO_SERVICE).update(sql, atm_number);
        LOG.info(String.format("Удален банкомат номером: %s", atm_number));
    }

    public static void deleteNewBankBranch(String bankBranchNumber) {
        String sql = "DELETE FROM bank_office WHERE office_number=?";
        getDBConnection(ABS_INFO_SERVICE).update(sql, bankBranchNumber);
        LOG.info(String.format("Удален филиал банка номером: %s", bankBranchNumber));
    }

    public static String getNewBankBranchId(String numberBank) {
        String sql = "SELECT uuid FROM bank_office WHERE office_number=?";
        String uuid = (getDBConnection(ABS_INFO_SERVICE).queryForObject(sql, String.class, numberBank));
        LOG.info(String.format("Получен uuid банка: %s: %s", numberBank, uuid));
        return uuid;
    }

    public static String getNewBankBranchUuid(String uuid) {
        String sql = "SELECT uuid FROM bank_office WHERE uuid=?::uuid";
        String actualUuid = (getDBConnection(ABS_INFO_SERVICE).queryForObject(sql, String.class, uuid));
        LOG.info(String.format("Получен uuid банка: %s", uuid));
        return actualUuid;
    }
}
