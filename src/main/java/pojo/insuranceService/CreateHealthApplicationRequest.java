package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHealthApplicationRequest {
    private String type;
    private HealthApplication healthApplication;
}
