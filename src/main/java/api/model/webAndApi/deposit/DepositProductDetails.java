package api.model.webAndApi.deposit;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepositProductDetails {
    private String icon;
    private String header;
    private List<String> details;

    public DepositProductDetails(String icon, String header, List<String> details) {
        this.icon = icon;
        this.header = header;
        this.details = details;
    }

    public DepositProductDetails(String header, List<String> details) {
        this.header = header;
        this.details = details;
    }

    public DepositProductDetails(String header) {
        this.header = header;
    }

    public DepositProductDetails(List<String> details) {
        this.details = details;
    }

    public DepositProductDetails() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DepositProductDetails)) {
            return false;
        }

        DepositProductDetails depositProductDetails = (DepositProductDetails) o;
        return getHeader().equals(depositProductDetails.getHeader()) &&
                getDetails().equals(depositProductDetails.getDetails());
    }
}
