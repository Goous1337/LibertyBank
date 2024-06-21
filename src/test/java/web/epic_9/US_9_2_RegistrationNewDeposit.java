package web.epic_9;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import web.BaseTest;

import java.util.stream.Stream;

@Tag("Web")
@Epic("9 - Депозиты")
@Feature("US-9.2 Оформление нового депозита ")
@DisplayName("US-9.2 Оформление нового депозита ")
public class US_9_2_RegistrationNewDeposit extends BaseTest {
    private static Stream<Object> testValidDataLibertyStandardExpress() {
        return Stream.of(Arguments.of("500000", "6"),
                Arguments.of("500001", "7"),
                Arguments.of("999999", "35"),
                Arguments.of("1000000", "36"));
    }

    private static Stream<Object> testInvalidDataSumLibertyStandardExpress() {
        return Stream.of(Arguments.of("499999", "6"),
                Arguments.of("1000001", "7"));
    }

    private static Stream<Object> testInvalidDataTermLibertyStandardExpress() {
        return Stream.of(Arguments.of("500000", "2"),
                Arguments.of("1000000", "37"));
    }

    private static Stream<Object> testInvalidDataSpecialSymbolsLibertyStandardExpress() {
        return Stream.of(Arguments.of("Dj#", "v$3"),
                Arguments.of(";}.", "Z@!"));
    }

    private static Stream<Object> testInvalidDataEmptyFields() {
        return Stream.of(Arguments.of("", ""),
                Arguments.of("", ""));
    }

    private static Stream<Object> testValidDataLibertyPlusExpress() {
        return Stream.of(Arguments.of("100000", "1"),
                Arguments.of("100001", "2"),
                Arguments.of("9999999", "35"),
                Arguments.of("10000000", "36"));
    }

    private static Stream<Object> testInvalidDataSumLibertyPlusExpress() {
        return Stream.of(Arguments.of("99999", "1"),
                Arguments.of("10000001", "2"));
    }

    private static Stream<Object> testInvalidDataTermLibertyPlusExpress() {
        return Stream.of(Arguments.of("100002", "0"),
                Arguments.of("99994566", "37"));
    }

    private static Stream<Object> testValidDataLibertyChild() {
        return Stream.of(Arguments.of("1000", "1"),
                Arguments.of("1001", "2"),
                Arguments.of("99999", "35"),
                Arguments.of("100000", "36"));
    }

    private static Stream<Object> testInvalidDataSumLibertyChild() {
        return Stream.of(Arguments.of("999", "1"),
                Arguments.of("100001", "7"));
    }

    private static Stream<Object> testInvalidDataTermLibertyChild() {
        return Stream.of(Arguments.of("1234", "0"),
                Arguments.of("99876", "37"));
    }

    private static Stream<Object> testValidDataLibertyBase() {
        return Stream.of(Arguments.of("1000", "3"),
                Arguments.of("1001", "4"),
                Arguments.of("499999", "35"),
                Arguments.of("500000", "36"));
    }

    private static Stream<Object> testInvalidDataSumLibertyBase() {
        return Stream.of(Arguments.of("999", "6"),
                Arguments.of("500001", "18"));
    }

    private static Stream<Object> testInvalidDataTermLibertyBase() {
        return Stream.of(Arguments.of("1234", "1"),
                Arguments.of("453123", "37"));
    }

    private static Stream<Object> testValidDataLibertyPremium() {
        return Stream.of(Arguments.of("1000000", "6"),
                Arguments.of("1000001", "7"),
                Arguments.of("9999999", "35"),
                Arguments.of("10000000", "36"));
    }

    private static Stream<Object> testInvalidDataSumLibertyPremium() {
        return Stream.of(Arguments.of("999999", "6"),
                Arguments.of("10000001", "9"));
    }

    private static Stream<Object> testInvalidDataTermLibertyPremium() {
        return Stream.of(Arguments.of("1345876", "5"),
                Arguments.of("9567888", "37"));
    }

