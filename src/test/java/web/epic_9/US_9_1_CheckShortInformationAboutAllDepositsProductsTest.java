package web.epic_9;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;
import web.constans.deposit.depositEnums.DepositsNameEnum;

@Tags({@Tag("Web"), @Tag("MVP"), @Tag("Positive")})
@Epic("9 - Депозиты")
@Feature("US-9.1 Просмотр краткой информации по всем депозитным продуктам банка ")
@DisplayName("US-9.1 Просмотр краткой информации по всем депозитным продуктам банка ")
public class US_9_1_CheckShortInformationAboutAllDepositsProductsTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
    }

    @Test
    @DisplayName("US 9.1 Просмотр краткой информации по всем депозитным продуктам банка ")
    @TmsLink("LIB3-1895")
    public void checkShortInfoAboutDepositsProducts() {
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_CHILD);
        //depositsProductsSteps.assertShortInfoAboutDeposits(DepositsEnum.LIBERTY_CURRENCY_EUR);
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_CALCULATED);
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_PLUS_EXPRESS);
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_BASIC);
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_PREMIUM);
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_STANDARD_EXPRESS);
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_PLUS_CURRENCY_USD);
        depositsProductsSteps.assertShortInfoAboutDeposits(DepositsNameEnum.LIBERTY_STANDARD);
    }
}
