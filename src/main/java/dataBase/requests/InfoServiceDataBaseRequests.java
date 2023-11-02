package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.LibertyServiceName.INFO_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class InfoServiceDataBaseRequests {

    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getCityByCityId(int cityId) {
        String sql = "SELECT city_name FROM city_dict WHERE id=?";
        String cityName = getDBConnection(INFO_SERVICE).queryForObject(sql, String.class, cityId);
        LOG.info(String.format("По id: %s получено название города: %s", cityId, cityName));
        return cityName;
    }

}