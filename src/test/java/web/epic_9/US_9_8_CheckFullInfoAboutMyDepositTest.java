package web.epic_9;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
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
        myDepositsProductsStep.clickShowMoreAboutMyDepositButton();
    }

    @Test
    @DisplayName("Просмотр элементов подробной информации о депозитном продукте пользователя")
    @TmsLink("LIB3-2671")
    public void checkInfoElementsAboutDeposit(){
        myDepositDetailedInfoStep.assertTextAccountNumberDepositIsDisplayed();
    }

//    @Test
//    @Disabled
//    @DisplayName("Просмотр подробной информации о депозитном продукте пользователя")
//    @TmsLink("LIB3-2671")
//    public void checkFullInfoAboutDeposit(){
//        myDepositDetailedInfoStep.assertMoreInfoAboutMyDepositProduct();
//    }

}
