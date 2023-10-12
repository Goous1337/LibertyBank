package dataProviders;

import dataBase.DataBaseConnector;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import static constant.DataBaseConstants.USER_ACCOUNT_SERVICE_DB;

public class DataUtils {

    static JdbcTemplate jdbcTemplate = DataBaseConnector.getJdbcTemplate(USER_ACCOUNT_SERVICE_DB);

    public static String getNonExistentClientPhoneNumber() {
        String newPhoneNumber;
        do {
            newPhoneNumber = RandomStringUtils.randomNumeric(11);
        } while (isPhoneNumberExistingInClientDB(newPhoneNumber));
        return newPhoneNumber;
    }

    private static boolean isPhoneNumberExistingInClientDB(String phoneNumber) {
        String selectPhoneNumbersCountSQL = "SELECT COUNT(*) FROM public.client WHERE mobile_phone = ?";
        Integer phoneNumberCount = jdbcTemplate.queryForObject(selectPhoneNumbersCountSQL, Integer.class, phoneNumber);
        return phoneNumberCount != null && phoneNumberCount > 0;
    }

}
