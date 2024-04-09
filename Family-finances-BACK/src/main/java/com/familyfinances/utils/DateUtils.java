package com.familyfinances.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtils {
    public static String getDateNow() {
        return new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date());
    }

    public static String getYearMonthString(String date) throws ParseException {
        return new SimpleDateFormat("MMMM, yyyy")
                .format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
    }

    public static String getYearMonthStringReverce(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM")
                .format(new SimpleDateFormat("MMMM, yyyy").parse(date));
    }

    public static String getYearMonth(String date) throws ParseException {
        return new SimpleDateFormat("MM-yyyy")
                .format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
    }

    private static Date getDateForAge(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);

    }

    public static boolean isChild(String date) throws ParseException {
        Date birdth = DateUtils.getDateForAge(date);
        Date now = new Date();
        Long dif = now.getTime() - birdth.getTime();
        return dif > 60000L * 24 * 7 * 4 * 12 * 18;
    }
}
