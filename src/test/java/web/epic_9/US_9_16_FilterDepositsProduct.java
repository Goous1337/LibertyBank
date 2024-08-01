package web.epic_9;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import web.BaseTest;

import java.util.stream.Stream;

@Tag("Web")
@Epic("9 - Депозиты")
@Feature("US-9.16 Фильтр депозитных продуктов")
@DisplayName("US-9.16 Фильтр депозитных продуктов")
public class US_9_16_FilterDepositsProduct extends BaseTest {

    private static Stream<Object> testValidDataForFilter() {
        return Stream.of(Arguments.of("1000", "1"),
                Arguments.of("1001", "2"));
    }

    private static Stream<Object> testInvalidDataForFilter() {
        return Stream.of(Arguments.of("3000", "60"),
                Arguments.of("10000000", "1"));
    }

    @BeforeEach
    public void setUpTest() {
        authorization();

    }

    @Test
    @Tag("Positive")
    @DisplayName("US-9.16 Проверка отображения параметров фильтрации депозитных продуктов")
    @TmsLink("LIB3-2600")
    public void checkInfoAboutFilterDeposit() {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositProductsButton();
        depositsFilterStep.clickFilterButton();
        depositsFilterStep.assertAnyButtonIsDisplayed();
        depositsFilterStep.assertForAccumulationIsDisplayed();
        depositsFilterStep.assertForCalculationIsDisplayed();
        depositsFilterStep.assertForRubIsDisplayed();
        depositsFilterStep.assertForUsdIsDisplayed();
        depositsFilterStep.assertForEurIsDisplayed();
        depositsFilterStep.assertFieldAmountOfDepositDisplayed();
        depositsFilterStep.assertFieldTermOfDepositDisplayed();
    }

    @Test
    @Tag("Positive")
    @DisplayName("UC 9.16 Проверка отображения параметров фильтрации при первоначальном открытии")
    @TmsLink("LIB3-2601")
    public void checkInfoAboutStartFilterDeposit() {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositProductsButton();
        depositsFilterStep.clickFilterButton();
        depositsFilterStep.assertAnyButtonIsDisplayed();
        depositsFilterStep.assertForRubIsDisplayed();
        depositsFilterStep.assertFieldMeaningSumRubOfDepositDisplayed();
        depositsFilterStep.assertFieldMeaningTermOfDepositDisplayed();
        depositsFilterStep.assertForUsdIsDisplayed();
        depositsFilterStep.clickCurrencyUSDButton();
        depositsFilterStep.assertFieldMeaningSumCurrencyOfDepositDisplayed();
        depositsFilterStep.assertFieldMeaningTermOfDepositDisplayed();
        depositsFilterStep.assertForEurIsDisplayed();
        depositsFilterStep.clickCurrencyEURButton();
        depositsFilterStep.assertFieldMeaningSumCurrencyOfDepositDisplayed();
        depositsFilterStep.assertFieldMeaningTermOfDepositDisplayed();
    }

    @ParameterizedTest
    @Tag("Positive")
    @DisplayName("UC 9.16 Проверка позитивного сценария работы фильтра депозитных продуктов")
    @TmsLink("LIB3-2602")
    @MethodSource("testValidDataForFilter")
    public void checkPositiveFilterDeposit(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositProductsButton();
        depositsFilterStep.clickFilterButton();
        depositsFilterStep.clickForAccumulationButton();
        depositsFilterStep.enterValidateAmountDepositInput(sumDeposit);
        depositsFilterStep.enterValidateTermOfDepositInput(termDeposit);
        depositsFilterStep.asserNameOfLibertyChildDeposit();
    }

    @ParameterizedTest
    @Tag("Negative")
    @DisplayName("UC 9.16 Проверка поведения системы при отсутствии депозитных продуктов по заданным параметрам фильтрации")
    @TmsLink("LIB3-2604")
    @MethodSource("testInvalidDataForFilter")
    public void checkInfoAboutEmptyDepositProductFilterParam(String sumDeposit, String termDeposit) {
        depositsProductsSteps.clickDepositButton();
        depositsProductsSteps.clickDepositProductsButton();
        depositsFilterStep.clickFilterButton();
        depositsFilterStep.clickCurrencyUSDButton();
        depositsFilterStep.enterValidateAmountDepositInput(sumDeposit);
        depositsFilterStep.enterValidateTermOfDepositInput(termDeposit);
        depositsFilterStep.asserEmptyDeposit();
        depositsFilterStep.clickCurrencyEURButton();
        depositsFilterStep.enterValidateAmountDepositInput(sumDeposit);
        depositsFilterStep.enterValidateTermOfDepositInput(termDeposit);
        depositsFilterStep.asserEmptyDeposit();
    }
}
