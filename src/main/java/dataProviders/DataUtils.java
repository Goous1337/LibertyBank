package dataProviders;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;

import static constant.LibertyServiceName.*;
import static dataBase.DataBaseConnector.getDBConnection;

public class DataUtils {

    public static String getNonExistentClientPhoneNumber() {
        String newPhoneNumber;
        do {
            newPhoneNumber = RandomStringUtils.randomNumeric(11);
        } while (isPhoneNumberExistingInClientDB(newPhoneNumber));
        return newPhoneNumber;
    }

    private static boolean isPhoneNumberExistingInClientDB(String phoneNumber) {
        String selectPhoneNumbersCountSQL = "SELECT COUNT(*) FROM public.client WHERE mobile_phone = ?";
        Integer phoneNumberCount = getDBConnection(USER_SERVICE)
                .queryForObject(selectPhoneNumbersCountSQL, Integer.class, phoneNumber);
        return phoneNumberCount != null && phoneNumberCount > 0;
    }

    public static HashMap<String,String> getPassportWithCustomerStatus(Integer customerStatus) {
        HashMap<String,String> passport = new HashMap<>();
        String selectPassportIdWithCustomerStatus = "SELECT passport_id FROM public.customer WHERE customer_status = '"+customerStatus+"'";
        Integer passportId = getDBConnection(CUSTOMER_SERVICE)
                .queryForList(selectPassportIdWithCustomerStatus, Integer.class).get(0);
        String selectPassportSeries = "SELECT series FROM public.passport WHERE id = '"+passportId+"'";
        String selectPassportNumber = "SELECT number FROM public.passport WHERE id = '"+passportId+"'";
        passport.put("series",getDBConnection(CUSTOMER_SERVICE)
                .queryForList(selectPassportSeries, String.class).get(0));
        passport.put("number",getDBConnection(CUSTOMER_SERVICE)
                .queryForList(selectPassportNumber, String.class).get(0));
        return passport;
    }
    public static String getCustomerIdWithCustomerStatus(boolean push_notification) {
        String sql = "SELECT customer_id FROM public.customer WHERE push_notification = ?";
        return getDBConnection(CUSTOMER_SERVICE)
                .queryForList(sql, String.class,push_notification).get(0);
    }
}