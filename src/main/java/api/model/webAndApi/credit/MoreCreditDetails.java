package api.model.webAndApi.credit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class MoreCreditDetails {

    private String icon;
    private String header;
    private ArrayList<String> details;
}
