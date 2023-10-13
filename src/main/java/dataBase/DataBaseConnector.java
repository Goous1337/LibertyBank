package dataBase;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import constant.LibertyServiceName;
import lombok.Data;

import static constant.DataBaseConstants.DB_PASSWORD;
import static constant.DataBaseConstants.DB_USER;
import static constant.DataBaseConstants.POSTGRESQL_DB_DRIVER;
import static property.PropertiesReader.getPropertyValue;

@Data
public class DataBaseConnector {
    private static Map<String, JdbcTemplate> jdbcTemplateMap = new HashMap<>();

    public static JdbcTemplate getDBConnection(LibertyServiceName service) {
        String serviceName = service.getServiceName();
        if (!jdbcTemplateMap.containsKey(serviceName)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource(getConnectionUrl(serviceName)));
            jdbcTemplateMap.put(serviceName, jdbcTemplate);
        }
        return jdbcTemplateMap.get(serviceName);
    }

    private static DataSource dataSource(String url) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(POSTGRESQL_DB_DRIVER);
        dataSource.setUrl(url);
        dataSource.setUsername(getPropertyValue(DB_USER));
        dataSource.setPassword(getPropertyValue(DB_PASSWORD));
        return dataSource;
    }

    private static String getConnectionUrl(String serviceName) {
        return getPropertyValue("db_url") + serviceName + "?characterEncoding=utf8";
    }

}