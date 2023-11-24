package api;

import java.util.ArrayList;
import java.util.List;

import api.core.RequestParam;
import lombok.Data;
import service.CustomerService;
import service.DepositService;
import service.InfoService;
import service.UserAccountService;

/**
 * Базовый тестовый класс
 */
@Data
public class BaseTest {
    protected List<RequestParam> params;
    protected CustomerService customerService;
    protected UserAccountService userAccountService;

    protected InfoService infoService;

    protected DepositService depositService;

    public BaseTest() {
        params = new ArrayList<>();
        customerService = new CustomerService();
        userAccountService = new UserAccountService();
        infoService = new InfoService();
        depositService = new DepositService();
    }
}