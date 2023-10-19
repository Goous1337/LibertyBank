package dataProviders;

import org.apache.commons.lang3.RandomStringUtils;

import static constant.LibertyServiceName.CUSTOMER_SERVICE;
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
        Integer phoneNumberCount = getDBConnection(CUSTOMER_SERVICE)
                .queryForObject(selectPhoneNumbersCountSQL, Integer.class, phoneNumber);
        return phoneNumberCount != null && phoneNumberCount > 0;
    }

}