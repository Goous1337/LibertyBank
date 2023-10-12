package api;

import java.util.ArrayList;
import java.util.List;

import api.core.RequestParam;
import lombok.Data;
import service.CustomerService;
import service.VerificationService;

/**
 * Базовый тестовый класс
 */
@Data
public class BaseTest {
    protected List<RequestParam> params;
    protected CustomerService customerService;
    protected VerificationService verificationService;

    public BaseTest() {
        params = new ArrayList<>();
        customerService = new CustomerService();
        verificationService = new VerificationService();
    }
}