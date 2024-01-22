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
}
