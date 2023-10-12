package dataBase;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static constant.DataBaseConstants.*;
import static property.PropertiesReader.getPropertyValue;

@Data
public class DataBaseConnector {
    // Хранилище для экземпляров JdbcTemplate

    private static Map<String, JdbcTemplate> jdbcTemplateMap = new HashMap<>();

    // Переменные для хранения информации о соединении
    private String dbName;
    private String host;
    private String port;
    private String user;
    private String password;

    // Приватный конструктор
    private DataBaseConnector(String dbName, String host, String port, String user, String password) {
        this.dbName = dbName;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    // Метод для получения экземпляра JdbcTemplate по имени
    public static JdbcTemplate getJdbcTemplate(String dbName) {
        if (!jdbcTemplateMap.containsKey(dbName)) {
            // Если экземпляр с указанным именем не существует, создаем новый
            DataBaseConnector dataBaseConnector = new DataBaseConnector(dbName, getPropertyValue("db_host"),
                    getPropertyValue("db_port"), getPropertyValue("db_user"), getPropertyValue("db_pass"));
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataBaseConnector.dataSource());
            jdbcTemplateMap.put(dbName, jdbcTemplate);
        }
        // Возвращаем существующий или новый экземпляр
        return jdbcTemplateMap.get(dbName);
    }

    // Метод для создания DataSource
    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(getConnectionUrl());
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    // Метод для получения URL соединения
    private String getConnectionUrl() {
        return getPropertyValue("db_url") + host + ":" + port + "/" + dbName;
    }

//    // Метод для получения значения свойства из файла конфигурации
//    private static String getPropertyValue(String propertyName) {
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream("config.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return properties.getProperty(propertyName);
//    }
}
