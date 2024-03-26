package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.creditPages.CreditProdutsPage;

public class СreditProductsSteps {
    protected CreditProdutsPage creditProdutsPage;

    public СreditProductsSteps() {
        creditProdutsPage = new CreditProdutsPage();
    }

    /*Краткая информация о Кредитных продуктах банка*/
    @Step("Сравнение названия кредита Liberty Cash в БД и UI")
    public void assertShortInfoAboutCreditProducts(Enum nameOfCredit) {
        Assertions.assertEquals(creditProdutsPage.getObjectFromBackEnd(nameOfCredit), creditProdutsPage.getObjectFromWeb(nameOfCredit), "test");
    }

    /*Клик кнопки 'Показать больше'*/
    @Step("Клик кнопки 'Показать больше' у Liberty Наличными '")
    public void clickShowMoreLibertyCashButton() {
        creditProdutsPage.clickButtonShowMoreLibertyCash();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Срочный '")
    public void clickShowMoreLibertyExpressButton() {
        creditProdutsPage.clickButtonShowMoreLibertyExpress();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Money '")
    public void clickShowMoreLibertyMoneyButton() {
        creditProdutsPage.clickButtonShowMoreLibertyMoney();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Easy '")
    public void clickShowMoreLibertyEasyButton() {
        creditProdutsPage.clickButtonShowMoreLibertyEasy();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Car '")
    public void clickShowMoreLibertyCarButton() {
        creditProdutsPage.clickButtonShowMoreLibertyCar();
    }

    @Step("Клик кнопки 'Показать больше' у Liberty Моя квартира '")
    public void clickShowMoreLibertyMyFlatButton() {
        creditProdutsPage.clickButtonShowMoreLibertyMyFlat();
    }

}
