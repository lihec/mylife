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

    /**
     * 获取当前详细时间
     * @return
     */
    public static String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 按照指定格式格式化时间
     * @param date
     * @param format
     * @return
     */
    public static String getDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 按照指定格式转换字符串to时间
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

}