    private static Stream<Object> testValidDataLibertyStandard() {
        return Stream.of(Arguments.of("500000", "1"),
                Arguments.of("500001", "2"),
                Arguments.of("999999", "35"),
                Arguments.of("1000000", "36"));
    }

    private static Stream<Object> testValidDataLibertyCalculated() {
        return Stream.of(Arguments.of("1000", "1"),
                Arguments.of("1001", "2"),
                Arguments.of("9999990", "59"),
                Arguments.of("10000000", "60"));
    }

    private static Stream<Object> testInvalidDataSumLibertyCalculated() {
        return Stream.of(Arguments.of("999", "6"),
                Arguments.of("10000001", "7"));
    }

    private static Stream<Object> testInvalidDataTermLibertyCalculated() {
        return Stream.of(Arguments.of("13455", "0"),
                Arguments.of("8456123", "61"));
    }

    private static Stream<Object> testValidDataLibertyBaseExpress() {
        return Stream.of(Arguments.of("1000", "3"),
                Arguments.of("1001", "4"),
                Arguments.of("499999", "35"),
                Arguments.of("500000", "36"));
    }

    private static Stream<Object> testInvalidDataSumLibertyBaseExpress() {
        return Stream.of(Arguments.of("0", "6"),
                Arguments.of("500001", "7"));
    }

    private static Stream<Object> testValidDataLibertyCurrency() {
        return Stream.of(Arguments.of("3000", "3"),
                Arguments.of("3001", "4"),
                Arguments.of("999999", "35"),
                Arguments.of("1000000", "36"));
    }

