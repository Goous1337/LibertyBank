package dataBase;

import constant.LibertyServiceName;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static constant.DataBaseConstants.DB_INSURANCE_SERVICE;
import static constant.DataBaseConstants.DB_PASSWORD;
import static constant.DataBaseConstants.DB_USER;
import static constant.DataBaseConstants.POSTGRESQL_DB_DRIVER;
import static property.PropertiesReader.getPropertyValue;

@Data
public class DataBaseConnector {
    private static ThreadLocal<DataBaseConnector> instance = new ThreadLocal<>();
    private static Connection connection;
    private static DriverManagerDataSource dataSource;
    private static Map<String, JdbcTemplate> jdbcTemplateMap = new HashMap<>();

    private DataBaseConnector() throws SQLException {
        try {
            this.connection = DataBaseConnector.getDataBaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JdbcTemplate getDBConnection(LibertyServiceName service) {
        String serviceName = service.getServiceName();
        if (!jdbcTemplateMap.containsKey(serviceName)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource(getConnectionUrl(serviceName)));
            jdbcTemplateMap.put(serviceName, jdbcTemplate);
        }
        return jdbcTemplateMap.get(serviceName);
    }

    private static DataSource dataSource(String url) {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(POSTGRESQL_DB_DRIVER);
        dataSource.setUrl(url);
        dataSource.setUsername(getPropertyValue(DB_USER));
        dataSource.setPassword(getPropertyValue(DB_PASSWORD));
        return dataSource;
    }

    private static String getConnectionUrl(String serviceName) {
        return getPropertyValue("db_url") + serviceName + "?characterEncoding=utf8";
    }

    public static Connection getDataBaseConnection() throws SQLException {
        dataSource = new DriverManagerDataSource();
        dataSource.setUsername(getPropertyValue(DB_USER));
        dataSource.setPassword(getPropertyValue(DB_PASSWORD));
        dataSource.setUrl(getConnectionUrl(DB_INSURANCE_SERVICE));
        connection = DriverManager.getConnection(dataSource.getUrl(),
                dataSource.getUsername(), dataSource.getPassword());
        return connection;
    }

    public static DataBaseConnector getInstance() throws SQLException {
        if (null == instance.get()) {
            instance.set(new DataBaseConnector());
        }
        return instance.get();
    }

    public Connection getConnectionDB() {
        return connection;
    }
}
