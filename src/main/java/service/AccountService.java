package service;

import io.restassured.response.Response;
import pojo.accountService.AccountData;
import pojo.accountService.ChangeAccountNameRequest;
import pojo.accountService.ChangeAccountStatusRequest;
import pojo.accountService.ChangeMainAccountRequest;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static constant.AccountServiceConstants.*;
import static constant.ApiEndpoints.ACCOUNTS_LIST;
import static constant.ApiEndpoints.ACCOUNTS_LIST_BY_CUSTOMER_ID;
import static io.restassured.http.Method.*;

public class AccountService {

    public Response getAccountsList() {
        return sendSimpleRequest(GET, ACCOUNTS_LIST,
                getRP(HEADER, HEADER_CUSTOMER_ID, VALID_CUSTOMER_ID));
    }

    public Response getAccountsListByCustomerIdWithNoAccounts() {
        return sendSimpleRequest(GET, ACCOUNTS_LIST,
                getRP(HEADER, HEADER_CUSTOMER_ID, CUSTOMER_WITH_NO_ACCOUNTS));
    }

    public Response getAccountsListByInvalidCustomerId() {
        return sendRequestWithoutParams(GET, ACCOUNTS_LIST_BY_CUSTOMER_ID + INVALID_CUSTOMER_ID);
    }

    public Response getAccountsListByValidCustomerIdWithNoAccounts() {
        return sendRequestWithoutParams(GET, ACCOUNTS_LIST_BY_CUSTOMER_ID + CUSTOMER_WITH_NO_ACCOUNTS);
    }

    public Response getAccountsListByValidCustomerIdWithAccounts() {
        return sendRequestWithoutParams(GET, ACCOUNTS_LIST_BY_CUSTOMER_ID + VALID_CUSTOMER_ID);
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
    }

    public Response changeAccountStatus(String accountId, String status) {
        return sendSimpleRequest(PATCH, ACCOUNTS_LIST + "/" + accountId,
                new ChangeAccountStatusRequest(status));
    }

    public Response changeAccountName(String accountId, String accountName) {
        return sendSimpleRequest(PATCH, ACCOUNTS_LIST + "/" + accountId,
                new ChangeAccountNameRequest(accountName));
    }

    public Response getAccountInfoData(String accountId) {
        return sendRequestWithoutParams(GET, ACCOUNTS_LIST + "/" + accountId);
    }

    public Response setMainAccountStatus(String accountId, Boolean isMain) {
        return sendSimpleRequest(PATCH, ACCOUNTS_LIST + "/" + accountId,
                new ChangeMainAccountRequest(isMain));
    }
}
