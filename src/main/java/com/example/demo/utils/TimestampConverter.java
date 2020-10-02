package com.example.demo.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TimestampConverter {

    public static String getStringFromTimestamp(Timestamp timestamp) {
        long dayDiff = getDateDiff(timestamp, new Timestamp(System.currentTimeMillis()), TimeUnit.DAYS);
        if (dayDiff < 4) {
            long hourDiff = getDateDiff(timestamp, new Timestamp(System.currentTimeMillis()), TimeUnit.HOURS);
            if (hourDiff < 24) {
                long minuteDiff = getDateDiff(timestamp, new Timestamp(System.currentTimeMillis()), TimeUnit.MINUTES);
                if (minuteDiff < 60){
                    return minuteDiff + " minute" + (minuteDiff == 1 ? "" : "s") + " ago";
                }
                return hourDiff + " hour" + (hourDiff == 1 ? "" : "s") + " ago";
            }
            else return dayDiff + " day" + (dayDiff == 1 ? "" : "s") + " ago";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
    }

    public static long getDateDiff(Timestamp oldTs, Timestamp newTs, TimeUnit timeUnit) {
        long diffInMS = newTs.getTime() - oldTs.getTime();
        return timeUnit.convert(diffInMS, TimeUnit.MILLISECONDS);
    }
}
