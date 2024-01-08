package dataBase.requests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import pojo.absClientService.AbsClientServiceShortInfoData;

import static constant.LibertyServiceName.ABS_CLIENT_SERVICE_BD;
import static dataBase.DataBaseConnector.getDBConnection;

public class AbsClientServiceDataBaseRequest {
    private static final Logger LOG = LogManager.getLogger("DBRequest");

    public static String getClientPassportSeries(int id) {
        String sql = "SELECT series FROM passport WHERE id =?";
        String passportSeries = getDBConnection(ABS_CLIENT_SERVICE_BD).queryForObject(sql, String.class, id);
        LOG.info(String.format("Получена серия пасспорта: %s по id: %s", passportSeries, id));
        return passportSeries;
    }

    public static String getClientPassportNumber(int id) {
        String sql = "SELECT number FROM passport WHERE id =?";
        String passportNumber = getDBConnection(ABS_CLIENT_SERVICE_BD).queryForObject(sql, String.class, id);
        LOG.info(String.format("Получен номер паспорта: %s по id: %s", passportNumber, id));
        return passportNumber;
    }

    public static AbsClientServiceShortInfoData getClientData(int id) {
        String sql = """
                SELECT customer_uuid, first_name, last_name, patronymic
                FROM passport
                JOIN customer ON passport.id=customer.passport_id
                WHERE passport.id=?
                """;
        AbsClientServiceShortInfoData absClientServiceShortInfoData =
                getDBConnection(ABS_CLIENT_SERVICE_BD).queryForObject
                        (sql, new Object[]{id}, new BeanPropertyRowMapper<>(AbsClientServiceShortInfoData.class));
        LOG.info(String.format("Получена информация о пользователе по id: %s", id));
        return absClientServiceShortInfoData;
    }

    public static String getClientUuid(int id) {
        String sql = "SELECT customer_uuid FROM customer WHERE passport_id=?";
        String clientUuid = getDBConnection(ABS_CLIENT_SERVICE_BD).queryForObject(sql, String.class, id);
        LOG.info(String.format("Получен uuid клиента: %s по id %s", clientUuid, id));
        return clientUuid;
    }
}
