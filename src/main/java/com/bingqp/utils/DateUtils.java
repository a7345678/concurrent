/*
 * Copyright 2006 AsiaInfo Holdings, Inc
 * All right reserved.
 * Created on Aug 21, 2006
 */
package com.bingqp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author 奥萨
 */
public class DateUtils {

    public static String yyyyMMdd = "yyyyMMdd";

    public static String getOneHoursAgo() {
        String oneHoursAgoTime = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);  //时间设置为当前时间-1小时，同理，也可以设置其他时间
        oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());//获取到完整的时间
        return oneHoursAgoTime;
    }

    public static Date getYesterday() {
        String time = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static String getYesterdayStr() {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getYesterday());//获取到完整的时间
        return time;
    }

    public static String getOneDateString(int day) {
        String time = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());//获取到完整的时间
        return time;
    }

    public static String offsetMinuteATime(Integer minute) {
        String time = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minute);  //时间设置为当前时间-1小时，同理，也可以设置其他时间
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());//获取到完整的时间
        return time;
    }

    public static String getOneHoursAgoTime() {
        String oneHoursAgoTime = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);  //时间设置为当前时间-1小时，同理，也可以设置其他时间
        oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());//获取到完整的时间
        return oneHoursAgoTime;
    }

    public static String test() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天：" + day);

        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月：" + mon);

        //过去三个月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        Date m3 = c.getTime();
        String mon3 = format.format(m3);
        System.out.println("过去三个月：" + mon3);

        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        System.out.println("过去一年：" + year);

        return null;
    }

    /**
     * 获取yyyyMMddHHmmss格式的时间
     *
     * @return String
     */
    public static String getDateString() {
        DateFormat dfmt = new SimpleDateFormat("yyyyMMddHHmmss");
        return dfmt.format(new Date());
    }

    public static String getYYYYMMDDHH24MISS(String strYYYY_MM_DD) {
        String tmp = "";
        try {
            Date d = getDate(strYYYY_MM_DD, "yyyy-mm-dd");
            DateFormat dfmt = new SimpleDateFormat("yyyyMMddHHmmss");
            return dfmt.format(d);
        } catch (ParseException e) {
        }
        return tmp;
    }

    /**
     * 传入一个format pattern，得到当前时间的字符串
     *
     * @return String  eg: yyyyMMddHHmmss
     */
    public static String getDateString(String pattern) {
        DateFormat dfmt = new SimpleDateFormat(pattern);
        return dfmt.format(new Date());
    }

    public static String getDateString(int days, String pattern) {
        DateFormat dfmt = new SimpleDateFormat(pattern);
        long days2 = (long) days;
        return dfmt.format(new Date((new Date()).getTime() + 1000 * 60 * 60 * 24 * days2));
    }

    /**
     * 传入一个时间，获取一个yyyy-MM-dd HH:mm:ss字符串
     *
     * @param date Date
     * @return String
     */
    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(vdfmt).format(date);
    }

    /**
     * 获取一个时间字符串
     */
    public static String vdfmt = "yyyy-MM-dd HH:mm:ss";
    public static String yyyyMMddHHmmssPattern = "yyyyMMddHHmmss";

    public static String getDateString(Date date, String pattern) {
        SimpleDateFormat sdfmt = new SimpleDateFormat(pattern);
        return date != null ? sdfmt.format(date) : "";
    }

    public static Date getDate(String strDate) throws ParseException {
        DateFormat dfmt = new SimpleDateFormat(yyyyMMddHHmmssPattern);
        return dfmt.parse(strDate);
    }

    public static Date getDate(String strDate, String pattern) throws ParseException {
        DateFormat dfmt = new SimpleDateFormat(pattern);
        return dfmt.parse(strDate);
    }

    /**
     * 20061111163558 2006-11-11 16:35:58
     *
     * @param stringdate String
     * @return Date
     * @throws ParseException
     */
    public static String getStringDate(String stringdate) {
        if (stringdate == null) return null;

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "";
        try {
            Date date = formatter1.parse(stringdate);
            dateString = formatter2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static String getStringDate(String stringdate, String fpattern, String tpattern) {
        if (stringdate == null) return null;

        SimpleDateFormat formatter1 = new SimpleDateFormat(fpattern);
        SimpleDateFormat formatter2 = new SimpleDateFormat(tpattern);
        String dateString = "";
        try {
            Date date = formatter1.parse(stringdate.trim());
            dateString = formatter2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static String getChdate(int month_num) {
        Calendar c1 = Calendar.getInstance();
        String result = "";
        c1.add(2, month_num);
        result = String.valueOf(c1.get(1));
        if ((c1.get(2) + 1) >= 10) {
            result = result + String.valueOf(c1.get(2) + 1);
        } else {
            result = result + "0" + String.valueOf(c1.get(2) + 1);
        }
        return result;
    }

    public static int getSysYear() {
        Calendar calendar = new GregorianCalendar();
        int iyear = calendar.get(Calendar.YEAR);
        return iyear;
    }

    public static int getSysMonth() {
        Calendar calendar = new GregorianCalendar();
        int imonth = calendar.get(Calendar.MONTH) + 1;
        return imonth;
    }

    public static int getSysDay() {
        Calendar calendar = new GregorianCalendar();
        int idate = calendar.get(Calendar.DAY_OF_MONTH);
        return idate;
    }

    public static String getDateString2() {
        String tmp = "";
        tmp = getSysYear() + "    " + getSysMonth() + "    " + getSysDay() + "    ";
        return tmp;
    }

    public static int getTwoMonthNum(String startDate, String endDate) {
        int year1 = Integer.parseInt(startDate.substring(0, 4));
        int year2 = Integer.parseInt(endDate.substring(0, 4));
        int month1 = Integer.parseInt(startDate.substring(4));
        int month2 = Integer.parseInt(endDate.substring(4));
        return Math.abs(year1 - year2) * 12 - (month1 - month2) + 1;
    }

    public static String getNextMonth(String startDate, int i) {
        int start = Integer.parseInt(startDate);
        int next = start + i;
        int year = Integer.parseInt(String.valueOf(next).substring(0, 4));
        int month = Integer.parseInt(String.valueOf(next).substring(4));
        if (month > 12) {
            year = year + 1;
            month = month - 12;
        }
        if (month < 10) {
            return year + "0" + month;
        } else {
            return year + "" + month;
        }
    }

    public static int getDays(String yearMonth) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4)) - 1;
        if (month == 1) {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    return 28;
                } else {
                    return 29;
                }
            } else {
                return 28;
            }
        } else {
            return days[month];
        }
    }


    public static int isBetweenDays(String startDay, String endDay) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String today = formatter.format(date);
        startDay = today.substring(0, 6) + startDay;
        endDay = today.substring(0, 6) + endDay;
        if (today.compareTo(startDay) >= 0 && today.compareTo(endDay) <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isDate(String dateStr, String dateFomrat) {
        //"yyyy-mm-dd","yyyyMMddHHmmss"
        boolean tmp = false;
        try {
            Date d = getDate(dateStr, dateFomrat);
            DateFormat dfmt = new SimpleDateFormat(dateFomrat);
            dfmt.format(d);
            tmp = true;
        } catch (ParseException e) {
            tmp = false;
        }
        return tmp;
    }

    public static boolean isBetweenDays(String startDay, String endDay, String dateFomrat) {
        boolean tmp = false;

        if (isDate(startDay, dateFomrat) && isDate(endDay, dateFomrat)) {
            try {
                if (getDate(startDay, dateFomrat).getTime() > (getDate(endDay, dateFomrat)).getTime()) {
                    tmp = false;
                } else
                    tmp = true;
            } catch (ParseException e) {
                ;
            }
        }


        return tmp;
    }

    public static String getYyyyMm(String theDayYyyy_mm_dd) {
        String dayYYYYMMDD = "";
        dayYYYYMMDD = StringUtils.replace(theDayYyyy_mm_dd, "-", "");
        return dayYYYYMMDD.substring(0, 6);
    }

    public static boolean isDateStr(String strDate, String pattern) {
        boolean tmp = true;

        try {
            getDate(strDate, pattern);
        } catch (ParseException e) {
            tmp = false;
        }

        return tmp;
    }

    public static Date getAfterNDaysDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);

        return cal.getTime();
    }

    /**
     * 从年初到现在计算出来的时间戳
     *
     * @return
     */
    public static long getTimestampBetweenCurrentYearStart() {
        return DateUtils.timestampBetweenTwoDate(String.format("%s-01-01 00:00:00", DateUtils.getCurrentYear()), DateUtils.getDateString(new Date()));
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static Integer getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static long hoursBetweenTwoDate(String firstString, String secondString) {
        long nDay = timestampBetweenTwoDate(firstString, secondString) / (60 * 60 * 1000);
        return nDay;
    }

    public static long timestampBetweenTwoDate(String firstString, String secondString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = df.parse(firstString);
            secondDate = df.parse(secondString);
        } catch (Exception e) {

        }
        return secondDate.getTime() - firstDate.getTime();
    }

    /**
     * 得到当前月的后month_num个月的帐期 例如当前为2009-05-16，返回上个月的帐期，则设置month_num为-1,返回为2009-04-01
     * 例如当前为2009-05-16，返回下个月的帐期，则设置month_num为1,返回为2009-06-01
     *
     * @param month_num
     * @return
     */
    public static String getCurMonthFirstDay(int month_num) {
        Calendar c1 = Calendar.getInstance();
        String result = "";
        c1.add(2, month_num);
        result = String.valueOf(c1.get(1));
        if ((c1.get(2) + 1) >= 10) {
            result = result + "-" + String.valueOf(c1.get(2) + 1);
        } else {
            result = result + "-0" + String.valueOf(c1.get(2) + 1);
        }
        result += "-01";

        return result;
    }

    /**
     * 判断时间是否在时间段内 00:00:00至00:05:00之间*
     *
     * @param date         当前时间 yyyy-MM-dd HH:mm:ss
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd   结束时间 00:05:00
     * @return
     */

    public static boolean isInDate(Date date, String strDateBegin,

                                   String strDateEnd) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String strDate = sdf.format(date);

        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));

        // 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));

        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));

        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {

            // 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {

                return true;

                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间

            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM

                    && strDateM <= strDateEndM) {

                return true;

                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间

            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM

                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {

                return true;

            }

            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数

            else if (strDateH >= strDateBeginH && strDateH == strDateEndH

                    && strDateM <= strDateEndM) {

                return true;

                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数

            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH

                    && strDateM == strDateEndM && strDateS <= strDateEndS) {

                return true;

            } else {

                return false;

            }

        } else {

            return false;

        }
    }

    /**
     * 时间比较器
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compareDate(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1比dt2大");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1大dt2早");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static void main(String[] args) {
        DateUtils.isInDate(new Date(), "18:00:00", "20:00:00");

        String timestamp = "1508388247943";
        String dateStr = DateUtils.stampToDate(timestamp);

        String now = DateUtils.getDateString(new Date());
        int i = DateUtils.compareDate(now, "2018-1-26 08:00:00");
        System.out.println(i);

        testHourBetweenTwoDate();
    }

    public static void testHourBetweenTwoDate() {
        long days = hoursBetweenTwoDate("2018-1-29 08:00:00", "2018-1-30 08:00:00");
        System.out.println(days);
    }

    public static long hoursBetweenTwoDate(Date last, Date now) {
        long nDay = ((now.getTime() - last.getTime()) / (60 * 60 * 1000));
        return nDay;
    }

    public static String getOneDateString(int day, String pattern) {
        String time = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        time = new SimpleDateFormat(pattern).format(cal.getTime());
        return time;
    }

    public static String getHourString(int hour, String pattern) {
        String time = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, hour);
        time = new SimpleDateFormat(pattern).format(cal.getTime());
        return time;
    }
}
