package service;

import io.restassured.response.Response;
import pojo.accountService.AccountData;
import pojo.accountService.ChangeAccountStatusRequest;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static constant.AccountServiceConstants.*;
import static constant.ApiEndpoints.ACCOUNTS_LIST;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;
import static io.restassured.http.Method.PATCH;
import static property.BaseProperties.CUSTOMER_ID;

public class AccountService {

    public Response getAccountsList() {
        return sendSimpleRequest(GET, ACCOUNTS_LIST,
                    getRP(HEADER, HEADER_CUSTOMER_ID, VALID_CUSTOMER_ID));
    }

    public Response checkCreateNewAccount(String currency, String accountType, Boolean isMain) {
        return sendSimpleRequest(POST, ACCOUNTS_LIST,
                getRP(HEADER, HEADER_CUSTOMER_ID, VALID_CUSTOMER_ID),
                new AccountData(currency, accountType, isMain));
    }

    public Response checkCreateNewAccountWithInvalidUserData(String currency, String accountType, Boolean isMain) {
        return sendSimpleRequest(POST, ACCOUNTS_LIST,
                getRP(HEADER, HEADER_CUSTOMER_ID, INVALID_CUSTOMER_ID),
                new AccountData(currency, accountType, isMain));

    public Response changeAccountStatus(String accountId, String status) {
        return sendSimpleRequest(PATCH, ACCOUNTS_LIST + "/" + accountId,
                new ChangeAccountStatusRequest(status));
    }
}
