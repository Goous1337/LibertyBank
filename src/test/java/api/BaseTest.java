package api;

import java.util.ArrayList;
import java.util.List;

import api.core.RequestParam;
import lombok.Data;
import service.RegistrationService;

/**
 * Базовый тестовый класс
 */
@Data
public class BaseTest {
    protected List<RequestParam> params;
    protected RegistrationService registrationService;

    public BaseTest() {
        params = new ArrayList<>();
        registrationService = new RegistrationService();
    }
}