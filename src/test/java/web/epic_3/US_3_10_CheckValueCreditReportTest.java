package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import web.BaseTest;

import java.util.stream.Stream;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.10 Получение отчета для пользователя после оформления заявки на кредит")
@DisplayName("US-3.10 Получение отчета для пользователя после оформления заявки на кредит")
public class US_3_10_CheckValueCreditReportTest extends BaseTest {
    private static Stream<Object> testDataLibertyMoney() {
        return Stream.of(Arguments.of("3000", "12", "1234567890", "1000", "1000"));
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Liberty Car")
    @MethodSource("testDataLibertyMoney")
    public void checkPositiveBorderLibertyCarInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        сreditProductsSteps.clickShowMoreLibertyCarButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);

    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Моя квартира")
    @MethodSource("testDataLibertyMoney")
    public void checkPositiveBorderLibertyMyFlatInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        сreditProductsSteps.clickShowMoreLibertyMyFlatButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);

    }
}
