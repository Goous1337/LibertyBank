package web.epic_4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.AccountServiceConstants.*;
import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.4.3 Изменение пользовательского названия счета")
@DisplayName("US-4.4.3 Изменение пользовательского названия счета")
public class ChangingUserAccountNameTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ACCOUNTS_URL);
    }

    @Test
    @TmsLink("LIB2-2487")
    @DisplayName("Отмена переименования счета")
    public void cancelingAccountRenaming() {
        accountSteps.clickAccount();
        accountInfoSteps.clickPencil();
        renameAccountSteps.clickNewAccountNameTextField();
        renameAccountSteps.setValueInNewAccountNameTextField(VALID_ACCOUNT_NAME);
        renameAccountSteps.cancel();
    }

    @Test
    @TmsLink("LIB2-2488")
    @DisplayName("Переименование счета")
    public void accountRemaining() {
        accountSteps.clickAccount();
        accountInfoSteps.clickPencil();
        renameAccountSteps.clickNewAccountNameTextField();
        renameAccountSteps.setValueInNewAccountNameTextField(VALID_ACCOUNT_NAME);
        renameAccountSteps.save();
        confirmationSteps.assertSuccessRenameAccountNameDialogBoxIsDisplayed();
        confirmationSteps.returnToAccount();
        accountInfoSteps.assertActualAccountNameEqualsExpectedName();
    }

    @Test
    @TmsLink("LIB2-2489")
    @DisplayName("Отображение сообщения при вводе более 30 символов для переименования счета")
    public void displayingMessageWhenEnteringMoreThanThirtyCharacters() {
        accountSteps.clickAccount();
        accountInfoSteps.clickPencil();
        renameAccountSteps.clickNewAccountNameTextField();
        renameAccountSteps.setValueInNewAccountNameTextField(INVALID_LONG_ACCOUNT_NAME);
        renameAccountSteps.save();

        Assertions.assertTrue(renameAccountSteps.isMoreThan30SymbolsMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение"));

        renameAccountSteps.assertEmptyMessageIsDisplayed();
    }

    @Test
    @TmsLink("LIB2-2490")
    @DisplayName("Отображение сообщения при вводе менее 1 символа или только пробела для переименования счета")
    public void displayingMessageWhenEnteringLessThanOneCharacterOrOnlySpace() {
        accountSteps.clickAccount();
        accountInfoSteps.clickPencil();
        renameAccountSteps.clickNewAccountNameTextField();
        renameAccountSteps.setValueInNewAccountNameTextField(INVALID_SHORT_ACCOUNT_NAME);
        renameAccountSteps.save();
        Assertions.assertTrue(renameAccountSteps.isChangeFieldNameMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение"));

        renameAccountSteps.assertMoreThenThirtyCharactersMessageIsDisplayed();
    }

    @Test
    @TmsLink("LIB2-2491")
    @DisplayName("Отображение сообщения при вводе недопустимых символов для переименования счета")
    public void displayingMessageWhenEnteringInvalidCharacters() {
        accountSteps.clickAccount();
        accountInfoSteps.clickPencil();
        renameAccountSteps.clickNewAccountNameTextField();
        renameAccountSteps.setValueInNewAccountNameTextField(INVALID_CHARACTERS_ACCOUNT_NAME);
        renameAccountSteps.save();
        renameAccountSteps.assertInvalidCharactersMessageIsDisplayed();
    }
}
