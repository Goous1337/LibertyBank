package dataBase.requests;

import static constant.LibertyServiceName.ABS_CLIENT_SERVICE_BD;
import static constant.LibertyServiceName.CUSTOMER_SERVICE_2_0;
import static dataBase.DataBaseConnector.getDBConnection;

public class CustomerService_2_0_DataBaseRequest {
    public static String getCustomerId() {
        String sql = "select customer_id\n" +
                "from customer \n" +
                "where passport_id=4";
        String customerId = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, String.class);
        return customerId;
    }
}
