package api.model.webAndApi.deposit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepositProductDetails {
    private String icon;
    private String header;
    private List<String> depositDetails;

    public DepositProductDetails() {

    }
}
