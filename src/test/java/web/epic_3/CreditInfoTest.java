package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.*;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.2 Просмотр информации о кредитных продуктах банка")
@DisplayName("US-3.2 Просмотр информации о кредитных продуктах банка")
public class CreditInfoTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CREDIT_PRODUCTS_URL);
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("US 3-2 Просмотр подробной информации о кредитном продукте Liberty Money")
    @TmsLink("LIB3-181")
    public void checkShortInfoAboutLibertyMoney() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditInfoSteps.assertShortTextNameCreditProductPageText();
    }

}
