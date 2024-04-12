package web.steps;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import web.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.INVALID_COLOR;
import static web.constans.AccountServiceConstants.INVALID_TEXT_IN_ELEMENT;

public class LoginSteps {
    protected LoginPage loginPage;

    SoftAssertions softAssertions = new SoftAssertions();

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    public void clearAssertions() {
        softAssertions = new SoftAssertions();
    }

    @Step("Ввести валидный номер телефона")
    public void enterPhone(String phoneNumber) {
        loginPage.enterPhone(phoneNumber);
    }

    @Step("Ввести валидный пароль")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step("Нажать кнопку 'Вперед' и перейти на страницу main")
    public void tapSubmitButtonToMain() {
        loginPage.submitToMainPage();
    }

    @Step("Кликнуть по кнопке 'Вперед'")
    public void clickSubmitButton() {
        loginPage.clickSubmitButton();
    }

    @Step("Проверка кнопки и поля ввода на правильных значениях")
    public void assertSubmitButtonAndInputSuccessful(
            String bgButtonColor, String textButtonColor, String inputColor) {
        assertAll(
                () -> assertTrue(loginPage.checkButtonCondition(bgButtonColor, textButtonColor, true),
                        INVALID_COLOR),
                () -> assertTrue(loginPage.isValidInput(inputColor), INVALID_COLOR)
        );
    }

    @Step("Проверка кнопки и поля ввода при неправильных значениях")
    public void assertSubmitButtonAndInputInvalid(
            String bgButtonColor, String textButtonColor, String inputColor) {
        assertAll(
                () -> assertTrue(loginPage.checkButtonCondition(bgButtonColor, textButtonColor, false),
                        INVALID_COLOR),
                () -> assertTrue(loginPage.isValidInput(inputColor), INVALID_COLOR),
                () -> assertTrue(loginPage.checkErrorHint("Неверный пароль или номер телефона"),
                        INVALID_TEXT_IN_ELEMENT)
        );
    }

//    @Step("\"Номер телефона\" 10 символов")
//    public void assertEmptyPhoneInput() {
//        softAssertions.assertThat(loginPage.getTextFromPhoneInput().length())
//                .as("\"Номер телефона\" пустой")
//                .isEqualTo(0);
//    }

    @Step
    public void assertAmountSymbolsPhoneInput(int amountSymbols) {
        softAssertions.assertThat(loginPage.getTextFromPhoneInput().length())
                .as("Проверка кол-ва символов поля \"Номер телефона\"")
                .isEqualTo(amountSymbols);
    }

    @Step("Проверка цвета поля \"Номер телефона\"")
    public void assertColorPhoneInput(String inputPhoneColor) {
        softAssertions.assertThat(loginPage.isValidPhoneInput(inputPhoneColor))
                .as("Проверка цвета поля \"Номер телефона\"")
                .isEqualTo(true);
    }

    @Step("Проверка сообщения об ошибке")
    public void assertErrorPhoneInput(String textErrorPhone) {
        softAssertions.assertThat(loginPage.checkErrorPhoneHint(textErrorPhone))
                .as("Проверка сообщения об ошибке поля \"Номер телфона\"")
                .isEqualTo(true);
    }

    public void assertAllChecks() {
        softAssertions.assertAll();
    }
}
