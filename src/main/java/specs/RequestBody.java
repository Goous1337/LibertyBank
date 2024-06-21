package specs;

import pojo.cardService.RegistryDebitBody;
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

    public static RegistryDebitBody buildRegistryDebitBody(String customerId) {
        return RegistryDebitBody.builder()
                .type_name(CARD_NAME_CLASSIC)
                .customer(customerId)
                .accountId(ACCOUNT_ID_DEBIT)
                .currency(CURRENCY_RUB)
                .paymentSystem(PAYMENT_SYSTEM)
                .deliveryType(DELIVERY_TYPE)
                .officeNumber(OFFICE_NUMBER)
                .build();
    }

    public static RegistryDebitBody REGISTER_DEBIT_BODY = buildRegistryDebitBody(CUSTOMER_ID_WITH_ACTIVE_CARDS);
    public static RegistryDebitBody REGISTER_DEBIT_BODY_INVALID_CUSTOMER_ID = buildRegistryDebitBody(CUSTOMER_ID_NOT_EXIST);
    public static RegistryDebitBody REGISTER_DEBIT_BODY_NULL_CUSTOMER_ID = buildRegistryDebitBody(NULL_ACCOUNT_ID);
}