package com.png.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DateFormatter {

    public static Timestamp getCurrentTime() {
        return new Timestamp(new java.util.Date().getTime());
    }

    public static String getCurrentTimeString() {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        return df.format(new java.util.Date().getTime());
    }

    public static Timestamp getTimestampFromString(String dateString) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        return new Timestamp(df.parse(dateString).getTime());
    }

    public static Timestamp getTimestampFromString(String dateString, String parseFormat) throws ParseException {
        DateFormat df = new SimpleDateFormat(parseFormat);
        return new Timestamp(df.parse(dateString).getTime());
    }
    public static String getDateStringFromTimestamp(Timestamp timestamp){
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        return df.format(timestamp);
    }

    public static Integer getNights(Timestamp startDate, Timestamp endDate){

        //milliseconds
        long milliInDateDiff = Math.abs(endDate.getTime() - startDate.getTime());

        long milliInSec = 1000;
        long milliInMin = milliInSec * 60;
        long milliInHour = milliInMin * 60;
        long milliInDay = milliInHour * 24;
        int elapsedDays = (int) Math.ceil(milliInDateDiff * 1.0 / milliInDay);
        System.out.printf("%d Nights:  %n", elapsedDays);
        return elapsedDays;
    }

    public static Integer getDiffInDays(Timestamp startDate, Timestamp endDate) {

        //milliseconds
        long milliInDateDiff = endDate.getTime() - startDate.getTime();

        long milliInSec = 1000;
        long milliInMin = milliInSec * 60;
        long milliInHour = milliInMin * 60;
        long milliInDay = milliInHour * 24;
        long dayDiff = milliInDateDiff / milliInDay;
        int elapsedDays;
        if (dayDiff < 0)
            elapsedDays = (int) Math.ceil(dayDiff);
        else
            elapsedDays = (int) Math.floor(dayDiff);
        System.out.printf("Diff in Days:  %d", elapsedDays);
        return elapsedDays;
    }
}
