package app.helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ValidateHelper {
    public static String validateText(String content) throws Exception {
        if (content == null || content.equals("")) throw new Exception("Thông tin không hợp lệ.");

        content = content.trim();

        if (content.equals("")) throw new Exception("Thông tin không hợp lệ.");

        return content;
    }

    public static Date validateDate(LocalDate content) throws Exception {
        if (content == null) throw new Exception("Thông tin không hợp lệ.");

        Instant instant = Instant.from(content.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        return validateDate(date);
    }

    public static Date validateDate(Date content) throws  Exception {
        if (content == null) throw new Exception("Thông tin không hợp lệ.");

        return content;
    }
}
