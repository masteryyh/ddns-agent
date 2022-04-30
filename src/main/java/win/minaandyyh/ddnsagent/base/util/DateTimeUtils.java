package win.minaandyyh.ddnsagent.base.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Useful Date&Time utilities
 *
 * @author masteryyh
 */
public class DateTimeUtils {
    private static final DateTimeFormatter UTC_FORMATTER = DateTimeFormatter.ISO_INSTANT;

    private DateTimeUtils() {}

    /**
     * Get current UTC time and format in yyyy-MM-dd'T'HH:mm:ssZ format.
     *
     * @return Formatted UTC time
     */
    public static String getUTCString() {
        return LocalDateTime.now(ZoneOffset.UTC).format(UTC_FORMATTER);
    }
}
