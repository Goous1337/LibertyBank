package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.ABS_CLIENT_SERVICE;
import static constant.LibertyServiceName.ABS_INFO_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class AbsClientServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getCustomerUuid() {
        String sql = "select customer_uuid\n" +
                "from customer \n" +
                "where id=3";
        String customer_uuid = getDBConnection(ABS_CLIENT_SERVICE).queryForObject(sql, String.class);
        return customer_uuid;
    }
}
