package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.*;
import static dataBase.DataBaseConnector.getDBConnection;

public class CustomerServiceDataBaseRequest {

    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String receivingEmailCustomerByCustomerId(String customerId) {
        String sql = "SELECT email FROM public.client WHERE customer_id=?::uuid";
        String email = getDBConnection(CUSTOMER_SERVICE).queryForObject(sql, String.class, customerId);
        LOG.info(String.format("По id пользователя %s получен email %s", customerId, email));
        return email;
    }

}