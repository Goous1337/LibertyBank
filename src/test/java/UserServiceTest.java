import api.BaseTest;
import dataBase.DataBaseConnector;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static constant.DataBaseConstants.USER_ACCOUNT_SERVICE_DB;

public class UserServiceTest extends BaseTest {

    @Test
    public void fetchData() {
        JdbcTemplate jdbcTemplate = DataBaseConnector.getJdbcTemplate(USER_ACCOUNT_SERVICE_DB);
        String sql = "SELECT name FROM manufacturers WHERE city = ?";
        System.out.println(jdbcTemplate.queryForObject(sql, String.class, "Moscow"));
    }
}