package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constant.LibertyServiceName.*;
import static dataBase.DataBaseConnector.getDBConnection;

public class CustomerService_2_0_DataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getCustomerIdByMobilePhone(String mobilePhone) {
        String sql = "SELECT customer_id FROM customer WHERE mobile_phone =?";
        String customerId = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, String.class, mobilePhone);
        LOG.info(String.format("Получен id пользователя: %s , по мобильному телефону: %s", customerId, mobilePhone));
        return customerId;
    }

    public static String getCustomerLastVerificationCodeById(String id) {
        String sql = "SELECT last_verification_code FROM user_profile WHERE customer_id =?::uuid";
        String verificationCode = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, String.class, id);
        LOG.info(String.format("Получен код верификации: %s , по id: %s", verificationCode, id));
        return verificationCode;
    }

    public static void resetTimerOfVerificationCodeById(String id) {
        String sql = "UPDATE user_profile SET sms_sent_counter = 0 WHERE customer_id =?::uuid";
        getDBConnection(CUSTOMER_SERVICE_DB_2_0).update(sql, id);
        LOG.info(String.format("Счетчик таймера по id: %s , был сброшен на начальное значение (30 секунд.)", id));
    }

    public static void deleteDataById(String id) {
        String sql = "DELETE FROM  user_token WHERE customer_id =?::uuid; DELETE FROM user_profile WHERE customer_id =?::uuid";
        getDBConnection(CUSTOMER_SERVICE_DB_2_0).update(sql, id, id);
        LOG.info(String.format("Данные пользователя по id: %s , были удалены", id));
    }

    public static List<String> getAllCustomerId() {
        String sql = "SELECT customer_id FROM user_profile";
        List<String> customerIdList = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForList(sql, String.class);
        LOG.info(String.format("Получен список id из таблицы user_profile"));
        return customerIdList;
    }

    public static String getCustomerIdByPassportId(int passportId) {
        String sql = "select customer_id\n" +
                "from customer \n" +
                "where passport_id=?";
        String customerId = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, String.class, passportId);
        return customerId;
    }

    public static String getCustomerPasswordById(String id) {
        String sql = "SELECT password FROM user_profile WHERE customer_id =?::uuid";
        String password = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, String.class, id);
        LOG.info(String.format("Получен пароль пользователя по id %s", id));
        return password;
    }

    public static void updateLastCodeExpiration(String id) throws SQLException {
        String sql = "update user_profile set last_code_expiration = NOW() - interval '3 hour' where customer_id =?::uuid";
        int update = getDBConnection(CUSTOMER_SERVICE_DB_2_0).update(sql, id);
        if (update != 1) {
            throw new SQLException("Не удалось обновить таблицу");
        }
        LOG.info(String.format("Таблица last_code_expiration была успешно обновлена по id %s", id));
    }

    public static Integer getWrongAttemptsByCustomerId(String id) {
        String sql = "SELECT wrong_attempts FROM user_profile WHERE customer_id =?::uuid";
        Integer wrongAttempts = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, Integer.class, id);
        LOG.info(String.format("Получены данные из колонки wrong_attempts по id %s", id));
        return wrongAttempts;
    }

    public static Integer getSmsSendCounterByCustomerId(String id) {
        String sql = "SELECT sms_sent_counter FROM user_profile WHERE customer_id =?::uuid";
        Integer smsSendCounter = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, Integer.class, id);
        LOG.info(String.format("Получены данные из колонки sms_sent_counter по id %s", id));
        return smsSendCounter;
    }

    public static void updateWrongAttemptsById(String id) {
        String sql = "UPDATE user_profile SET wrong_attempts = 0 WHERE customer_id =?::uuid";
        getDBConnection(CUSTOMER_SERVICE_DB_2_0).update(sql, id);
        LOG.info(String.format("Значение  в колонке wrong_attempts обновлено на значение 0 по id %s", id));
    }

    public static String receivingCustomerIdWithNotificationStatusFalse() {
        String sql = "SELECT customer_id FROM public.customer WHERE push_notification = false LIMIT 1";
        String customerID = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, String.class);
        LOG.info(String.format("получен customerId %s", customerID));
        return customerID;
    }

    public static String receivingCustomerIdWithNotificationStatusTrue() {
        String sql = "SELECT customer_id FROM public.customer WHERE push_notification = true LIMIT 1";
        String customerID = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, String.class);
        LOG.info(String.format("получен customerId %s", customerID));
        return customerID;
    }

    public static Boolean checkNotificationStatusByCustomerId(String customerId) {
        String sql = "SELECT push_notification FROM customer WHERE customer_id ='"+customerId+"';";
        Boolean push_notification = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, Boolean.class);
        LOG.info(String.format("получен push_notification: %s по customer_id: %s", push_notification, customerId));
        return push_notification;
    }

    public static String getMobilePhoneByCustomerId(String id){
        String sql = "select mobile_phone\n" +
                "from customer\n" +
                "where customer_id =?::uuid";
        String mobile_phone = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, String.class, id);
        return mobile_phone;
    }

    public static String getPasswordByCustomerId(String customerId){
        String sql = "select \"password\"  \n" +
                "from user_profile up \n" +
                "where customer_id = 'd54eb158-7499-4bda-bafb-d4bd965a1985'";
        String password = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, String.class);
        LOG.info(String.format("Получен пароль пользователя  id: %s", customerId));
        return password;
    }
    public static void updatePasswordInUserProfileTableIsNull(String idCustomer) {
        String sql = "UPDATE user_profile SET password = NULL WHERE customer_id =?::uuid";
        getDBConnection(CUSTOMER_SERVICE_2_0).update(sql, idCustomer);
        LOG.info(String.format("Удален пароль пользователя  id: %s", idCustomer));
    }

    public static Integer getCustomerPassportIdByMobilePhone(String mobilePhone) {
        String sql = "SELECT passport_id FROM customer WHERE mobile_phone=?";
        Integer passportId = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, Integer.class, mobilePhone);
        return passportId;
    }

    public static List<String> getCustomerPassportSeriesAndNumberByPassportId(Integer passportId) {
        List<String> identityDocNumber = new ArrayList<>();
        String sqlSeries = "SELECT passport.series FROM passport WHERE id =?";
        String sqlNumber = "SELECT passport.number FROM passport WHERE id =?";
        String passportSeries = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sqlSeries, String.class, passportId);
        identityDocNumber.add(passportSeries);
        String passportNumber = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sqlNumber, String.class, passportId);
        identityDocNumber.add(passportNumber);
        return identityDocNumber;
    }

    public static String getBlockedUserMobilePhone() {
        String sql = "SELECT mobile_phone FROM customer WHERE customer_status = '0' LIMIT 1";
        String mobilePhone = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, String.class);
        return mobilePhone;
    }

    public static Integer getBlockedUserPassportId() {
        String sql = "SELECT passport_id FROM customer WHERE customer_status = '0' LIMIT 1";
        Integer passportId = getDBConnection(CUSTOMER_SERVICE_DB_2_0).queryForObject(sql, Integer.class);
        return passportId;
    }
}
