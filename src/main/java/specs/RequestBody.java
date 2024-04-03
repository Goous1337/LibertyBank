package specs;

import pojo.cardService.RegistrDebitBody;
import pojo.cardService.ReissueRequestBody;

import static constant.AccountServiceConstants.*;
import static constant.CardServiceConstants.STATUS_ACTIVE;
import static constant.CardServiceConstants.*;

public class RequestBody {
    public static ReissueRequestBody buildReissueRequestBody(String accountId) {
        return ReissueRequestBody.builder()
                .accountId(accountId)
                .customerId(CUSTOMER_ID_WITH_ACTIVE_CARDS)
                .productTypeId(VALID_PRODUCT_TYPE_ID)
                .status(STATUS_ACTIVE)
                .build();
    }

    public static ReissueRequestBody REISSUE_REQUEST_BODY = buildReissueRequestBody(VALID_ACCOUNT_ID);
    public static ReissueRequestBody REISSUE_REQUEST_BODY_INVALID_ACCOUNT_ID = buildReissueRequestBody(INVALID_ID);
    public static ReissueRequestBody REISSUE_REQUEST_BODY_NULL_ACCOUNT_ID = buildReissueRequestBody(NULL_ACCOUNT_ID);

    public static RegistrDebitBody buildRegistrDebitBody(String customerId) {
        return RegistrDebitBody.builder()
                .customer(customerId)
                .productType(VALID_PRODUCT_TYPE_ID)
                .account(VALID_ACCOUNT_ID)
                .favourite(CARD_IS_NOT_FAVORITE)
                .build();
    }

    public static RegistrDebitBody REGISTER_DEBIT_BODY = buildRegistrDebitBody(CUSTOMER_ID_WITH_ACTIVE_CARDS);
    public static RegistrDebitBody REGISTER_DEBIT_BODY_INVALID_CUSTOMER_ID = buildRegistrDebitBody(CUSTOMER_ID_NOT_EXIST);
    public static RegistrDebitBody REGISTER_DEBIT_BODY_NULL_CUSTOMER_ID = buildRegistrDebitBody(NULL_ACCOUNT_ID);
}