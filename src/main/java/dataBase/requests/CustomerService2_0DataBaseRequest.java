package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.CUSTOMER_SERVICE_2_0;
import static dataBase.DataBaseConnector.getDBConnection;

public class CustomerService2_0DataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String receivingCustomerId() {
        String sql = "SELECT customer_id FROM public.customer LIMIT 1";
        String customerID = getDBConnection(CUSTOMER_SERVICE_2_0).queryForObject(sql, String.class);
        LOG.info(String.format("получен customerId %s", customerID));
        return customerID;
    }
}
