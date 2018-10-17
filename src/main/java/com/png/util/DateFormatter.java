package com.png.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DateFormatter {

    public Timestamp getTimestampFromString(String dateString) throws ParseException{
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        return new Timestamp(df.parse(dateString).getTime());
    }
    public String getDateStringFromTimestamp(Timestamp timestamp){
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        return df.format(timestamp);
    }
}
