package web.epic_4;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;
import static web.constans.AccountServiceConstants.NOT_EQUALS_MESSAGE;
import static web.constans.UrlConfig.ACCOUNTS_URL;

@Tag("Web")
@Epic("4 - Счета")
@Feature("US-4.4.3 Изменение пользовательского названия счета")
@DisplayName("US-4.4.3 Изменение пользовательского названия счета")
public class ChangingUserAccountNameTest extends BaseTest {

    public static final String VALID_ACCOUNT_NAME = "Иван_IvanovЁ@#$%&!?~1234567890";
    public static final String INVALID_LONG_ACCOUNT_NAME = "Иван_Ivanov любит брокколи на завтрак";
    public static final String INVALID_SHORT_ACCOUNT_NAME = " ";
    public static final String INVALID_CHARACTERS_ACCOUNT_NAME = "小林さんは花子さんに花を上げました。";

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
        Assertions.assertTrue(confirmationSteps.isSuccessRenameAccountNameDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        confirmationSteps.returnToAccount();
        Assertions.assertEquals(VALID_ACCOUNT_NAME, accountInfoSteps.getAccountName(), String.format(NOT_EQUALS_MESSAGE, "Название счета"));
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
        Assertions.assertTrue(renameAccountSteps.isInvalidCharactersMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение"));
    }
}
