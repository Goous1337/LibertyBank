package web.pages.depositPages;

import api.model.webAndApi.DepositProductService;
import api.model.webAndApi.credit.MyCreditMoreInformation;
import api.model.webAndApi.deposit.MyDepositMoreInfo;
import io.qameta.allure.Step;

import java.util.List;

public class MyDepositsDetailedInfoPage {
    public DepositProductService depositProductService;
    private String nameBackEnd;
    private String depAccountNumberBackEnd;
    private java.sql.Date openDateBackEnd;
    private java.sql.Date closeDateBackEnd;
    private Double interestRateBackEnd;
    private Double initialAmountBackEnd;
    private String currencyCodeBackEnd;
    private String nameWeb;
    private String depAccountNumberWeb;
    private java.sql.Date openDateWeb;
    private java.sql.Date closeDateWeb;
    private Double interestRateWeb;
    private Double initialAmountWeb;
    private String currencyCodeWeb;
    private List<MyDepositMoreInfo> listXpath;
    private List<MyDepositMoreInfo> listFromBackEnd;
    public MyDepositsDetailedInfoPage(){
        depositProductService = new DepositProductService();
    }

    public MyDepositMoreInfo getMyDepositProductObjectFromBackEnd() {
        depositProductService.getMoreInfoAboutMyDeposit();

    return new MyDepositMoreInfo();
    }

    public MyDepositMoreInfo getMyDepositProductObjectFromWeb() {
    return new MyDepositMoreInfo();
    }
}
