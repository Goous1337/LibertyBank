package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;
import web.constans.credit.creditEnums.CreditNameEnum;

@Tags({@Tag("Web"), @Tag("MVP")})
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
    @DisplayName("US 3-2 Просмотр краткой информации о кредитных продуктах банках")
    @TmsLink("LIB3-181")
    public void checkShortInfoAboutCreditProducts() {
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditNameEnum.LIBERTY_CASH);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditNameEnum.LIBERTY_EXPRESS);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditNameEnum.LIBERTY_CAR);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditNameEnum.LIBERTY_MONEY);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditNameEnum.LIBERTY_MY_FLAT);
        creditProductsSteps.assertShortInfoAboutCreditProducts(CreditNameEnum.LIBERTY_EASY);
    }
}
