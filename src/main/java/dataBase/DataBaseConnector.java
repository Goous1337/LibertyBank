package dataBase;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import lombok.Data;
import static property.PropertiesReader.getPropertyValue;

@Data
public class    DataBaseConnector {

    private static String getConnectionUrl() {
        return "jdbc:postgresql://"
                + getPropertyValue("db_host") + ":"
                + getPropertyValue("db_port") + "/"
                + getPropertyValue("demo_base");
    }

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSource());
        }
        return jdbcTemplate;
    }

    private static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(getConnectionUrl());
        dataSource.setUsername(getPropertyValue("db_user"));
        dataSource.setPassword(getPropertyValue("db_pass"));
        return dataSource;
    }

}
