package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;

import static constant.LibertyServiceName.USER_ACCOUNT_SERVICE;
import static constant.LibertyServiceName.USER_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class UserAccountServiceDataBaseRequests {

    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getIdCustomerByPhoneNumber(String phoneNumber) {
        String sql = "SELECT customer_id FROM client WHERE mobile_phone=?";
        try {
            return getDBConnection(USER_SERVICE).queryForObject(sql, String.class, phoneNumber);
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Пользователь с указанным номером телефона " + phoneNumber + " не найден");
            return null;
        }
    }

    public static String getVerificationCodeByIdCustomer(String idCustomer) {
        String sql = "SELECT last_verification_code " +
                "FROM user_profile WHERE customer_id=?::uuid";
        String verificationCode = getDBConnection(USER_ACCOUNT_SERVICE).queryForObject(sql, String.class, idCustomer);
        LOG.info("Получен код верификации пользователя id: " + idCustomer + ": " + verificationCode);
        return verificationCode;
    }

    public static void deleteVerificationCodeByIdCustomer(String idCustomer) {
        String sql = "DELETE FROM user_profile WHERE customer_id=?::uuid";
        getDBConnection(USER_ACCOUNT_SERVICE).update(sql, idCustomer);
        LOG.info("Удален код верификации пользователя id: " + idCustomer);
    }

}