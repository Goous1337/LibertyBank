package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.common.CommonCurrencyEnum;
import web.constans.credit.creditEnums.*;
import web.helpers.Converter;
import web.helpers.DateFormat;
import web.pages.BasePage;

import java.util.*;

import static web.helpers.Waiters.waitElement;

public class CreditApplicationReportPage extends BasePage {
    private CreditProductDetailedInformationPage creditProductDetailedInformationPage;
    private static CreditApplicationPage creditApplicationPage;
    private final Map<String, String> valuesXpathCreditReport;
    private final Map<String, String> valuesExpectedCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Название кредитного продукта:')]/following-sibling::td")
    private WebElement valueNameCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Дата создания заявки:')]/following-sibling::td")
    private WebElement valueDataCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Статус заявки:')]/following-sibling::td/p")
    private WebElement valueStatusCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Процентная ставка:')]/following-sibling::td")
    private WebElement valueInterestRateCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Запрашиваемая сумма кредита:')]/following-sibling::td")
    private WebElement valueSummaCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Валюта кредита:')]/following-sibling::td")
    private WebElement valueCurrencyCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Запрашиваемый период кредита:')]/following-sibling::td")
    private WebElement valueTermCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Способ получения:')]/following-sibling::td")
    private WebElement valueMethodOfObtainingCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Тип кредита:')]/following-sibling::td")
    private WebElement valueTypeCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Схема расчета платежей:')]/following-sibling::td")
    private WebElement valuePaymentСalculationSchemeCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Тип ставки:')]/following-sibling::td")
    private WebElement valueLoanRateTypeCreditReport;
    //Ключи
    @FindBy(xpath = "//*[contains(text(), 'Название кредитного продукта:')]")
    private WebElement nameCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Дата создания заявки:')]")
    private WebElement dataCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Статус заявки:')]")
    private WebElement statusCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Процентная ставка:')]")
    private WebElement interestRateCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Запрашиваемая сумма кредита:')]")
    private WebElement summaCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Валюта кредита:')]")
    private WebElement currencyCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Запрашиваемый период кредита:')]")
    private WebElement termCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Способ получения:')]")
    private WebElement methodOfObtainingCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Тип кредита:')]")
    private WebElement typeCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Схема расчета платежей:')]")
    private WebElement paymentСalculationSchemeCreditReport;
    @FindBy(xpath = "//*[contains(text(), 'Тип ставки:')]")
    private WebElement loanRateTypeCreditReport;
    @FindBy(xpath = "//button[contains(text(), 'Узнать статус заявки')]")
    private WebElement buttonStatusOfTheApplicationCredit;
    @FindBy(xpath = "//button[contains(text(), 'Отправить PDF')]")
    private WebElement buttonSendPdfApplicationCredit;
    @FindBy(xpath = "//h2[contains(text(), 'Отчет о заявке')]")
    private WebElement report;

    public CreditApplicationReportPage() {
        creditProductDetailedInformationPage = new CreditProductDetailedInformationPage();
        creditApplicationPage = new CreditApplicationPage();
        valuesExpectedCreditReport = new TreeMap<>();
        valuesXpathCreditReport = new TreeMap<>();
    }

    public void checkVisibleReport() {
        waitElement(report);
        report.isDisplayed();
    }

    public String getNameCreditReport() {
        return getTextElement(nameCreditReport);
    }

    public String getDataCreditReport() {
        return getTextElement(dataCreditReport);
    }

    public String getStatusCreditReport() {
        return getTextElement(statusCreditReport);
    }

    public String getInterestRateCreditReport() {
        return getTextElement(interestRateCreditReport);
    }

    private String getSummaCreditReport() {
        return getTextElement(summaCreditReport);
    }

    public String getCurrencyCreditReport() {
        return getTextElement(currencyCreditReport);
    }

    public String getTermCreditReport() {
        return getTextElement(termCreditReport);
    }

    public String getMethodOfObtainingCreditReport() {
        return getTextElement(methodOfObtainingCreditReport);
    }

    public String getTypeCreditReport() {
        return getTextElement(typeCreditReport);
    }

    public String getPaymentSchemeCreditReport() {
        return getTextElement(paymentСalculationSchemeCreditReport);
    }

    public String getLoanRateTypeCreditReport() {
        return getTextElement(loanRateTypeCreditReport);
    }

