package web.helpers;

import web.constans.DepositsConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    public Converter() {

    }

    public static Double convertToDouble(String value) {
        value = String.valueOf(value).replace(DepositsConstants.DELIMITER, DepositsConstants.POINT).trim();
        value = String.valueOf(value).replace(DepositsConstants.PROCENT, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.SPACE, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.RUB, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.EUR, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.USD, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.CAPS_RUB, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.CAPS_USD, "").trim();
        return Double.parseDouble(value);
    }

    public static int convertToInteger(String value) {
        value = String.valueOf(value).replace(DepositsConstants.MONTHS, "").trim();
        return Integer.parseInt(value);
    }

    public static java.sql.Date parseDate(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT);
        java.sql.Date result = null;
        try {
            result = new java.sql.Date(dateFormat.parse(value).getTime());
        } catch (ParseException e) {
            System.err.println(e);
        }
        return result;
    }

    public static List<String> parseToList(String str) {
        List<String> list = new ArrayList<>();
        list.add(str);
        return list;
    }
}
