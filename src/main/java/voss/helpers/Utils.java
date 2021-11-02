package voss.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {

    public static String getTodayDateWithTime(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime currDate = LocalDateTime.now();
        return dtf.format(currDate);
    }
}
