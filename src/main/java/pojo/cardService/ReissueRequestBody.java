package pojo.cardService;


import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ReissueRequestBody {
    String accountId;
    String customerId;
    String productTypeId;
    String status;
}
