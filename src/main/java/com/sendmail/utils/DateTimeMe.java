package com.sendmail.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeMe {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // get next date
    public static String getNextDate(String curDate) throws ParseException {
        Date date = formatter.parse(curDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 4); // get for 4 days after
        return formatter.format(calendar.getTime());
    }

    // Get current date
    public static String getCurrentDate() {
        Date date = new Date();
        return formatter.format(date);
    }
}
