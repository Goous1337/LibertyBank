package dataBase.requests;

import static constant.LibertyServiceName.USER_ACCOUNT_SERVICE;
import static constant.LibertyServiceName.USER_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class UserAccountServiceDataBaseRequests {

    public static String receivingIdCustomerByPhoneNumber(String phoneNumber) {
        String sql = "SELECT customer_id FROM public.client WHERE mobile_phone=?";
        String idCustomer = getDBConnection(USER_SERVICE).queryForObject(sql, String.class, phoneNumber);
        System.out.println(idCustomer);
        return idCustomer;
    }

    public static String receivingVerificationCodeByIdCustomer(String idCustomer) {
        String sql = "SELECT last_verification_code " +
                "FROM user_profile WHERE customer_id=?::uuid";
        String verificationCode = getDBConnection(USER_ACCOUNT_SERVICE).queryForObject(sql, String.class, idCustomer);
        System.out.println(verificationCode);
        return verificationCode;
    }
}