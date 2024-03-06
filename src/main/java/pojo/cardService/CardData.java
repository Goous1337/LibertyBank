package pojo.cardService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardData {
    private String id;
    private String typeName;
    private String paymentSystem;
    private boolean credit;
    private boolean active;
    private Number costPerMonth;
    private Number freeCostFrom;
    private Number servicePrice;
    private Number cardReissue;
    private Number addCardCost;
    private boolean virtual;
    private String level;
    private String currency;
    private Number validityTerm;
    private boolean oneUse;
    private String firstTwelveNumbers;
    private String lastFourNumbers;
    private String account;
    private Number balance;
    private String closedAt;
    private String cardStatus;
    private boolean favourite;

    public CardData() {

    }
}
