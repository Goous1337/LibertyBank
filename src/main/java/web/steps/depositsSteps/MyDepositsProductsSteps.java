package web.steps.depositsSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.depositPages.MyDepositsProductsPage;

public class MyDepositsProductsSteps {

    private MyDepositsProductsPage myDepositsProductsPage;

    public MyDepositsProductsSteps() {
        myDepositsProductsPage = new MyDepositsProductsPage();
    }

    public void clickDepositButton() {
        myDepositsProductsPage.clickDepositsButton();
    }

    @Step("Мои депозиты")
    public void myDepositButton() {
        myDepositsProductsPage.clickMyDepositsButton();
    }

    @Step
    public void assertObjects() {
        Assertions.assertEquals(myDepositsProductsPage.objectFromBack(), myDepositsProductsPage.objectFromWeb(), "text");
    }
    @Step("Нажатие кнопки 'Показать больше' действующего депозита у авторизированного пользователя")
    public void clickShowMoreAboutMyDepositButton() {
        myDepositsProductsPage.clickShowMoreAboutMyDepositButton();

    }

}