    private static Stream<Object> testInvalidDataSumLibertyCurrency() {
        return Stream.of(Arguments.of("2999", "5"),
                Arguments.of("1000001", "6"));
    }

    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @DisplayName("Оформление заявки на депозит")
    public void checkBaseApplicationTest() {

    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty Стандарт Срочный без пролонгации")
    @MethodSource("testValidDataLibertyStandardExpress")
    public void checkPositiveBorderDepositLibertyStandardExpress(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty Стандарт Срочный без пролонгации")
    @MethodSource("testInvalidDataSumLibertyStandardExpress")
    public void checkNegativeBorderDepositLibertyStandardExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayed(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty Стандарт Срочный без пролонгации")
    @MethodSource("testInvalidDataTermLibertyStandardExpress")
    public void checkNegativeBorderTermDepositLibertyStandardExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayed(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty Стандарт Срочный с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyStandardExpress")
    public void checkPositiveBorderDepositLibertyStandardExpressWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Стандарт Срочный без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyStandardExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Стандарт Срочный без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyStandardExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty+ Срочный без пролонгации")
    @MethodSource("testValidDataLibertyPlusExpress")
    public void checkPositiveBorderDepositLibertyPlusExpress(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty+ Срочный без пролонгации")
    @MethodSource("testInvalidDataSumLibertyPlusExpress")
    public void checkNegativeBorderDepositLibertyPlusExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedLibertyPlusDeposit(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty+ Срочный без пролонгации")
    @MethodSource("testInvalidDataTermLibertyPlusExpress")
    public void checkNegativeBorderTermDepositLibertyPlusExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayedPlusDeposit(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty+ Срочный с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyPlusExpress")
    public void checkPositiveBorderDepositLibertyPlusExpressWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty+ Срочный без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyPlusExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty+ Срочный без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyPlusExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty+ Детский без пролонгации")
    @MethodSource("testValidDataLibertyChild")
    public void checkPositiveBorderDepositLibertyChild(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyChildRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty+ Детский без пролонгации")
    @MethodSource("testInvalidDataSumLibertyChild")
    public void checkNegativeBorderDepositLibertyChild(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyChildRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedLibertyChild(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty+ Детский без пролонгации")
    @MethodSource("testInvalidDataTermLibertyChild")
    public void checkNegativeBorderTermDepositLibertyChild(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyChildRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayed(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty+ Детский c выключенными чекбоксами")
    @MethodSource("testValidDataLibertyChild")
    public void checkPositiveBorderDepositLibertyChildWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyChildRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty+ Детский без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyChild(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyChildRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма кредита' и 'Срок кредита' при оформлении заявки на депозит Liberty+ Детский без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyChild(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyChildRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty Базовый без пролонгации")
    @MethodSource("testValidDataLibertyBase")
    public void checkPositiveBorderDepositLibertyBase(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty Базовый без пролонгации")
    @MethodSource("testInvalidDataSumLibertyBase")
    public void checkNegativeBorderDepositLibertyBase(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedLibertyBase(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty Базовый без пролонгации")
    @MethodSource("testInvalidDataTermLibertyBase")
    public void checkNegativeBorderTermDepositLibertyBase(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayedLibertyBase(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty Базовый с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyBase")
    public void checkPositiveBorderDepositLibertyBaseWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Базовый без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyBase(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Базовый без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyBase(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty Premium без пролонгации")
    @MethodSource("testValidDataLibertyPremium")
    public void checkPositiveBorderDepositLibertyPremium(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyPremiumRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty Premium без пролонгации")
    @MethodSource("testInvalidDataSumLibertyPremium")
    public void checkNegativeBorderDepositLibertyPremium(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyPremiumRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedLibertyPremium(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty Premium без пролонгации")
    @MethodSource("testInvalidDataTermLibertyPremium")
    public void checkNegativeBorderTermDepositLibertyPremium(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyPremiumRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayed(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty Premium с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyPremium")
    public void checkPositiveBorderDepositLibertyPremiumWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyPremiumRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Premium без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyPremium(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyPremiumRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Premium без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyPremium(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyPremiumRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит 'Liberty Стандарт' без пролонгации")
    @MethodSource("testValidDataLibertyStandard")
    public void checkPositiveBorderDepositLibertyStandard(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty Стандарт без пролонгации")
    @MethodSource("testInvalidDataSumLibertyStandardExpress")
    public void checkNegativeBorderDepositLibertyStandard(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayed(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty Стандарт без пролонгации")
    @MethodSource("testInvalidDataTermLibertyChild")
    public void checkNegativeBorderTermDepositLibertyStandard(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayedPlusDeposit(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty Стандарт с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyStandard")
    public void checkPositiveBorderDepositLibertyStandardWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Стандарт без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyStandard(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Стандарт без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyStandard(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyStandardRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty Расчетный без пролонгации")
    @MethodSource("testValidDataLibertyCalculated")
    public void checkPositiveBorderDepositLibertyCalculated(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCalculatedRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty Расчетный без пролонгации")
    @MethodSource("testInvalidDataSumLibertyCalculated")
    public void checkNegativeBorderDepositLibertyCalculated(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCalculatedRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedLibertyCalculated(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty Расчетный без пролонгации")
    @MethodSource("testInvalidDataTermLibertyCalculated")
    public void checkNegativeBorderTermDepositLibertyCalculated(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCalculatedRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayedCalculated(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty Расчетный с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyCalculated")
    public void checkPositiveBorderDepositLibertyCalculatedWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCalculatedRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Расчетный без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyCalculated(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCalculatedRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Расчетный без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyCalculated(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCalculatedRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty Базовый Срочный без пролонгации")
    @MethodSource("testValidDataLibertyBaseExpress")
    public void checkPositiveBorderDepositLibertyBaseExpress(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty Базовый Срочный без пролонгации")
    @MethodSource("testInvalidDataSumLibertyBaseExpress")
    public void checkNegativeBorderDepositLibertyBaseExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedBaseExpress(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty Базовый Срочный без пролонгации")
    @MethodSource("testInvalidDataTermLibertyBase")
    public void checkNegativeBorderTermDepositLibertyBaseExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayedLibertyBase(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty Базовый Срочный с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyStandardExpress")
    public void checkPositiveBorderDepositLibertyBaseExpressWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Базовый Срочный без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyBaseExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty Базовый Срочный без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyBaseExpress(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyBaseExpressRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty+ Валютный в долларах без пролонгации")
    @MethodSource("testValidDataLibertyCurrency")
    public void checkPositiveBorderDepositLibertyCurrencyUSD(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty+ Валютный в долларах без пролонгации")
    @MethodSource("testInvalidDataSumLibertyCurrency")
    public void checkNegativeBorderDepositLibertyCurrencyUSD(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedLibertyCurrency(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty+ Валютный в долларах без пролонгации")
    @MethodSource("testInvalidDataTermLibertyStandardExpress")
    public void checkNegativeBorderTermDepositLibertyCurrencyUSD(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayedLibertyBase(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty+ Валютный в долларах с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyCurrency")
    public void checkPositiveBorderDepositLibertyCurrencyUSdWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty+ Валютный в долларах без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyCurrencyUSD(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty+ Валютный в долларах без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyCurrencyUSD(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }
    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка допустимых граничных значений полей при оформлении заявки на депозит Liberty+ Валютный в евро без пролонгации")
    @MethodSource("testValidDataLibertyCurrency")
    public void checkPositiveBorderDepositLibertyCurrencyEUR(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.clickRadioButtonEur();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Сумма депозита' при оформлении заявки на депозит Liberty+ Валютный евро без пролонгации")
    @MethodSource("testInvalidDataSumLibertyCurrency")
    public void checkNegativeBorderDepositLibertyCurrencyEUR(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.clickRadioButtonEur();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.assertErrorMessageSumIsDisplayedLibertyCurrency(Double.valueOf(sumDeposit));
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка не валидных значений в полях 'Срок депозита' при оформлении заявки на депозит Liberty+ Валютный в евро без пролонгации")
    @MethodSource("testInvalidDataTermLibertyStandardExpress")
    public void checkNegativeBorderTermDepositLibertyCurrencyEUR(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.clickRadioButtonEur();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageTermIsDisplayedLibertyBase(Double.valueOf(termDeposit));
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка возможности отправки заявки на депозит Liberty+ Валютный в евро с выключенными чекбоксами")
    @MethodSource("testValidDataLibertyCurrency")
    public void checkPositiveBorderDepositLibertyCurrencyEUrWithoutCheckboxes(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.clickRadioButtonEur();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка ввода спецсимволов в полях 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty+ Валютный в евро без пролонгации")
    @MethodSource("testInvalidDataSpecialSymbolsLibertyStandardExpress")
    public void checkSpecialSymbolsDepositLibertyCurrencyEUR(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.clickRadioButtonEur();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.enterValidateAmountDepositInput(sumDeposit);
        depositsApplicationSteps.fillTermOfDepositField(termDeposit);
        depositsApplicationSteps.assertErrorMessageSymbolSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }

    @ParameterizedTest
    @Tags({@Tag("Web"), @Tag("Negative")})
    @DisplayName("Проверка отправки заявки с пустыми полями 'Сумма депозита' и 'Срок депозита' при оформлении заявки на депозит Liberty+ Валютный в евро без пролонгации")
    @MethodSource("testInvalidDataEmptyFields")
    public void checkEmptyFieldDepositLibertyCurrencyEUR(String sumDeposit, String termDeposit) throws InterruptedException {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositsProductsBankButton();
        depositsProductsSteps.clickDepositLibertyCurrencyRegistrationButton();
        depositsApplicationSteps.clickRadioButtonEur();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.clickFieldSumOfDeposit();
        depositsApplicationSteps.clickFieldTermOfDeposit();
        depositsApplicationSteps.assertErrorMessageEmptyFieldSumTermIsDisplayed(sumDeposit, termDeposit);
        depositsApplicationSteps.fillCheckboxSendDepositLibertyStandardExpressWithoutLong();
        depositsApplicationSteps.assertSubmitButtonInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)"
        );
        depositsApplicationSteps.sendDepositLibertyStandardExpress();
    }
}
