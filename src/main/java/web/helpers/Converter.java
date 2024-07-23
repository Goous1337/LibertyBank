package web.helpers;

import web.constans.deposit.DepositsConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Converter {

    public Converter() {
    }

    public static Integer changePeriodOfCredit (String value) {
           String[] words = value.split(" ");
           if (words.length == 4) {
               Integer years = parseInt(words[0]) * 12;
               Integer month = parseInt(words[2]);
               Integer period = years + month;
               return period;
           }
           if (words.length == 2) {
               Integer year = parseInt(words[0]) * 12;
               return year;
           }
           return null;
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

    public static Double convertValueToDouble(String value) {
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

    public static Double convertCurrencyValueToDouble(String value) {
        value = String.valueOf(value).replace(DepositsConstants.DELIMITER, DepositsConstants.POINT).trim();
        value = String.valueOf(value).replace(DepositsConstants.PROCENT, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.SPACE, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.RUB, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.EUR, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.USD, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.CAPS_RUB, "").trim();
        value = String.valueOf(value).replace(DepositsConstants.CAPS_USD, "").trim();

        String[] parts = value.split("\\\\");
        String number = parts[0];

        return Double.parseDouble(number);
    }

    public static int convertToInteger(String value) {
        value = String.valueOf(value).replace(DepositsConstants.MONTHS, "").trim();
        return parseInt(value);
    }

    public static String convertStringToString(String value) {
        value = String.valueOf(value).replace("№", "");
        value = String.valueOf(value).replace("счёта:", "");
        value = String.valueOf(value).replace("счета:", "");
        value = String.valueOf(value).replace(" ", "");
        return value;
    }

    public static Integer convertStringToInteger(String str) {
        str = String.valueOf(str).replace("₽", "").trim();
        str = String.valueOf(str).replace("$", "").trim();
        str = String.valueOf(str).replace("%", "").trim();
        str = String.valueOf(str).replace("месяцев", "").trim();
        str = String.valueOf(str).replace("мес.", "").trim();
        str = String.valueOf(str).replace("от", "").trim();
        str = String.valueOf(str).replace("до", "").trim();
        str = String.valueOf(str).replace(" ", "").trim();
        str = String.valueOf(str).replace(",", "").trim();

        return parseInt(str);
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

    public static String changeDate(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DepositsConstants.DATE_FORMAT);
        java.sql.Date result = null;
        try {
            result = new java.sql.Date(dateFormat.parse(value).getTime());
        } catch (ParseException e) {
            System.err.println(e);
        }
        String date = result.toString();
        return date;
    }

    public static List<String> parseToList(String str) {
        List<String> list = new ArrayList<>();
        list.add(str);
        return list;
    }

    public static String convertInterestRateToString(Double number) {
        String str = String.valueOf(number).replace(".", ",") + "%";
        return str;
    }
}
