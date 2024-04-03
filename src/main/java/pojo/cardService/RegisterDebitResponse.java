package pojo.cardService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDebitResponse {
    String id;
    String productType;
    String account;
    String customer;
    String cardSecureData;
    String cardStatus;
    String userLimit;
    String createdAt;
    String expiredAt;
    Boolean favourite;
    Boolean embossed;
    Boolean permitVirtualPayment;
    Integer balance;
    Integer closedAt;
}
