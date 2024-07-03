package web.steps.depositsSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.constans.deposit.depositEnums.DepositsNameEnum;
import web.pages.depositPages.DepositsProductsFullInfoPage;

public class DepositsProductsFullInfoStep {
    private DepositsProductsFullInfoPage depositsProductsFullInfoPage;

    public DepositsProductsFullInfoStep() {
        depositsProductsFullInfoPage = new DepositsProductsFullInfoPage();
    }

    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty+ Детский")
    public void clickShowMoreButton() {
        depositsProductsFullInfoPage.clickShowMoreButton();
    }


    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty+ Расчетный")
    public void clickShowMoreButtonLibertyCalculated() {
        depositsProductsFullInfoPage.clickShowMoreButtonLibertyCalculated();
    }

    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty+ Валютный USD")
    public void clickShowMoreButtonLibertyCurrencyUsd() {
        depositsProductsFullInfoPage.clickShowMoreButtonLibertyCurrencyUsd();
    }

    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty+ Срочный")
    public void clickShowMoreButtonLibertyExpress() {
        depositsProductsFullInfoPage.clickShowMoreButtonLibertyExpress();
    }

    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty+ Базовый")
    public void clickShowMoreButtonLibertyBase() {
        depositsProductsFullInfoPage.clickShowMoreButtonLibertyBase();
    }

    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty Premium")
    public void clickShowMoreButtonLibertyPremium() {
        depositsProductsFullInfoPage.clickShowMoreButtonLibertyPremium();
    }

    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty Стандарт Срочный")
    public void clickShowMoreButtonLibertyStandardExpress() {
        depositsProductsFullInfoPage.clickShowMoreButtonLibertyStandardExpress();
    }

    @Step("Нажатие кнопки 'Показать больше' в депозите Liberty Стандарт")
    public void clickShowMoreButtonLibertyStandard() {
        depositsProductsFullInfoPage.clickShowMoreButtonLibertyStandard();
    }

    @Step("Сравнение данных полной информации о депозиты с БД и UI")
    public void assertFullInfoAboutDepositsProduct(DepositsNameEnum str) {
        Assertions.assertEquals(depositsProductsFullInfoPage.getObjectFromBack(str), depositsProductsFullInfoPage.getObjectFromWeb(str), "text");
    }
}
