package star.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author keshawn
 * @date 2017/12/28
 */
public final class DateUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);


    private DateUtil() {
    }

    public static java.util.Date toUtilDate(String string) {

        try {
            synchronized (SIMPLE_DATE_FORMAT) {
                return SIMPLE_DATE_FORMAT.parse(string);
            }
        } catch (ParseException e) {
            LOGGER.error("String cast to date error", e);
        }
        return null;
    }

    public static java.util.Date toUtilDate(Instant instant) {
        return java.util.Date.from(instant);
    }

    public static java.util.Date toUtilDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return toUtilDate(instant);
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(date.toInstant());
    }

    public static java.util.Date toUtilDate(java.sql.Date sqlDate) {
        return sqlDate == null
                ? null
                : new java.util.Date(sqlDate.getTime());
    }

    public static java.util.Date toUtilDate(Timestamp timestamp) {
        return timestamp == null
                ? null
                : new java.util.Date(timestamp.getTime());
    }

    public static java.sql.Date toSqlDate(java.util.Date utilDate) {
        return utilDate == null
                ? null
                : new java.sql.Date(utilDate.getTime());
    }

    public static java.sql.Date toSqlDate(LocalDateTime localDateTime) {
        return localDateTime == null
                ? null
                : new java.sql.Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public static String toString(java.util.Date utilDate) {
        synchronized (SIMPLE_DATE_FORMAT) {
            return SIMPLE_DATE_FORMAT.format(utilDate);
        }
    }

    public static LocalDate toLocalDate(java.sql.Date sqlDate) {
        return sqlDate == null
                ? null
                : sqlDate.toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp == null
                ? null
                : timestamp.toLocalDateTime();
    }

    public static Instant toInstant(Timestamp timestamp) {
        return timestamp == null
                ? null
                : timestamp.toInstant();
    }

    public static String toString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    public static Timestamp toTimestamp(Instant instant) {
        return instant == null
                ? null
                : Timestamp.from(instant);
    }
}
