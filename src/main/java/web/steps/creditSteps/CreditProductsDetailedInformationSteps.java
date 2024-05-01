package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.creditPages.CreditProductDetailedInformationPage;

public class CreditProductsDetailedInformationSteps {
    protected CreditProductDetailedInformationPage creditProductDetailedInformationPage;

    public CreditProductsDetailedInformationSteps() {
        creditProductDetailedInformationPage = new CreditProductDetailedInformationPage();
    }

    @Step("Сравнение подброной информации о кредитном продукте банка в БД и UI")
    public void assertMoreInfoAboutCreditProducts() {
        Assertions.assertEquals(creditProductDetailedInformationPage.getCreditProductObjectFromBackEnd(),
                creditProductDetailedInformationPage.getCreditProductObjectFromWeb(),
                "Актуальный результат не соответствует ожидаемому");
    }

    @Step("Нажать кнопку показать больше")
    public void clickShowMoreButton() {
        creditProductDetailedInformationPage.buttonCheckoutCredit();
    }
}
