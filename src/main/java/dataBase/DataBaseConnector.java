package dataBase;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static property.PropertiesReader.getPropertyValue;

@Data
public class DataBaseConnector {
    private static Map<String, JdbcTemplate> jdbcTemplateMap = new HashMap<>();

    private String dbName;
    private String host;
    private String port;
    private String user;
    private String password;

    private DataBaseConnector(String dbName, String host, String port, String user, String password) {
        this.dbName = dbName;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public static JdbcTemplate getJdbcTemplate(String dbName) {
        if (!jdbcTemplateMap.containsKey(dbName)) {
            DataBaseConnector dataBaseConnector = new DataBaseConnector(dbName, getPropertyValue("db_host"),
                    getPropertyValue("db_port"), getPropertyValue("db_user"), getPropertyValue("db_pass"));
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataBaseConnector.dataSource());
            jdbcTemplateMap.put(dbName, jdbcTemplate);
        }
        return jdbcTemplateMap.get(dbName);
    }

    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(getConnectionUrl());
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    private String getConnectionUrl() {
        return getPropertyValue("db_url") + host + ":" + port + "/" + dbName;
    }

}