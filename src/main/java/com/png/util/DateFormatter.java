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

    public static Timestamp getTimestampFromString(String dateString) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
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
            int elapsedDays = (int)Math.abs(Math.ceil(milliInDateDiff*1.0 /milliInDay));
            System.out.printf("%d Nights:  %n", elapsedDays);
            return elapsedDays;
    }
}