    public void putToMapXpathValuesCreditReport() {
        valuesXpathCreditReport.put(getNameCreditReport(), getTextElement(valueNameCreditReport));
        valuesXpathCreditReport.put(getDataCreditReport(), getTextElement(valueDataCreditReport));
        valuesXpathCreditReport.put(getStatusCreditReport(), getTextElement(valueStatusCreditReport));
        valuesXpathCreditReport.put(getInterestRateCreditReport(), getTextElement(valueInterestRateCreditReport));
        valuesXpathCreditReport.put(getSummaCreditReport(), Converter.convertStringToString(getTextElement(valueSummaCreditReport)));
        valuesXpathCreditReport.put(getCurrencyCreditReport(), getTextElement(valueCurrencyCreditReport));
        valuesXpathCreditReport.put(getTermCreditReport(), getTextElement(valueTermCreditReport));
        valuesXpathCreditReport.put(getMethodOfObtainingCreditReport(), getTextElement(valueMethodOfObtainingCreditReport));
        valuesXpathCreditReport.put(getTypeCreditReport(), getTextElement(valueTypeCreditReport));
        valuesXpathCreditReport.put(getPaymentSchemeCreditReport(), getTextElement(valuePaymentСalculationSchemeCreditReport));
        valuesXpathCreditReport.put(getLoanRateTypeCreditReport(), getTextElement(valueLoanRateTypeCreditReport));
    }

    public Map<String, String> putToMapCreditName() {
        valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_NAME_CONSTANT.getValueCreditReport(),
                creditProductDetailedInformationPage.getNameCreditProductPageText().getText());
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditInterestRate() {
        String interestRate = creditProductDetailedInformationPage.getInterestRateCreditProductText().getText();
        valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_INTEREST_RATE_CONSTANT.getValueCreditReport(),
                interestRate + " годовых");
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditData() {
        valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_DATA_CONSTANT.getValueCreditReport(),
                (DateFormat.getCurrentDate()));
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditStatus() {
        valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_STATUS_CONSTANT.getValueCreditReport(),
                CreditStatusEnum.CREDIT_STATUS_PENDING.getValueCreditStatus());
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditSum(String string) {
        String convertString = Converter.convertStringToString(string) + ",00";
        valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_SUMMA_CONSTANT.getValueCreditReport(),
                convertString);
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditCurrency() {
        searchCurrencyCreditToMap(valuesExpectedCreditReport);
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditTerm(String term) {
        valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_TERM_CONSTANT.getValueCreditReport(),
                term + " месяцев");
        return valuesExpectedCreditReport;
    }


    public Map<String, String> putToMapCreditMethodOfObtaining() {
        searchMethodOfObtainingCreditToMap(valuesExpectedCreditReport);
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditType() {
        searchTypeCreditValueToMap(valuesExpectedCreditReport);
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditScheme() {
        valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_PAYMENT_СALCULATION_SCHEME_CONSTANT.getValueCreditReport(),
                CreditPaymentSchemeEnum.CREDIT_TYPE_ANNUITY.getPaymentSchemeCredit());
        return valuesExpectedCreditReport;
    }

    public Map<String, String> putToMapCreditLoanRateType() {
        searchLoanRateCreditToMap(valuesExpectedCreditReport);
        return valuesExpectedCreditReport;
    }

    public String convertExpectedMapToString() {
        return convertWithIteration(valuesExpectedCreditReport);
    }

    public String convertXpatxMapToString() {
        return convertWithIteration(valuesXpathCreditReport).replace(":", "");
    }

    private String convertWithIteration(Map<String, String> map) {
        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : map.keySet()) {
            mapAsString.append(key + "=" + map.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }

    private static Map<String, String> searchLoanRateCreditToMap(Map<String, String> valuesExpectedCreditReport) {
        String stringCar = CreditNameEnum.LIBERTY_CAR.getValueCreditName();
        String stringEasy = CreditNameEnum.LIBERTY_EASY.getValueCreditName();
        String stringFlat = CreditNameEnum.LIBERTY_MY_FLAT.getValueCreditName();
        for (Map.Entry<String, String> key : valuesExpectedCreditReport.entrySet()) {
            if (key.getKey().contains(CreditReportValueEnum.CREDIT_REPORT_NAME_CONSTANT.getValueCreditReport())) {
                if (key.getValue().contains(stringCar) || key.getValue().contains(stringFlat) || key.getValue().contains(stringEasy)) {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_LOAN_RATE_TYPE_CONSTANT.getValueCreditReport(),
                            CreditLoanRateTypeEnum.CREDIT_LOAN_RATE_TYPE_LINKED.getLoanRateType());
                    break;
                } else {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_LOAN_RATE_TYPE_CONSTANT.getValueCreditReport(),
                            CreditLoanRateTypeEnum.CREDIT_LOAN_RATE_TYPE_FIX.getLoanRateType());
                    break;
                }
            }
        }
        return valuesExpectedCreditReport;
    }

