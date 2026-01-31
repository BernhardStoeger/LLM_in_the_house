package scead.llminthehouse.base.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils
{
    private DateUtils()
    {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime now()
    {
        return LocalDateTime.now();
    }

    public static LocalDate today()
    {
        return LocalDate.now();
    }

    public static String format(LocalDate date)
    {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "";
    }

    public static String format(LocalDateTime date)
    {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) : "";
    }

    public static String isoDate(LocalDate date)
    {
        return date != null ? date.format(DateTimeFormatter.ISO_DATE) : "";
    }

    public static String isoDateTime(LocalDateTime dateTime)
    {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ISO_DATE_TIME) : "";
    }

    public static LocalDateTime parseStringDateTime(String date)
    {
        if (StringUtils.isBlank(date))
        {
            return null;
        }
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDate parseStringDate(String date)
    {
        if (StringUtils.isBlank(date))
        {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    public static String parseDatetoString(LocalDateTime date)
    {
        if (date == null)
        {
            return null;
        }
        return date.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDate getLastDayOfPreviousMonth(LocalDate date)
    {
        return date.withDayOfMonth(1).minusDays(1);
    }

    public static LocalDate getLastDayOfMonth(LocalDate date)
    {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    public static LocalDate getFirstDayOfMonth(LocalDate date)
    {
        return date.withDayOfMonth(1);
    }

    public static LocalDate getFirstDayOfTheYear(LocalDate date)
    {
        return date.withMonth(1).withDayOfMonth(1);
    }

    public static LocalDate getLastDayOfTheYear(LocalDate date)
    {
        return date.withMonth(12).withDayOfMonth(31);
    }

}
