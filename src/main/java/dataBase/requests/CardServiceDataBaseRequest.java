package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static constant.CardServiceConstants.CUSTOMER_ID_WITH_ACTIVE_CARDS;
import static constant.LibertyServiceName.CARD_SERVICE_DB;
import static dataBase.DataBaseConnector.getDBConnection;

public class CardServiceDataBaseRequest {

    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getCardId(String cardStatus) {
        String sql = "SELECT id FROM card WHERE customer = '" + CUSTOMER_ID_WITH_ACTIVE_CARDS + "' AND card_status = '" + cardStatus + "' LIMIT 1";
        String cardId = getDBConnection(CARD_SERVICE_DB).queryForObject(sql, String.class);
        LOG.info(String.format("Получен id карты: %s", cardId));
        return cardId;
    }

    public static String getProductTypeId(String cardStatus) {
        String sql = "SELECT product_type FROM card WHERE customer = '" + CUSTOMER_ID_WITH_ACTIVE_CARDS + "' AND card_status = '" + cardStatus + "' LIMIT 1";
        String productTypeId = getDBConnection(CARD_SERVICE_DB).queryForObject(sql, String.class);
        LOG.info(String.format("Получен productTypeId карты: %s", productTypeId));
        return productTypeId;
    }

    public static String getPINByFirstTwelveNumbers(String firstTwelveNumbers) {
        LOG.info("Значение firstTwelveNumbers: " + firstTwelveNumbers);
        String sql = "SELECT pincode FROM card_secure_data WHERE first_twelve_numbers = '" + firstTwelveNumbers + "'";
        String oldPin = getDBConnection(CARD_SERVICE_DB).queryForObject(sql, String.class);
        LOG.info(String.format("Получен PIN-code карты: %s", oldPin));
        return oldPin;
    }
}
