package specs;

import pojo.cardService.ReissueRequestBody;

import static constant.AccountServiceConstants.*;
import static constant.CardServiceConstants.*;
import static constant.CardServiceConstants.STATUS_ACTIVE;

public class RequestBody {
    public static ReissueRequestBody buildReissueRequestBody(String accountId) {
        return ReissueRequestBody.builder()
                .accountId(accountId)
                .customerId(CUSTOMER_ID_WITH_ACTIVE_CARDS)
                .productTypeId(VALID_PRODUCT_TYPE_ID)
                .status(STATUS_ACTIVE)
                .build();
    }

    public static ReissueRequestBody reissueRequestBody = buildReissueRequestBody(VALID_ACCOUNT_ID);
    public static ReissueRequestBody reissueRequestBodyInvalidAccountId = buildReissueRequestBody(INVALID_ID);
    public static ReissueRequestBody reissueRequestBodyNullAccountId = buildReissueRequestBody(NULL_ACCOUNT_ID);
}