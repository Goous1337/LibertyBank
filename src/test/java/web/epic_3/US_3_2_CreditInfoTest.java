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
    @DisplayName("US 3-2 Просмотр подробной информации о кредитном продукте Liberty Наличными")
    @TmsLink("LIB3-181")
    public void checkShortInfoAboutCreditProducts() {
        сreditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_CASH);
        сreditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_EXPRESS);
        сreditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_CAR);
        сreditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_MONEY);
        сreditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_MY_FLAT);
        сreditProductsSteps.assertShortInfoAboutCreditProducts(CreditsEnum.LIBERTY_EASY);
    }
}
