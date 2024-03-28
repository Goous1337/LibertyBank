package pojo.cardService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReissueBadRequest {
    String timestamp;
    String error;
    Integer status;

}
