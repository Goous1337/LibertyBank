import org.junit.jupiter.api.Test;

import api.BaseTest;

import static constant.LibertyServiceName.USER_ACCOUNT_SERVICE;
import static dataBase.DataBaseConnector.getDBConnection;

public class UserServiceTest extends BaseTest {

    @Test
    public void fetchData() {
        String sql = "SELECT name FROM manufacturers WHERE city = ?";
        String name = getDBConnection(USER_ACCOUNT_SERVICE).queryForObject(sql, String.class, "Moscow");
        System.out.println(name);
    }
}