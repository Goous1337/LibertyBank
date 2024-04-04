package constant;

import lombok.Getter;

@Getter
public enum CardProducts {

    LIBERTY_CARD_GOLD("Liberty Card Gold"),
    LIBERTY_CARD_CHILD("Liberty Card Child"),
    LIBERTY_CARD_PLATINUM("Liberty Card Platinum"),
    LIBERTY_CARD_VIRTUAL("Liberty Card Virtual"),
    LIBERTY_CARD_CLASSIC("Liberty Card Classic"),
    LIBERTY_CARD_SECURE("Liberty Card Secure"),
    LIBERTY_CARD_TRAVEL("Liberty Card Travel");

    private final String cardName;

    CardProducts(String cardName) {
        this.cardName = cardName;
    }
}