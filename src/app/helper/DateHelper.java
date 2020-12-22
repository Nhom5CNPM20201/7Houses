package app.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class DateHelper {
    public static String getDateString(Date date) {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateString = df.format(date);
        return dateString;
    }

    public static long getDaysBetween(Date date_1, Date date_2) {
        if (date_1 == null || date_2 == null) return 1000000000;

        LocalDate lcDate_1 = Instant.ofEpochMilli(date_1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lcDate_2 = Instant.ofEpochMilli(date_2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        return ChronoUnit.DAYS.between(lcDate_1, lcDate_2);
    }

    public static LocalDate convertLocalDate(Date date) {
        if (date == null) return null;
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
