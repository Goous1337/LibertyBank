package web.steps.creditSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.creditPages.CreditApplicationReportPage;
import web.pages.creditPages.CreditProdutsPage;

public class CreditApplicationReportSteps {
    protected CreditApplicationReportPage creditApplicationReportPage;

    public CreditApplicationReportSteps() {
        creditApplicationReportPage = new CreditApplicationReportPage();
    }

    @Step("Сравнение")
    public void assertCreditReport() {
        Assertions.assertEquals("", creditApplicationReportPage.putToMapValuesCreditReport(), "");
    }
}
