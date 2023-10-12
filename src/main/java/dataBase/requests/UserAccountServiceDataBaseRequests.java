package dataBase.requests;

import dataBase.DataBaseConnector;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static constant.DataBaseConstants.USER_ACCOUNT_SERVICE_DB;
import static constant.DataBaseConstants.USER_SERVICE_DB;

public class UserAccountServiceDataBaseRequests {

    public static String receivingIdCustomerByPhoneNumber(String phoneNumber) {

        JdbcTemplate jdbcTemplate = DataBaseConnector.getJdbcTemplate(USER_SERVICE_DB);
        String sql = "SELECT customer_id FROM public.client WHERE mobile_phone=?";
        String idCustomer = jdbcTemplate.queryForObject(sql, String.class, phoneNumber);
        System.out.println(idCustomer);
        return idCustomer;
    }

    public static String receivingVerificationCodeByIdCustomer(String phoneNumber, String idCustomer) {
        JdbcTemplate jdbcTemplate1 = DataBaseConnector.getJdbcTemplate(USER_ACCOUNT_SERVICE_DB);
        String sql2 = "SELECT last_verification_code " +
                "FROM user_profile WHERE customer_id=?::uuid";
        String verificationCode = jdbcTemplate1.queryForObject(sql2, String.class, idCustomer);
        System.out.println(verificationCode);
        return verificationCode;

    }
}