package web.steps.depositsSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.depositPages.DepositsProductsBankPage;
import web.pages.depositPages.DepositsProductsFullInfoPage;

public class DepositsProductsSteps {
    private DepositsProductsBankPage depositsProductsBankPage;
    private DepositsProductsFullInfoPage depositsProductsFullInfoPage;

    public DepositsProductsSteps() {
        depositsProductsBankPage = new DepositsProductsBankPage();
        depositsProductsFullInfoPage = new DepositsProductsFullInfoPage();
    }

    @Step("Нажатие кнопки 'Депозиты' в навбаре")
    public void clickDepositButton() {
        depositsProductsBankPage.clickDepositsButton();
    }

    @Step("Нажатие кнопки 'Депозиты Liberty Bank'")
    public void clickDepositsProductsBankButton() {
        depositsProductsBankPage.clickDepositsProductsBankButton();
    }

    @Step("Сравнение краткой информации по всем депозитным продуктам банка")
    public void assertShortInfoAboutDeposits(Enum nameOfDeposit) {
        Assertions.assertEquals(depositsProductsBankPage.getObjectFromBack(nameOfDeposit), depositsProductsBankPage.getObjectFromWeb(nameOfDeposit), "text");
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty Стандарт Срочный'")
    public void clickDepositLibertyStandardExpressRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyStandardExpressRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty+ Срочный'")
    public void clickDepositLibertyExpressRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyExpressRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty+ Детский'")
    public void clickDepositLibertyChildRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyChildRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty Базовый'")
    public void clickDepositLibertyBaseRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyBaseRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty Premium'")
    public void clickDepositLibertyPremiumRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyPremiumRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty Стандарт'")
    public void clickDepositLibertyStandardRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyStandardRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty Расчетный'")
    public void clickDepositLibertyCalculatedRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyCalculatedRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty Базовый Срочный'")
    public void clickDepositLibertyBaseExpressRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyBaseExpressRegistrationButton();
    }

    @Step("Нажатие кнопки 'Оформить' депозита 'Liberty+ Валютный'")
    public void clickDepositLibertyCurrencyRegistrationButton() {
        depositsProductsFullInfoPage.clickDepositsLibertyCurrencyRegistrationButton();
    }
}
