package web.epic_9;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("9 - Депозиты")
@Feature("US-9.4 Просмотр пользователем своих действующих депозитов")
@DisplayName("US-9.4 Просмотр пользователем своих действующих депозитов")
public class US_9_4_CheckMyDeposits extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        myDepositsProductsSteps.clickDepositButton();
        myDepositsProductsSteps.myDepositButton();

    }

    @Test
    @Tag("Positive")
    @DisplayName("US 9.4 Просмотр пользователем своих действующих депозитов")
    @TmsLink("LIB3-1895")
    public void checkMyDepositProducts() {
        myDepositsProductsSteps.assertObjects();
    }
}
