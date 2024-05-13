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
    public LoginSteps enterPhone(String phoneNumber) {
        loginPage.enterPhone(phoneNumber);
        return this;
    }

    @Step("Нажать на поле логин")
    public void clickInputPhone() {
        loginPage.clickInputPhone();
    }

    @Step("Ввести валидный пароль")
    public LoginSteps enterPassword(String password) {
        loginPage.enterPassword(password);
        return this;
    }

    @Step("Нажать на поле пароль")
    public LoginSteps clickInputPassword() {
        loginPage.clickInputPassword();
        return this;
    }

    @Step("Выйти из поля номер телефона")
    public LoginSteps outFormPhone() {
        loginPage.outFormPhone();
        return this;
    }

    @Step("Выйти из поля пароль")
    public LoginSteps outFormPassword() {
        loginPage.outFormPassword();
        return this;
    }

    @Step("Нажать кнопку 'Вперед' и перейти на страницу main")
    public void tapSubmitButtonToMain() {
        loginPage.submitToMainPage();
    }

    @Step("Кликнуть по кнопке 'Вперед'")
    public LoginSteps clickSubmitButton() {
        loginPage.clickSubmitButton();
        return this;
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

    @Step("Проверки поля \"Номер телефона\"")
    public LoginSteps assertPhoneInput(int amountSymbols, String colorPhoneInput, String colorPlaceholderPhone,
                                       String textErrorPhone, boolean isNotVisible) {
        softAssertions.assertThat(loginPage.getTextFromPhoneInput().length())
                .as("Проверка кол-ва символов поля \"Номер телефона\"")
                .isEqualTo(amountSymbols);
        softAssertions.assertThat(loginPage.getPhoneInputBorderColor())
                .as("Проверка цвета поля \"Номер телефона\"")
                .isEqualTo(colorPhoneInput);
        softAssertions.assertThat(loginPage.getColorPlaceholderPhone())
                .as("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
                .isEqualTo(colorPlaceholderPhone);
        if (!textErrorPhone.isEmpty()) {
            softAssertions.assertThat(loginPage.getErrorPhoneHintText())
                    .as("Проверка сообщения об ошибке поля \"Номер телефона\" " +
                                    "при недостаточном кол-ве символов")
                    .isEqualTo(textErrorPhone);
        } else {
            softAssertions.assertThat(loginPage.isErrorPhoneHintIsDisplayed())
                    .as("Проверка отсутствия подсказки под полем \"Номер телефона\"")
                    .isEqualTo(isNotVisible);
        }
        return this;
    }

    @Step("Проверки поля \"Пароль\"")
    public LoginSteps assertPasswordInput(int amountSymbols, String colorPasswordInput, String colorPlaceholderPassword,
                                          String textErrorPassword, boolean isNotVisible) {
        softAssertions.assertThat(loginPage.getTextFromPasswordInput().length())
                .as("Проверка кол-ва символов поля \"Пароль\"")
                .isEqualTo(amountSymbols);
        softAssertions.assertThat(loginPage.getPasswordInputBorderColor())
                .as("Проверка цвета поля \"Пароль\"")
                .isEqualTo(colorPasswordInput);
        softAssertions.assertThat(loginPage.getColorPlaceholderPassword())
                .as("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
                .isEqualTo(colorPlaceholderPassword);
        if (!textErrorPassword.isEmpty()) {
            softAssertions.assertThat(loginPage.getErrorPasswordHintText())
                    .as("Проверка сообщения об ошибке поля \"Пароль\" при недостаточном кол-ве символов")
                    .isEqualTo(textErrorPassword);
        } else {
            softAssertions.assertThat(loginPage.isErrorPasswordHintIsDisplayed())
                    .as("Проверка отсутствия подсказки под полем \"Пароль\"")
                    .isEqualTo(isNotVisible);
        }
        return this;
    }

    public void assertAllChecks() {
        softAssertions.assertAll();
    }
}
