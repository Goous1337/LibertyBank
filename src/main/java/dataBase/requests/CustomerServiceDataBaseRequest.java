package dataBase.requests;

import static constant.LibertyServiceName.*;
import static dataBase.DataBaseConnector.getDBConnection;

public class CustomerServiceDataBaseRequest {

    public static String receivingEmailCustomerByCustomerId(String customerId) {
        String sql = "SELECT email FROM public.client WHERE customer_id=?::uuid";
        return getDBConnection(CUSTOMER_SERVICE).queryForObject(sql, String.class, customerId);
    }
}
