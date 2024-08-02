package web.epic_3;

import dataBase.requests.CreditServiceDataBaseRequests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import web.BaseTest;

import java.util.stream.Stream;

@Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
@Epic("3 - Кредиты")
@Feature("US-3.10 Получение отчета для пользователя после оформления заявки на кредит")
@DisplayName("US-3.10 Получение отчета для пользователя после оформления заявки на кредит")
public class US_3_10_CheckValueCreditReportTest extends BaseTest {

    private static Stream<Object> testDataLibertyMoney() {
        return Stream.of(Arguments.of("500050", "47", "1234567890", "1000", "1000", "123456"));
    }

    private static Stream<Object> testDataLibertyMyFlat() {
        return Stream.of(Arguments.of("1000000", "120", "1234567890", "15000", "39000", "123456"));
    }

    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Получение отчета после оформления кредита Liberty Car")
    @TmsLink("LIB3-535")
    @MethodSource("testDataLibertyMoney")
    public void checkPositiveBorderLibertyCarInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome, String autogenerCode) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyCarButton();
        creditApplicationReportSteps.getValuesCreditProduct();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationReportSteps.getValuesCreditApplicationStep(sumCredit, termCredit);
        creditApplicationSteps.sendApplicationForm();
        creditMobileCodeVerificationSteps.enterGenerationCode(autogenerCode);
        creditMobileCodeVerificationSteps.clickNextButton();
        creditApplicationReportSteps.reportIsVisible();
        creditApplicationReportSteps.getDefaultValuesStep();
        creditApplicationReportSteps.assertCreditReports();
        CreditServiceDataBaseRequests.deleteCreditOrder(sumCredit, termCredit);
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Моя квартира")
    @TmsLink("LIB3-535")
    @MethodSource("testDataLibertyMyFlat")
    public void checkPositiveBorderLibertyMyFlatInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome, String autogenerCode) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMyFlatButton();
        creditApplicationReportSteps.getValuesCreditProduct();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationReportSteps.getValuesCreditApplicationStep(sumCredit, termCredit);
        creditApplicationSteps.sendApplicationForm();
        creditMobileCodeVerificationSteps.enterGenerationCode(autogenerCode);
        creditMobileCodeVerificationSteps.clickNextButton();
        creditApplicationReportSteps.reportIsVisible();
        creditApplicationReportSteps.getDefaultValuesStep();
        creditApplicationReportSteps.assertCreditReports();
        CreditServiceDataBaseRequests.deleteCreditOrder(sumCredit, termCredit);
    }
}
