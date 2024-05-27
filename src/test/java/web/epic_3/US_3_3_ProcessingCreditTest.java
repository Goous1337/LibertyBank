package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import web.BaseTest;

import java.util.stream.Stream;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.3 Оформление заявки на кредит")
@DisplayName("US-3.3 Оформление заявки на кредит")
public class US_3_3_ProcessingCreditTest extends BaseTest {

    private static Stream<Object> testDataLibertyMoney() {
        return Stream.of(Arguments.of("3000", "12", "1234567890", "1000", "1000"),
                Arguments.of("3001", "13", "1234567890", "1000", "1000"),
                Arguments.of("99999", "59", "1234567890", "1000", "1000"),
                Arguments.of("100000", "60", "123456789012", "10000", "10000"));
    }

    private static Stream<Object> testDataLibertyExpress() {
        return Stream.of(Arguments.of("50000", "12", "1234567890", "1000", "1000"),
                Arguments.of("50001", "12", "1234567890", "1000", "1000"),
                Arguments.of("2999999", "59", "1234567890", "1000", "1000"),
                Arguments.of("3000000", "60", "123456789123", "5194", "51352"));
    }

    private static Stream<Object> testDataLibertyCash() {
        return Stream.of(Arguments.of("50000", "12", "1234567890", "1000", "1000"),
                Arguments.of("50001", "13", "1234567890", "1000", "1000"),
                Arguments.of("4999999", "59", "1234567890", "1000", "1000"),
                Arguments.of("5000000", "60", "423456123512", "5194", "51352"));
    }

    private static Stream<Object> testDataLibertyEasy() {
        return Stream.of(Arguments.of("50000", "12", "1234567890", "1500", "3000"),
                Arguments.of("50001", "13", "1234567890", "1000", "1000"),
                Arguments.of("2999999", "59", "1234567890", "1000", "1000"),
                Arguments.of("3000000", "60", "423456123512", "1000", "2500"));
    }

    private static Stream<Object> testDataLibertyCar() {
        return Stream.of(Arguments.of("500000", "36", "1234567890", "15000", "39000"),
                Arguments.of("500001", "37", "1234567890", "1000", "1000"),
                Arguments.of("49999999", "83", "1234567890", "1000", "1000"),
                Arguments.of("50000000", "84", "423456123512", "31500", "62300"));
    }

    private static Stream<Object> testDataLibertyMyFlat() {
        return Stream.of(Arguments.of("1000000", "120", "1234567890", "15000", "39000"),
                Arguments.of("1000001", "121", "1234567890", "1000", "1000"),
                Arguments.of("499999999", "239", "912348765012", "1000", "1000"),
                Arguments.of("500000000", "240", "912348765012", "301500", "623300"));
    }

    private static Stream<Object> testDataInvalidAmountAndPeriodLibertyMyFlat() {
        return Stream.of(Arguments.of("1000000", "119", "1234567890", "20000", "39000"),
                Arguments.of("999999", "120", "1234567890", "12000", "36000"),
                Arguments.of("500000000", "241", "1234567890", "20000", "39000"),
                Arguments.of("500000001", "240", "1234567890", "12000", "36000"),
                Arguments.of(" ", "241", "1234567890", "20000", "39000"),
                Arguments.of("500000000", " ", "1234567890", "20000", "39000"),
                Arguments.of("500000000", "2345", "1234567890", "20000", "39000")
        );
    }

    private static Stream<Object> testDataInvalidLibertyMyFlat() {
        return Stream.of(
                Arguments.of("1200000", "160", " ", "20000", "39000"),
                Arguments.of("3200000", "201", "1234567890", " ", "39000"),
                Arguments.of("3200000", "215", "1234567890", "12600", " "),
                Arguments.of("3200000", "178", "123456789", "10000", "79000"),
                Arguments.of("3200000", "178", "12345678900", "10000", "79000"),
                Arguments.of("3200000", "178", "912345678", "10000", "79000"),
                Arguments.of("3200000", "178", "0012345678", "10000", "79000"),
                Arguments.of("3201000", "150", "1234567890", "5000", " "),
                Arguments.of("3200000", "178", "^!%*^@$#", "10000", "39000"),
                Arguments.of("3200000", "178", "1234567890", "(*&)(*", "39000"),
                Arguments.of("3200000", "178", "1234567890", "4000", "!@#$%$")
        );
    }


    @BeforeAll
    public void setUpTest() {
        authorization();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @DisplayName("Оформление заявки на кредит")
    public void checkBaseApplicationTest() {

    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Liberty Money")
    @MethodSource("testDataLibertyMoney")
    public void checkPositiveBorderLibertyMoneyInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMoneyButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Liberty Срочный")
    @MethodSource("testDataLibertyExpress")
    public void checkPositiveBorderLibertyExpressInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyExpressButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Liberty Наличный")
    @MethodSource("testDataLibertyCash")
    public void checkPositiveBorderLibertyCashInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyCashButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Liberty Easy")
    @MethodSource("testDataLibertyEasy")
    public void checkPositiveBorderLibertyEasyInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyEasyButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Liberty Car")
    @MethodSource("testDataLibertyCar")
    public void checkPositiveBorderLibertyCarInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyCarButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на кредит Моя квартира")
    @MethodSource("testDataLibertyMyFlat")
    public void checkPositiveBorderLibertyMyFlatInputTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMyFlatButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
    }

    @ParameterizedTest
    @IgnoreForBinding
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма кредита','Срок кредита' при оформлении заявки на кредит")
    @MethodSource("testDataInvalidAmountAndPeriodLibertyMyFlat")
    public void checkIncorrectDataInputAmountAndPeriodCreditTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMyFlatButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);

        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
    }

    @ParameterizedTest
    @IgnoreForBinding
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Идентификационный номер работодателя','Общая долговая нагрузка','Среднемесячный доход' при оформлении заявки на кредит")
    @MethodSource("testDataInvalidLibertyMyFlat")
    public void checkIncorrectDataInputCreditTest(String sumCredit, String termCredit, String employerIdentificationNumber, String totalDebtLoad, String averageMonthlyIncome) {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMyFlatButton();
        creditProductDetailedInformationSteps.clickShowMoreButton();
        creditApplicationSteps.enterValidateAmountCreditInput(sumCredit);
        creditApplicationSteps.enterPeriodMonthsCreditInput(termCredit);
        creditApplicationSteps.enterIdentificationNumberCreditInput(employerIdentificationNumber);
        creditApplicationSteps.assertEmployerIdentificationNumberErrorMessageIsDisplayed(employerIdentificationNumber);
        creditApplicationSteps.enterMonthlyExpenditureCreditInput(totalDebtLoad);
        creditApplicationSteps.assertTotalDebtLoadErrorMessageIsDisplayed(totalDebtLoad);
        creditApplicationSteps.enterMonthlyIncomeCreditInput(averageMonthlyIncome);
        creditApplicationSteps.assertTotalDebtLoadErrorMessageIsDisplayed(averageMonthlyIncome);
        //Дописать метод на проверку значений averageMonthlyIncome
        creditApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
    }
}