    private static Map<String, String> searchCurrencyCreditToMap(Map<String, String> valuesExpectedCreditReport) {
        for (Map.Entry<String, String> key : valuesExpectedCreditReport.entrySet()) {
            if (key.getValue().contains(CreditNameEnum.LIBERTY_MONEY.getValueCreditName())) {
                if (creditApplicationPage.getCurrencyCreditText().equals(CommonCurrencyEnum.CURRENCY_EUR.getCurrency())) {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_CURRENCY_CONSTANT.getValueCreditReport(),
                            CommonCurrencyEnum.CURRENCY_EUR.getCurrency());
                    break;
                } else if (creditApplicationPage.getCurrencyCreditText().equals(CommonCurrencyEnum.CURRENCY_USD.getCurrency())) {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_CURRENCY_CONSTANT.getValueCreditReport(),
                            CommonCurrencyEnum.CURRENCY_USD.getCurrency());
                    break;
                }
            } else {
                valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_CURRENCY_CONSTANT.getValueCreditReport(),
                        CommonCurrencyEnum.CURRENCY_RUB.getCurrency());
                break;
            }
        }
        return valuesExpectedCreditReport;
    }

    private static Map<String, String> searchTypeCreditValueToMap(Map<String, String> valuesExpectedCreditReport) {
        String stringCar = CreditNameEnum.LIBERTY_CAR.getValueCreditName();
        String stringFlat = CreditNameEnum.LIBERTY_MY_FLAT.getValueCreditName();
        for (Map.Entry<String, String> key : valuesExpectedCreditReport.entrySet()) {
            if (key.getKey().contains(CreditReportValueEnum.CREDIT_REPORT_NAME_CONSTANT.getValueCreditReport())) {
                if (key.getValue().contains(stringCar) || key.getValue().contains(stringFlat)) {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_TYPE_CONSTANT.getValueCreditReport(),
                            CreditTypeEnum.CREDIT_TYPE_TARGET.getTypeCredit());
                    break;
                } else {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_TYPE_CONSTANT.getValueCreditReport(),
                            CreditTypeEnum.CREDIT_TYPE_CONSUMER.getTypeCredit());
                    break;
                }
            }
        }
        return valuesExpectedCreditReport;
    }

    private static Map<String, String> searchMethodOfObtainingCreditToMap(Map<String, String> valuesExpectedCreditReport) {
        String stringCash = CreditNameEnum.LIBERTY_CASH.getValueCreditName();
        String stringExpress = CreditNameEnum.LIBERTY_EXPRESS.getValueCreditName();
        for (Map.Entry<String, String> key : valuesExpectedCreditReport.entrySet()) {
            if (key.getKey().contains(CreditReportValueEnum.CREDIT_REPORT_NAME_CONSTANT.getValueCreditReport())) {
                if (key.getValue().contains(stringCash) || key.getValue().contains(stringExpress)) {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_METHOD_OF_OBTAINING_CONSTANT.getValueCreditReport(), CreditMethodOfObtainEnum.CREDIT_METHOD_OF_OBTAIN_CASH.getMethodOfObtaining());
                    break;
                } else {
                    valuesExpectedCreditReport.put(CreditReportValueEnum.CREDIT_REPORT_METHOD_OF_OBTAINING_CONSTANT.getValueCreditReport(), CreditMethodOfObtainEnum.CREDIT_METHOD_OF_OBTAIN_CARD.getMethodOfObtaining());
                    break;
                }
            }
        }
        return valuesExpectedCreditReport;
    }

}
