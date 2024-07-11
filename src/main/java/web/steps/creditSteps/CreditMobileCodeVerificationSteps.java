package web.steps.creditSteps;

import io.qameta.allure.Step;

public class CreditMobileCodeVerificationSteps {
    protected CreditMobileCodeVerificationPage creditMobileCodeVerificationPage;

    public CreditMobileCodeVerificationSteps() {
        creditMobileCodeVerificationPage = new CreditMobileCodeVerificationPage();
    }

    @Step("Ввод 6-ти значного кода")
    public void enterGenerationCode(String code) {
        creditMobileCodeVerificationPage.sendCode(code);
    }

    @Step("Продолжить действия после ввода 6-ти значного кода")
    public void clickNextButton() {
        creditMobileCodeVerificationPage.clickNextButton();
    }

}
