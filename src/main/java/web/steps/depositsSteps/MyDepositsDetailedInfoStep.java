package web.steps.depositsSteps;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import web.pages.depositPages.*;

public class MyDepositsDetailedInfoStep {
    protected MyDepositsDetailedInfoPage myDepositsDetailedInfoPage;
    public MyDepositsDetailedInfoStep() {
        myDepositsDetailedInfoPage = new MyDepositsDetailedInfoPage();
    }
    @Step("Сравнение подробной информации о депозите пользователя в БД и UI")
    public void assertMoreInfoAboutMyDepositProduct(){
        Assertions.assertEquals(myDepositsDetailedInfoPage.getMyDepositProductObjectFromBackEnd(),
                myDepositsDetailedInfoPage.getMyDepositProductObjectFromWeb(),
                "Актуальный результат не соответствует ожидаемому");
    }
}
