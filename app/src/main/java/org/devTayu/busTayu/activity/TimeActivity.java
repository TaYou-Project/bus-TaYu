package org.devTayu.busTayu.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeActivity {

    long now;
    Date date;
    SimpleDateFormat simpleDateFormat;
    String formatDate;

    public String getTime() {
        now = System.currentTimeMillis();
        date = new Date(now);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a", new Locale("en", "KO"));
        formatDate = simpleDateFormat.format(date);

        return formatDate;
    }

    public String getTime2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk" + ":00");
        Date time = new Date();
        String currentTime = format.format(time);

        return currentTime;
    }

    public java.util.Date getTimeToDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        try {
            Date date = format.parse(String.valueOf(time));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
