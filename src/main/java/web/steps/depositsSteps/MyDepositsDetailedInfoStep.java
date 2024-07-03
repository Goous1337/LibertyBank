package web.steps.depositsSteps;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.depositPages.*;
import static constant.DepositConstants.*;


public class MyDepositsDetailedInfoStep {
    protected MyDepositsDetailedInfoPage myDepositsDetailedInfoPage;
    public MyDepositsDetailedInfoStep() {
        myDepositsDetailedInfoPage = new MyDepositsDetailedInfoPage();
    }

//    @Step("Сравнение подробной информации о депозите пользователя API и UI")
//    public void assertMoreInfoAboutMyDepositProduct(){
//        Assertions.assertEquals(myDepositsDetailedInfoPage.getMyDepositProductObjectFromBackEnd(),
//                myDepositsDetailedInfoPage.getMyDepositProductObjectFromWeb(),
//                "Актуальный результат не соответствует ожидаемому");
//    }

    @Step("Проверка отображения 'Название депозитного продукта' у активного пользователя")
    public void assertTextNameTitleDepositIsDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.depositProductNameTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Название депозита не отображается"));
        //TestListener.takeScreenshot();
    }

    @Step("Проверка отображения 'Номер счёта депозитного продукта' у активного пользователя")
    public void assertTextAccountNumberDepositIsDisplayed() {
        Assertions.assertTrue(myDepositsDetailedInfoPage.depAccountNumberTextDisplayed(), String.format(NOT_DISPLAYED_DEPOSIT_WEB_ELEMENT_MESSAGE, "Номер счёта депозита не отображается"));
        //TestListener.takeScreenshot();
    }

}
