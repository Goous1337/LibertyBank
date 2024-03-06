package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CreditDetails {

    private String icon;
    private String header;
    private ArrayList<String> details;
}
