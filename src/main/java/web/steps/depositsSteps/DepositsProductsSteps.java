package web.steps.depositsSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.depositPages.DepositsProductsBankPage;

public class DepositsProductsSteps {
    private DepositsProductsBankPage depositsProductsBankPage;

    public DepositsProductsSteps() {
        depositsProductsBankPage = new DepositsProductsBankPage();
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
}
