package api.model.webAndApi.credit;

import lombok.Setter;

import java.util.ArrayList;

public class CreditDetails {
    @Setter
    private String icon;
    @Setter
    private String header;
    @Setter
    private ArrayList<String> details;
}
