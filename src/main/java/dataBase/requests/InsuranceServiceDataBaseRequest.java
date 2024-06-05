package dataBase.requests;

import dataBase.DataBaseConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getApplicationId(String applicationId) throws SQLException {
        String getApplicationIdFromDB = null;
        String sql = "SELECT id FROM applications_basic WHERE id = ?::uuid";
        PreparedStatement preparedStatement = DataBaseConnector.getInstance().getConnectionDB().prepareStatement(sql);
        preparedStatement.setString(1, applicationId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            getApplicationIdFromDB = resultSet.getString("id");
        }
        LOG.info(String.format("Получена заявка на страхование №:" + applicationId));
        return getApplicationIdFromDB;
    }
}
