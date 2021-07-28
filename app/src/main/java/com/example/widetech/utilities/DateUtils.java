package com.example.widetech.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {
    private DateUtils() {
    }

    public static String getCurrentDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault());

        return dateFormat.format(new Date());
    }

    public static String getCurrentDateTimeFormat() {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault());

        return dateFormat.format(new Date());
    }

    public static String getCurrentDateShortFormat() {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_SHORT, Locale.getDefault());

        return dateFormat.format(new Date());
    }

    public static boolean isToday(long timeInMilliseconds) {
        return android.text.format.DateUtils.isToday(timeInMilliseconds);
    }

    public static boolean isYesterday(long timeInMilliseconds) {
        return android.text.format.DateUtils.isToday(timeInMilliseconds
                + android.text.format.DateUtils.DAY_IN_MILLIS);
    }

    public static boolean isFuture(long timeInMilliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMilliseconds);

        return calendar.getTime().after(Calendar.getInstance().getTime());
    }
}
