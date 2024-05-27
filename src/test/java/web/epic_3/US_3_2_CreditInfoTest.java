package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;
import web.enums.CreditsEnum;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.2 Просмотр информации о кредитных продуктах банка")
@DisplayName("US-3.2 Просмотр информации о кредитных продуктах банка")
public class US_3_2_CreditInfoTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("US 3-2 Просмотр краткой информации о кредитных продуктах банках")
    @TmsLink("LIB3-181")
    public void checkShortInfoAboutCreditProducts() {
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_CASH);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_EXPRESS);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_CAR);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_MONEY);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_MY_FLAT);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_EASY);
    }
}
