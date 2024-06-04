package pojo.investmentService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"timestamp"})
public class CustomerIdNotFoundResponse {
    public String uri;
    public String type;
    public String message;
    public int timestamp;
}
