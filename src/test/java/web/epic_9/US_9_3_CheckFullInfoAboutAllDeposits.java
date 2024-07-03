package web.epic_9;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;
import web.constans.deposit.depositEnums.DepositsNameEnum;

@Tags({@Tag("Web"), @Tag("MVP"), @Tag("Positive")})
@Epic("9 - Депозиты")
@Feature("US-9.3 Просмотр подробной информации о депозитном предложении ")
@DisplayName("US-9.3 Просмотр подробной информации о депозитном предложении ")
public class US_9_3_CheckFullInfoAboutAllDeposits extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty+ Детский")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyChild() {
        depositsProductsFullInfoStep.clickShowMoreButton();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_CHILD);
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty+ Рассчетный")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyCalculated() {
        depositsProductsFullInfoStep.clickShowMoreButtonLibertyCalculated();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_CALCULATED);
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty+ Валютный USD")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyCurrencyUsd() {
        depositsProductsFullInfoStep.clickShowMoreButtonLibertyCurrencyUsd();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_PLUS_CURRENCY_USD);
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty+ Срочный")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyExpress() {
        depositsProductsFullInfoStep.clickShowMoreButtonLibertyExpress();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_PLUS_EXPRESS);
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty Базовый")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyBase() {
        depositsProductsFullInfoStep.clickShowMoreButtonLibertyBase();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_BASIC);
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty Premium")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyPremium() {
        depositsProductsFullInfoStep.clickShowMoreButtonLibertyPremium();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_PREMIUM);
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty Стандарт Срочный")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyStandardExpress() {
        depositsProductsFullInfoStep.clickShowMoreButtonLibertyStandardExpress();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_STANDARD_EXPRESS);
    }

    @Test
    @DisplayName("US 9.3 Просмотр подробной информации о депозитном предложении Liberty Стандарт")
    @TmsLink("LIB3-1899")
    public void checkFullInfoAboutLibertyStandard() {
        depositsProductsFullInfoStep.clickShowMoreButtonLibertyStandard();
        depositsProductsFullInfoStep.assertFullInfoAboutDepositsProduct(DepositsNameEnum.LIBERTY_STANDARD);
    }
}
