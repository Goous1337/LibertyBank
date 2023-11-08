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
        String selectPassportIdWithCustomerStatus = "SELECT passport_id FROM public.customer WHERE customer_status = '"+customerStatus+"'" +
                " AND patronymic NOT LIKE 'Реакт%' AND patronymic NOT LIKE 'Айос%' AND patronymic NOT LIKE 'Андроид%'";
        Integer passportId = getDBConnection(CUSTOMER_SERVICE)
                .queryForList(selectPassportIdWithCustomerStatus, Integer.class).get(0);
        String selectPassportSeries = "SELECT series FROM public.passport WHERE id = '"+passportId+"'";
        String selectPassportNumber = "SELECT number FROM public.passport WHERE id = '"+passportId+"'";
        passport.put("series",getDBConnection(CUSTOMER_SERVICE)
                .queryForList(selectPassportSeries, String.class).get(0));
        passport.put("number",getDBConnection(CUSTOMER_SERVICE)
                .queryForList(selectPassportNumber, String.class).get(0));
        passport.put("третье значение", "3" );
        return passport;
    }

}