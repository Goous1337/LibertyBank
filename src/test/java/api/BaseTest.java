package api;

import java.util.ArrayList;
import java.util.List;

import api.core.RequestParam;
import lombok.Data;
import service.RegistrationService;
import service.VerificationService;

/**
 * Базовый тестовый класс
 */
@Data
public class BaseTest {
    protected List<RequestParam> params;
    protected RegistrationService registrationService;
    protected VerificationService verificationService;

    public BaseTest() {
        params = new ArrayList<>();
        registrationService = new RegistrationService();
        verificationService = new VerificationService();
    }
}