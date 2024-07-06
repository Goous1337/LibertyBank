package web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import api.model.webAndApi.CreditProductService;
import api.model.webAndApi.credit.MoreCreditDetails;
import api.model.webAndApi.credit.MoreCreditProduct;
import web.drivers.DriverManager;
import web.helpers.TestListener;
import web.steps.*;
import web.steps.accountSteps.AccountInfoSteps;
import web.steps.accountSteps.AccountSteps;
import web.steps.accountSteps.CreateAccountSteps;
import web.steps.accountSteps.RenameAccountSteps;
import web.steps.depositsSteps.*;
import web.steps.cardSteps.CardInfoSteps;
import web.steps.cardSteps.CardProductInfoSteps;
import web.steps.cardSteps.CardProductsSteps;
import web.steps.cardSteps.CardSteps;
import web.steps.cardSteps.CloseCardSteps;
import web.steps.cardSteps.ConfirmationSteps;
import web.steps.cardSteps.FilterCardsSteps;
import web.steps.creditSteps.*;
import web.steps.insuranceSteps.InsuranceApplicationPropertyContentsSteps;

import static property.UserPropertiesReader.USER_PASSWORD;
import static property.UserPropertiesReader.USER_PHONE;
import static web.constans.UrlConfig.BASE_URL;
import static web.constans.UrlConfig.LOGIN_URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestListener.class)
public class BaseTest {
    protected static AccountSteps accountSteps;
    protected AccountInfoSteps accountInfoSteps;
    protected DepositsProductsSteps depositsProductsSteps;
    protected CreditInfoSteps creditInfoSteps;
    protected CreditProductsDetailedInformationSteps creditProductDetailedInformationSteps;
    protected СreditProductsSteps creditProductsSteps;
    protected CreditApplicationSteps creditApplicationSteps;
    protected CreditApplicationReportSteps creditApplicationReportSteps;
    protected CreditMobileCodeVerificationSteps creditMobileCodeVerificationSteps;
    protected MyCreditSteps myCreditSteps;
    protected MyCreditDetailedInformationSteps myCreditDetailedInformationSteps;
    protected CreateAccountSteps createAccountSteps;
    protected ConfirmationSteps confirmationSteps;
    protected RenameAccountSteps renameAccountSteps;
    protected LoginSteps loginSteps;
    protected ResetPasswordSteps resetPasswordSteps;
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
    protected MyDepositsProductsStep myDepositsProductsStep;
    protected DepositsProductsFullInfoStep depositsProductsFullInfoStep;
    protected DepositsApplicationSteps depositsApplicationSteps;
    protected MyDepositsDetailedInfoStep myDepositDetailedInfoStep;

    public BaseTest() {
        refreshPages();
    }

    protected ChangePinSteps changePinSteps;
    protected InsuranceApplicationPropertyContentsSteps insuranceApplicationPropertyContentsSteps;



    protected void open(String pageUrl) {
        DriverManager.getDriver()
                .get(BASE_URL + pageUrl);
    }

    protected void authorization() {
        open(LOGIN_URL);
        loginSteps.clickInputPhone();
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.clickInputPassword();
        loginSteps.enterPassword(USER_PASSWORD);
        loginSteps.tapSubmitButtonToMain();
    }

    @BeforeEach
    public void driverInitialization() {
        DriverManager.getDriver();
        refreshPages();
    }

    @AfterEach
    public void clearCache() {
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
        resetPasswordSteps = new ResetPasswordSteps();
        securitySteps = new SecuritySteps();
        changePasswordSteps = new ChangePasswordSteps();
        dropDownAccountMenuSteps = new DropDownAccountMenuSteps();
        changeNotificationStatusSteps = new ChangeNotificationStatusSteps();
        creditInfoSteps = new CreditInfoSteps();
        creditProductsSteps = new СreditProductsSteps();
        creditApplicationSteps = new CreditApplicationSteps();
        creditProductDetailedInformationSteps = new CreditProductsDetailedInformationSteps();
        myCreditSteps = new MyCreditSteps();
        myCreditDetailedInformationSteps = new MyCreditDetailedInformationSteps();
        moreCreditProduct = new MoreCreditProduct();
        moreCreditDetails = new MoreCreditDetails();
        creditProductService = new CreditProductService();
        creditApplicationReportSteps = new CreditApplicationReportSteps();
        creditMobileCodeVerificationSteps = new CreditMobileCodeVerificationSteps();
        updateEmailSteps = new UpdateEmailSteps();
        personalDataSteps = new PersonalDataSteps();
        cardProductsSteps = new CardProductsSteps();
        cardSteps = new CardSteps();
        cardInfoSteps = new CardInfoSteps();
        cardProductsSteps = new CardProductsSteps();
        cardProductInfoSteps = new CardProductInfoSteps();
        closeCardSteps = new CloseCardSteps();
        filterCardsSteps = new FilterCardsSteps();
        depositsProductsSteps = new DepositsProductsSteps();
        myDepositsProductsStep = new MyDepositsProductsStep();
        depositsApplicationSteps = new DepositsApplicationSteps();
        myDepositDetailedInfoStep = new MyDepositsDetailedInfoStep();
        changePinSteps = new ChangePinSteps();
        insuranceApplicationPropertyContentsSteps = new InsuranceApplicationPropertyContentsSteps();
    }
}
