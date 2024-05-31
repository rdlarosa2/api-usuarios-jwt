package com.rdelarosa.jwt.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CoDate  {
    public static void newDate(Date theDate) {
        /*
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        long millis = theDate.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        */
        System.out.println("date >>" + theDate.toString() + "<<");
    }
}
