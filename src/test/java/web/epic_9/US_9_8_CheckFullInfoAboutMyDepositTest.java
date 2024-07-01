package web.epic_9;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import web.BaseTest;

@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("9 - Депозиты")
@Feature("US-9.8 Просмотр подробной информации о действующем депозите пользователя")
@DisplayName("US-9.8 Просмотр подробной информации о действующем депозите пользователя")
public class US_9_8_CheckFullInfoAboutMyDepositTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        depositsProductsSteps.clickDepositButton();
        myDepositsProductsSteps.clickShowMoreAboutMyDepositButton();
    }


}
