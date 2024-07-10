package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;


@Tags({@Tag("WEB"), @Tag("MVP")})
@Epic("3 - Кредиты")
@Feature("US-3.4 Получение списка кредитных заявок пользователя")
@DisplayName("US-3.4 Получение списка кредитных заявок пользователя")

public class US_3_4_GetListUsersLoanApplicationTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        creditInfoSteps.clickCreditButton();
    }

    @Test
    @DisplayName("Отображение информации о кредитной заявке пользователя")
    @TmsLink("LIB3")
    public void checkBasicInfoLoanApplications() {
        creditInfoSteps.clickSubmittedCreditRequestButton();
        applicationInfoSteps.assertAmountOfCreditApplications();
        applicationInfoSteps.assertNameCreditProduct();
        applicationInfoSteps.assertStatusOfCreditApplication();
        applicationInfoSteps.assertSumOfCredit();
        applicationInfoSteps.assertPercentOfCredit();
        applicationInfoSteps.assertDateOfCredit();
        applicationInfoSteps.assertDateOfCreateApplication();
    }

    @Test
    @Disabled
    @DisplayName("Отображение сообщения об отсутствии заявок на кредит")
    @TmsLink("LIB3")
    public void checkMessageIaDisplayed() {
        creditInfoSteps.clickSubmittedCreditRequestButton();
        applicationInfoSteps.assertMessageNoApplications();
    }

    @Test
    @DisplayName("Сравнение данных заявки")
    @TmsLink("LIB3")
    public void checkDataWebAndBackEnd() {
        creditInfoSteps.clickSubmittedCreditRequestButton();
        applicationInfoSteps.assertDataOfApplication();
    }
}
