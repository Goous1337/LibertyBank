package service;

import io.restassured.response.Response;
import pojo.accountService.ChangeAccountStatusRequest;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static constant.AccountServiceConstants.HEADER_CUSTOMER_ID;
import static constant.AccountServiceConstants.VALID_CUSTOMER_ID;
import static constant.ApiEndpoints.ACCOUNTS_LIST;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;
import static property.BaseProperties.CUSTOMER_ID;

public class AccountService {

    public Response getAccountsList() {
        return sendSimpleRequest(GET, ACCOUNTS_LIST,
                    getRP(HEADER, HEADER_CUSTOMER_ID, VALID_CUSTOMER_ID));
    }

    public Response changeAccountStatus(String accountId, String status) {
        return sendSimpleRequest(PATCH, ACCOUNTS_LIST + "/" + accountId,
                new ChangeAccountStatusRequest(status));
    }
}
