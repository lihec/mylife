package org.tools.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间常用方法
 * @author 李贺[of253]
 * @date 2014/6/30 21:03
 */
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date());
    }

    public static String getDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

}
