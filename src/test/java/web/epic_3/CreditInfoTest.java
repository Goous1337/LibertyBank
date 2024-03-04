package web.epic_3;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.BaseTest;

import org.junit.jupiter.api.BeforeEach;

import static web.constans.UrlConfig.*;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.2 Просмотр информации о кредитных продуктах банка")
@DisplayName("US-3.2 Просмотр информации о кредитных продуктах банка")
public class CreditInfoTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CREDIT_PRODUCTS_URL);
    }

    @Test
    @DisplayName("Проверка отображения информации о кредите - Liberty Наличными")
    public void checkLibertyCashCreditInfo(){
        creditInfoSteps.assertNameOfCredit();
        creditInfoSteps.assertProcentOfCredit();
        creditInfoSteps.assertDescription();
        creditInfoSteps.assertShowMore();
        creditInfoSteps.assertGetApplication();
    }
    @Test
    @DisplayName("Проверка отображения информации о кредите - Liberty Срочный")
    public void checkLibertyExpressCreditInfo(){
        creditInfoSteps.assertNameOfLibertyExpressCredit();
        creditInfoSteps.assertProcentLibertyExpressCredit();
        creditInfoSteps.assertDescriptionLibertyExpress();
        creditInfoSteps.assertShowMoreLibertyExpress();
        creditInfoSteps.assertGetApplicationLibertyExpress();
    }


}
