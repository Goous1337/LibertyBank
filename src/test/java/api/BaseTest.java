package api;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import api.core.RequestParam;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import service.*;

/**
 * Базовый тестовый класс
 */
@Data
@Log4j2
public class BaseTest {
    protected List<RequestParam> params;
    protected CustomerService customerService;
    protected CustomerService2_0 customerService2_0;
    protected UserAccountService userAccountService;

    protected InfoService infoService;

    protected DepositService depositService;
    protected CreditService creditService;
    protected AbsInfoService absInfoService;
    protected AbsClientService absClientService;
    protected CustomerService_2_0 customerService_2_0;

    public BaseTest() {
        params = new ArrayList<>();
        customerService = new CustomerService();
        userAccountService = new UserAccountService();
        infoService = new InfoService();
        depositService = new DepositService();
        creditService = new CreditService();
        absInfoService = new AbsInfoService();
        absClientService = new AbsClientService();
        customerService2_0 = new CustomerService2_0();
        customerService_2_0 = new CustomerService_2_0();
    }

    @BeforeEach
    public void beforeTestLogging(TestInfo info) {
        String testLink = info.getTestMethod()
                .get()
                .getAnnotation(TmsLink.class).value();
        String testDescription = info.getTestMethod()
                .get()
                .getAnnotation(Description.class).value();
        log.info("\n_____________________________________________________\n");
        log.info(String.format("Test-case link: %s\nTest description: %s\n", testLink, testDescription));
    }

}