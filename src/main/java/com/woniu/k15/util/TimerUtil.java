package com.woniu.k15.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerUtil {
    public TimerUtil() {
    }

    public String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        String strTime = simpleDateFormat.format(date);
        return strTime;
    }

    public Date stringToDate(String strTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        Date date = null;
        try {
            Date parse = simpleDateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String uuidTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMdHHmmss");
        String strTime = simpleDateFormat.format(date);
        return strTime;
    }
}
