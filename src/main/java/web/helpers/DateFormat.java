package web.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {

    public static String getCurrentDate() {
        LocalDate date;
        date = LocalDate.now();
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
