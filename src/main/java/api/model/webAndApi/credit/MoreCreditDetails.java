package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class MoreCreditDetails {
    private String icon;
    private String header;
    private ArrayList<String> details;

    public MoreCreditDetails() {

    }

    public MoreCreditDetails(String header) {
        this.header = header;
    }

    public MoreCreditDetails(String icon, String header, ArrayList<String> details) {
        this.icon = icon;
        this.header = header;
        this.details = details;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoreCreditDetails)) {
            return false;
        }
        MoreCreditDetails moreCreditDetails = (MoreCreditDetails) obj;
        return getHeader().equals(moreCreditDetails.getHeader());
    }

}
