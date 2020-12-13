package app.helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class DateHelper {
    public static long getDaysBetween(Date date_1, Date date_2) {
        if (date_1 == null || date_2 == null) return 1000000000;

        LocalDate lcDate_1 = Instant.ofEpochMilli(date_1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lcDate_2 = Instant.ofEpochMilli(date_2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        return ChronoUnit.DAYS.between(lcDate_1, lcDate_2);
    }
}
