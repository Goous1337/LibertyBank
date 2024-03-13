package web;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.MoreCreditDetails;
import api.model.webAndApi.credit.MoreCreditProduct;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;


import web.drivers.DriverManager;
import web.helpers.TestListener;
import web.steps.*;


import static property.UserPropertiesReader.USER_PASSWORD;
import static property.UserPropertiesReader.USER_PHONE;
import static web.constans.UrlConfig.BASE_URL;
import static web.constans.UrlConfig.LOGIN_URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestListener.class)
public class BaseTest {
    protected static AccountSteps accountSteps;
    protected AccountInfoSteps accountInfoSteps;
    protected CreditInfoSteps creditInfoSteps;
    protected CreateAccountSteps createAccountSteps;
    protected ConfirmationSteps confirmationSteps;
    protected RenameAccountSteps renameAccountSteps;
    protected LoginSteps loginSteps;
    protected HomeSteps homeSteps;
    protected SecuritySteps securitySteps;
    protected PersonalDataSteps personalDataSteps;
    protected ChangePasswordSteps changePasswordSteps;
    protected DropDownAccountMenuSteps dropDownAccountMenuSteps;
    protected ChangeNotificationStatusSteps changeNotificationStatusSteps;
    protected MoreCreditProduct moreCreditProduct;
    protected MoreCreditDetails moreCreditDetails;
    protected CreditProductService creditProductService;
    protected UpdateEmailSteps updateEmailSteps;
    protected CardProductsSteps cardProductsSteps;
    protected CardSteps cardSteps;
    protected CardInfoSteps cardInfoSteps;
    protected CardProductInfoSteps cardProductInfoSteps;
    protected CloseCardSteps closeCardSteps;
    protected FilterCardsSteps filterCardsSteps;

    public BaseTest() {
        refreshPages();
    }

    protected void open(String pageUrl) {
        DriverManager.getDriver()
                .get(BASE_URL + pageUrl);
    }

    protected void authorization() {
        open(LOGIN_URL);
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.enterPassword(USER_PASSWORD);
        loginSteps.tapSubmitButton();
    }

    @BeforeEach
    public void driverInitialization() {
        DriverManager.getDriver();
    }

    @AfterAll
    public void tearDown() {
        TestListener.takeScreenshot();
        DriverManager.resetDriver();
    }

    private void refreshPages() {
        accountSteps = new AccountSteps();
        accountInfoSteps = new AccountInfoSteps();
        createAccountSteps = new CreateAccountSteps();
        confirmationSteps = new ConfirmationSteps();
        renameAccountSteps = new RenameAccountSteps();
        loginSteps = new LoginSteps();
        homeSteps = new HomeSteps();
        securitySteps = new SecuritySteps();
        changePasswordSteps = new ChangePasswordSteps();
        dropDownAccountMenuSteps = new DropDownAccountMenuSteps();
        changeNotificationStatusSteps = new ChangeNotificationStatusSteps();
        creditInfoSteps = new CreditInfoSteps();
        moreCreditProduct = new MoreCreditProduct();
        moreCreditDetails = new MoreCreditDetails();
        creditProductService = new CreditProductService();
        updateEmailSteps = new UpdateEmailSteps();
        personalDataSteps = new PersonalDataSteps();
        cardProductsSteps = new CardProductsSteps();
        cardSteps = new CardSteps();
        cardInfoSteps = new CardInfoSteps();
        cardProductsSteps = new CardProductsSteps();
        cardProductInfoSteps = new CardProductInfoSteps();
        closeCardSteps = new CloseCardSteps();
        filterCardsSteps = new FilterCardsSteps();
    }

}
