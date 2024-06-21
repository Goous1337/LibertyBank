package web.steps.depositsSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.depositPages.DepositApplicationPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.INVALID_COLOR;

public class DepositsApplicationSteps {

    protected DepositApplicationPage depositApplicationPage;

    public DepositsApplicationSteps() {
        depositApplicationPage = new DepositApplicationPage();
    }

    @Step("Клик по полю 'Сумма депозита'")
    public void clickFieldSumOfDeposit() {
        depositApplicationPage.clickSumOfDepositField();
    }

    @Step("Клик по полю 'Срок депозита'")
    public void clickFieldTermOfDeposit() {
        depositApplicationPage.clickTermOfDepositField();
    }

    @Step("Клик по радиокнопке 'EUR'")
    public void clickRadioButtonEur() {
        depositApplicationPage.clickRadioButtonEuro();
    }

    @Step("Ввод значения в поле ввода 'Сумма депозита'")
    public void enterValidateAmountDepositInput(String amountDeposit) {
        depositApplicationPage.fillSumOfDepositField(amountDeposit);
    }

    @Step("Ввод значения в поле ввода 'Срок депозита'")
    public void fillTermOfDepositField(String periodMonths) {
        depositApplicationPage.fillTermOfDepositField(periodMonths);
    }

    @Step("Заполнение чекбокса 'Я ознакомлен (а)' на оформление депозита 'Liberty Стандарт Срочный'")
    public void fillCheckboxSendDepositLibertyStandardExpressWithoutLong() {
        depositApplicationPage.clickCheckBoxKnowConditions();
    }

    @Step("Проверка стиля кнопки 'Отправить заявку' при правильных значениях")
    public void assertSubmitButtonSuccessful(String bgButtonColor, String textButtonColor) {
        assertTrue(depositApplicationPage.checkButtonCondition(bgButtonColor, textButtonColor, true), INVALID_COLOR);
    }

    @Step("Проверка стиля кнопки 'Отправить заявку' при неправильных значениях")
    public void assertSubmitButtonInvalid(String bgButtonColor, String textButtonColor) {
        assertTrue(depositApplicationPage.checkButtonCondition(bgButtonColor, textButtonColor, false), INVALID_COLOR);
    }

    @Step("Нажатие кнопки 'Отправить заявку' на оформление депозита 'Liberty Стандарт Срочный'")
    public void sendDepositLibertyStandardExpress() {
        depositApplicationPage.clickSendForm();
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty Стандарт Срочный'")
    public void assertErrorMessageSumIsDisplayed(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowed(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowed(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Срок депозита' депозита 'Liberty Стандарт Срочный'")
    public void assertErrorMessageTermIsDisplayed(Double depositTerm) {
        if (depositApplicationPage.isDepositTermLessThanMinAllowed(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_TERM.getMessage());
        } else if (depositApplicationPage.isDepositTermMoreThanMaxAllowed(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_TERM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в полях 'Cумма депозита' и 'Срок депозита' при введении в поля спецсимволов  депозитов")
    public void assertErrorMessageSymbolSumTermIsDisplayed(String depositSum, String depositTerm) {
        if (depositApplicationPage.textSymbolAmountTermDepositIsDisplayed(depositSum, depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textSymbolAmountTermDepositIsDisplayed(depositSum, depositTerm), true, DepositApplicationPage.ErrorMessage.INPUT_DIGITS.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' и 'Срок депозита' при невведении в поля данных")
    public void assertErrorMessageEmptyFieldSumTermIsDisplayed(String depositSum, String depositTerm) {
        depositApplicationPage.clickSumOfDepositField();
        depositApplicationPage.clickTermOfDepositField();
        if (depositApplicationPage.textFillNecessaryAmountTermDepositIsDisplayed(depositSum, depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textFillNecessaryAmountTermDepositIsDisplayed(depositSum, depositTerm), true, DepositApplicationPage.ErrorMessage.FILL_NECESSARY.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty+ Срочный'")
    public void assertErrorMessageSumIsDisplayedLibertyPlusDeposit(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowedLibertyPlusExpress(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowedPlusExpress(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Срок депозита' депозита 'Liberty+ Срочный'")
    public void assertErrorMessageTermIsDisplayedPlusDeposit(Double depositTerm) {
        if (depositApplicationPage.isDepositTermLessThanMinAllowedPlusExpress(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_TERM.getMessage());
        } else if (depositApplicationPage.isDepositTermMoreThanMaxAllowedPlusExpress(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_TERM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty+ Детский'")
    public void assertErrorMessageSumIsDisplayedLibertyChild(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowedLibertyChild(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowedLibertyChild(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty Базовый'")
    public void assertErrorMessageSumIsDisplayedLibertyBase(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowedLibertyBase(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowedLibertyBase(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Срок депозита' депозита 'Liberty Базовый'")
    public void assertErrorMessageTermIsDisplayedLibertyBase(Double depositTerm) {
        if (depositApplicationPage.isDepositTermLessThanMinAllowedLibertyBase(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_TERM.getMessage());
        } else if (depositApplicationPage.isDepositTermMoreThanMaxAllowedLibertyBase(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_TERM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty Premium'")
    public void assertErrorMessageSumIsDisplayedLibertyPremium(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowedLibertyPremium(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowedLibertyPremium(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty Расчетный'")
    public void assertErrorMessageSumIsDisplayedLibertyCalculated(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowedLibertyCalculated(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowedLibertyCalculated(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }

    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Срок депозита' депозита 'Liberty Расчетный'")
    public void assertErrorMessageTermIsDisplayedCalculated(Double depositTerm) {
        if (depositApplicationPage.isDepositTermLessThanMinAllowedCalculated(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_TERM.getMessage());
        } else if (depositApplicationPage.isDepositTermMoreThanMaxAllowedCalculated(depositTerm)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreTermDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_TERM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty Базовый Срочный'")
    public void assertErrorMessageSumIsDisplayedBaseExpress(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowedLibertyBaseExpress(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowedLibertyBaseExpress(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }

    @Step("Проверка на отображение уведомления об ошибке в поле 'Cумма депозита' депозита 'Liberty+ Валютный'")
    public void assertErrorMessageSumIsDisplayedLibertyCurrency(Double depositSum) {
        if (depositApplicationPage.isDepositSumLessThanMinAllowedLibertyCurrency(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorLessAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.LITTLE_SUM.getMessage());
        } else if (depositApplicationPage.isDepositSumMoreThanMaxAllowedLibertyCurrency(depositSum)) {
            Assertions.assertEquals(depositApplicationPage.textErrorMoreAmountDepositIsDisplayed(), true, DepositApplicationPage.ErrorMessage.BIG_SUM.getMessage());
        } else {
            Assertions.fail("Неизвестный тип ошибки");
        }
    }
}
