package cn.raven.financial.yellowcow.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Desription:
 * @Author: Raven wu
 * @Date: Created in 17:32 2017/12/1
 */
public class DateUtil {

    /*
     * ========================================================================== ==
     */
    /* 定义时间常量，毫秒为单位。 */
    /*
     * ========================================================================== ==
     */
    /**
     * 一秒
     */
    public static final long SECOND = 1000;

    /**
     * 一分钟
     */
    public static final long MINUTE = SECOND * 60;

    /**
     * 一小时
     */
    public static final long HOUR = MINUTE * 60;

    /**
     * 一天
     */
    public static final long DAY = 24 * HOUR;

    /**
     * 一天的起始时间
     */
    public static final String TIME_BEGIN = " 00:00:00";

    /**
     * 一天的结束时间
     */
    public static final String TIME_END = " 23:59:59";

    /*
     * ========================================================================== ==
     */
    /* 定义日期格式。 */
    /*
     * ========================================================================== ==
     */

    /**
     * 年月 <code>yyyy-MM</code>
     */
    public static final String MONTH_PATTERN = "yyyy-MM";

    /**
     * 年月日 <code>yyyyMMdd</code>
     */
    public static final String DEFAULT_PATTERN = "yyyyMMdd";

    /**
     * 年月日 <code>yyyyMMdd</code>
     */
    public static final String DOT_PATTERN = "yyyy.MM.dd";

    /**
     * 年月日时分秒 <code>yyyyMMddHHmmss</code>
     */
    public static final String FULL_PATTERN = "yyyyMMddHHmmss";

    /**
     * 标准格式的年月日时分秒 <code>yyyyMMdd HH:mm:ss</code>
     */
    public static final String FULL_STANDARD_PATTERN = "yyyyMMdd HH:mm:ss";

    /**
     * 中文格式的年月日格式 <code>yyyy-MM-dd</code>
     */
    public static final String CHINESE_PATTERN = "yyyy-MM-dd";

    /**
     * 中文格式的年月日时分秒格式 <code>yyyy-MM-dd HH:mm:ss</code>
     */
    public static final String FULL_CHINESE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // ==========================================================================
    // 年月日小时等。
    // ==========================================================================

    /**
     * 获取当前日期
     *
     * @return 当前日期 @see Calendar
     */
    public static Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期 @see Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date getCurrentDatetime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * truncate截断日期(只支持截断小时，分，秒，毫秒）
     *
     * @param date  待截断的日期
     * @param index Calendar.HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND
     * @return
     */
    public static Date truncateDate(Date date, int index) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(date.getTime());
        if (index <= Calendar.MILLISECOND) {
            calendar.set(Calendar.MILLISECOND, 0);
        }
        if (index <= Calendar.SECOND) {
            calendar.set(Calendar.SECOND, 0);
        }
        if (index <= Calendar.MINUTE) {
            calendar.set(Calendar.MINUTE, 0);
        }
        if (index <= Calendar.HOUR_OF_DAY) {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
        }
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 返回去除时分秒的日期对象
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 返回<code>yyyy-MM-dd</code>默认格式的当前日期
     *
     * @return
     */
    public static String getDefaultCurrentDate() {
        return getCurrentDate(CHINESE_PATTERN);
    }

    /**
     * 获取一定格式的当前日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        return formatDate(getCurrentDate(), format);
    }

    /**
     * 获取<code>yyyy-MM-dd HH:mm:ss</code>默认格式的当前时间戳
     *
     * @return
     */
    public static String getDefaultCurrentTimestamp() {
        return formatDate(getCurrentTimestamp(), FULL_CHINESE_PATTERN);
    }

    /**
     * 获取一定格式的当前时间戳
     *
     * @return
     */
    public static String getCurrentTimestamp(String format) {
        return formatDate(getCurrentTimestamp(), format);
    }

    /**
     * 返回当前月份
     *
     * @return [yyyy]
     */

    public static String getYear() {
        return formatDate("yyyy");
    }

    /**
     * 返回当前月份
     *
     * @return [MM]
     */

    public static String getMonth() {
        return formatDate("MM");
    }

    /**
     * 返回当前日
     *
     * @return [dd]
     */

    public static String getDay() {
        return formatDate("dd");
    }

    /**
     * 根据日期获取年份
     *
     * @param date 日期 @see Date
     * @return 年份，如果<code>date</code>为<code>null</code>,返回<code>-1</code>
     */
    public static final int getYear(Date date) {
        if (date == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 根据毫秒数获取年份 @see Calendar.YEAR
     *
     * @param millis 毫秒
     * @return 年份
     */
    public static final int getYear(long millis) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 根据日期获取月份 @see Calendar.MONTH
     *
     * @param date 日期 @see Date
     * @return 月份，如果<code>date</code>为<code>null</code>,返回<code>-1</code>
     */
    public static final int getMonth(Date date) {
        if (date == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据毫秒数获取月份 @see Calendar.MONTH
     *
     * @param millis 毫秒
     * @return 月份
     */
    public static final int getMonth(long millis) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据时间获取日 @see Calendar.DAY_OF_MONTH
     *
     * @param date 日期 @see Date
     * @return 年月日中的日，如果<code>date</code>为<code>null</code>,返回<code>-1</code>
     */
    public static final int getDay(Date date) {
        if (date == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 根据毫秒数获取日 @see Calendar.DAY_OF_MONTH
     *
     * @param millis 毫秒
     * @return 年月日中的日
     */
    public static final int getDay(long millis) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 根据日期获取小时 @see Calendar.HOUR_OF_DAY
     *
     * @param date 日期 @see Date
     * @return 小时，如果<code>date</code>为<code>null</code>,返回<code>-1</code>
     */
    public static final int getHour(Date date) {
        if (date == null) {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 根据毫秒数获取小时 @see Calendar.HOUR_OF_DAY
     *
     * @param millis 毫秒
     * @return 小时
     */
    public static final int getHour(long millis) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    // ==========================================================================
    // 常用中文格式。
    // ==========================================================================

    /**
     * 返回中文格式的当前日期
     *
     * @return [yyyy-MM-dd]
     */
    public static String getChinesePatternNow() {
        return formatDate(CHINESE_PATTERN);
    }

    /**
     * 返回中文格式的当前日期
     *
     * @return [yyyy-MM-dd HH:mm:ss]
     */
    public static String getFullChinesePatternNow() {
        return formatDate(FULL_CHINESE_PATTERN);
    }

    /**
     * 返回中文格式的当前日期
     *
     * @param date 日期 @see Date
     * @return [yyyy-MM-dd]
     */
    public static String getChinesePatternNow(Date date) {
        return formatDate(date, CHINESE_PATTERN);
    }

    /**
     * 返回中文格式的当前日期
     *
     * @param date 日期 @see Date
     * @return [yyyy-MM-dd HH:mm:ss]
     */
    public static String getFullCNPatternNow(Date date) {
        return formatDate(date, FULL_CHINESE_PATTERN);
    }

    // ==========================================================================
    // Date to String。
    // ==========================================================================

    /**
     * 将日期转换为 <code>yyyy-MM-dd</code> 的字符串格式
     *
     * @param date 日期 @see Date
     * @return 格式化后的日期字符串
     */
    public static String formatDate(final Date date) {
        return formatDate(date, CHINESE_PATTERN);
    }

    /**
     * 将日期转换为指定的字符串格式
     *
     * @param date   日期 @see Date
     * @param format 日期格式
     * @return 格式化后的日期字符串，如果<code>date</code>为<code>null</code>或者 <code>format</code>为空，则返回<code>null</code>。
     */
    public static String formatDate(final Date date, String format) {
        if (null == date || StringUtils.isBlank(format)) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将当前日期转换为指定的字符串格式
     *
     * @param format 日期格式
     * @return 格式化后的日期字符串
     */
    public static String formatDate(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * 将long类型毫秒时间戳转换为yyyy-MM-dd HH:mm:ss的字符串格式
     *
     * @param millis 毫秒时间戳
     * @return 格式化后的日期字符串
     */
    public static String formatDate(long millis) {
        Calendar calendar = Calendar.getInstance();


        calendar.setTimeInMillis(millis);
        return formatDate(calendar.getTime(), FULL_CHINESE_PATTERN);
    }

    /**
     * 将long类型时间戳转换为指定的字符串格式
     *
     * @param millis 毫秒时间戳
     * @param format 转换成的日期格式
     * @return 格式化后的日期字符串
     */
    public static String formatDate(long millis, String format) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(millis);
        return formatDate(calendar.getTime(), format);
    }

    // ==========================================================================
    // String to Date。
    // ==========================================================================

    /**
     * 将<code>yyyy-MM-dd<code>格式的字符串转变为日期对象
     *
     * @param sDate 日期字符串
     * @return 日期
     */
    public static Date parseDate(String sDate) {
        return parseDate(sDate, CHINESE_PATTERN, null);
    }

    /**
     * 将字符串转换撑日期对象
     *
     * @param sDate  日期字符串
     * @param format 日期格式 @see DateFormat
     * @return 日期对象 @see Date
     */
    public static Date parseDate(String sDate, String format) {
        return parseDate(sDate, format, null);
    }

    /**
     * 将字符串转换成日期对象
     *
     * @param sDate        日期字符串
     * @param format       日期格式 @see DateFormat
     * @param defaultValue 默认值
     * @return 日期对象，如果格式化失败则返回默认值<code>defaultValue</code>
     */
    public static Date parseDate(String sDate, String format, Date defaultValue) {
        if (StringUtils.isBlank(sDate) || StringUtils.isBlank(format)) {
            return defaultValue;
        }

        DateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(sDate);
        } catch (ParseException e) {
            return defaultValue;
        }

    }

    // ==========================================================================
    // 增减年月日。
    // ==========================================================================

    /**
     * 给指定日期增加月份数
     *
     * @param date   指定日期 @see Date
     * @param months 增加的月份数
     * @return 增加月份后的日期
     */
    public static Date addMonths(Date date, int months) {
        if (months == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 给指定日期增加天数
     *
     * @param date 指定日期 @see Date
     * @param days 增加的天数
     * @return 增加天数后的日期
     */
    public static Date addDays(final Date date, int days) {
        if (days == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);

        return cal.getTime();
    }

    /**
     * 给指定日期增加分钟
     *
     * @param date 指定日期 @see Date
     * @param mins 增加的分钟
     * @return 增加分钟后的日期
     */
    public static Date addMins(final Date date, int mins) {
        if (mins == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);

        return cal.getTime();
    }

    /**
     * 给指定日期增加秒
     *
     * @param date    指定日期 @see Date
     * @param seconds 增加的秒
     * @return 增加分钟后的日期
     */
    public static Date addSeconds(final Date date, int seconds) {
        if (seconds == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);

        return cal.getTime();
    }

    // ==========================================================================
    // 日期比较。
    // ==========================================================================

    /**
     * 比较传入的日期是否大于当前时间
     *
     * @param first
     * @return
     */
    public static boolean greaterThanNow(Date first) {
        return compareDate(first, new Timestamp(System.currentTimeMillis())) == 1;
    }

    /**
     * 比较两个日期的先后顺序
     *
     * @param first  第一个日期 @see Date
     * @param second 第二个日期 @see Date
     * @return 如果<code>first</code>==<code>second</code>，返回<code>0</code>;
     * <p>
     * 如果<code>first</code>&lt;<code>second</code>，返回<code>-1</code>;
     * <p>
     * 如果<code>first</code>&gt;<code>second</code>，返回<code>1</code>
     */
    public static int compareDate(Date first, Date second) {
        if ((first == null) && (second == null)) {
            return 0;
        }

        if (first == null) {
            return -1;
        }

        if (second == null) {
            return 1;
        }

        if (first.getTime() < second.getTime()) return -1;
        if (first.getTime() > second.getTime()) return 1;
        return 0;
    }

    /**
     * 判断两个日期对象是否具有相同的月份
     *
     * @param date1 第一个日期对象 @see Date
     * @param date2 第二个日期对象 @see Date
     * @return 如果两个日期对象具有相同的月份，返回 <code>true</code>
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }

        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(date2);
        return isSameMonth(cal1, cal2);
    }

    /**
     * 判断两个日期对象是否具有相同的日
     *
     * @param date1 第一个日期对象 @see Date
     * @param date2 第二个日期对象 @see Date
     * @return 如果两个日期对象具有相同的日，返回 <code>true</code>
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }

        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(date2);
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && (cal1.get(Calendar.DATE) == cal2
                .get(Calendar.DATE)));
    }

    /**
     * 判断两个日历对象是否具有相同的月份
     *
     * @param cal1 第一个日期对象 @see Calendar
     * @param cal2 第二个日期对象 @see Calendar
     * @return 如果两个日历对象具有相同的月份，返回 <code>true</code>
     */
    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        if (cal1 == null && cal2 == null) {
            return true;
        }

        if (cal1 == null || cal2 == null) {
            return false;
        }

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH));
    }

    // ==========================================================================
    // 常见特殊时间点。
    // ==========================================================================

    /**
     * 返回零点的日期对象
     *
     * @param date 日期 @see Date
     * @return 零点的日期对象 ，如果<code>date</code>为<code>null</code>，返回 <code>null</code>
     */
    public static Date getStartOfDate(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 返回 日期当中的最后时间
     *
     * @param date
     * @return
     */
    public static Date getEndOfDate(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取上周的星期一
     *
     * @return 上周的星期一
     */
    public static Date getPreviousMonday() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        Date date;
        if (dayOfWeek == 1) {
            date = addDays(cd.getTime(), -7);
        } else {
            date = addDays(cd.getTime(), -6 - dayOfWeek);
        }
        return getStartOfDate(date);
    }

    /**
     * 获取一个月之前的星期一
     *
     * @return 一个月之前的星期一
     */
    public static Date getMondayBefore4Week() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        Date date;
        if (dayOfWeek == 1) {
            date = addDays(cd.getTime(), -28);
        } else {
            date = addDays(cd.getTime(), -27 - dayOfWeek);
        }
        return getStartOfDate(date);
    }

    /**
     * 获取本周的星期一
     *
     * @return the day of current monday
     */
    public static Date getCurrentMonday() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        Date date;
        if (dayOfWeek == 1) {
            date = cd.getTime();
        } else {
            date = addDays(cd.getTime(), 1 - dayOfWeek);
        }
        return getStartOfDate(date);
    }

    /**
     * 返回给定日期时间所在月份的最后一天
     *
     * @param date 给定的日期对象 @see Date
     * @return 给定日期时间所在月份的最后一天
     */
    public static Date getEndOfMonth(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DATE, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 返回给定日期时间所在月份的第一天
     *
     * @param date 给定的日期对象 @see Date
     * @return 给定日期时间所在月份的第一天
     */
    public static Date getFirstOfMonth(final Date date) {
        Date lastMonth = addMonths(date, -1);

        lastMonth = getEndOfMonth(lastMonth);
        return addDays(lastMonth, 1);
    }

    public static Date getWeekEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        int dw = cal.get(Calendar.DAY_OF_WEEK);
        while (dw != Calendar.SUNDAY) {
            cal.add(Calendar.DATE, 1);
            dw = cal.get(Calendar.DAY_OF_WEEK);
        }
        return cal.getTime();
    }

    /**
     * 检查日期的合法性
     *
     * @param sourceDate 给定日期 @see Date
     * @param format     日期格式
     * @return 如果合法返回<code>true</code>，如果<code>sourceDate</code>为 <code>null</code>或者<code>format</code>为空，返回
     * <code>false</code>
     */
    public static boolean inFormat(String sourceDate, String format) {
        if (sourceDate == null || StringUtils.isBlank(format)) {
            return false;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            dateFormat.parse(sourceDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ==========================================================================
    // 时间间隔。
    // ==========================================================================

    /**
     * 返回两个时间间隔的秒数
     *
     * @param d1 起始时间
     * @param d2 终止时间
     * @return the number of seconds interval,if either <code>d1</code> or <code>d2</code> is zero,return
     * <code>-1</code>
     */
    public static int getNumberOfSecondsBetween(final double d1, final double d2) {
        if ((d1 == 0) || (d2 == 0)) {
            return -1;
        }

        return (int) (Math.abs(d1 - d2) / SECOND);
    }

    /**
     * 返回两个时间间隔的月数
     *
     * @param begin 起始时间 @see Date
     * @param end   终止时间 @see Date
     * @return 间隔的月份数，如果<code>begin</code>或者<code>end</code>为<code>null</code> ，返回 <code>-1</code>
     */
    public static int getNumberOfMonthsBetween(final Date begin, final Date end) {
        if (begin == null || end == null) {
            return -1;
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(begin);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        return (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12
                + (cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH));
    }

    /**
     * 返回两个时间间隔的分钟数
     *
     * @param begin 起始时间
     * @param end   终止时间
     * @return 间隔的分钟数，如果<code>begin</code>或者<code>end</code>为<code>null</code> ，返回 <code>-1</code>
     */
    public static long getNumberOfMinuteBetween(final Date begin, final Date end) {
        if (begin == null || end == null) {
            return -1;
        }

        long millisec = end.getTime() - begin.getTime();
        return millisec / (60 * 1000);
    }

    /**
     * 返回两个时间间隔的小时数
     *
     * @param begin 起始时间
     * @param end   终止时间
     * @return 间隔的小时数，如果<code>begin</code>或者<code>end</code>为<code>null</code> ，返回 <code>-1</code>
     */
    public static long getNumberOfHoursBetween(final Date begin, final Date end) {
        if (begin == null || end == null) {
            return -1;
        }

        long millisec = end.getTime() - begin.getTime() + 1;
        return millisec / (60 * 60 * 1000);
    }

    /**
     * 计算2个日历之间相差的天数
     *
     * @param before 前一个日历 @see Calendar
     * @param after  后一个日历 @see Calendar
     * @return 相差的天数，如果<code>before</code>或者<code>after</code>为<code>null</code> ，返回 <code>-1</code>
     */
    public static long getNumberOfDaysBetween(Calendar before, Calendar after) {
        if (before == null || after == null) {
            return -1;
        }

        before.clear(Calendar.MILLISECOND);
        before.clear(Calendar.SECOND);
        before.clear(Calendar.MINUTE);
        before.clear(Calendar.HOUR_OF_DAY);

        after.clear(Calendar.MILLISECOND);
        after.clear(Calendar.SECOND);
        after.clear(Calendar.MINUTE);
        after.clear(Calendar.HOUR_OF_DAY);

        long elapsed = after.getTime().getTime() - before.getTime().getTime();
        return elapsed / DAY;
    }

    // ==========================================================================
    // 远程。
    // ==========================================================================

    /**
     * 获取远程服务器日期
     *
     * @param url 链接地址
     *            <p>
     *            <p>
     *            http://192.9.162.55
     *            <p>
     *            http://java.sun.com
     *            </p>
     * @return 远程服务器日期，如果反生异常，返回当前日期
     */
    public static Date getRemoteDate(String url) {
        if (url == null) {
            return null;
        }

        URLConnection uc;
        try {
            uc = new URL(url).openConnection();
            uc.connect(); // 发出连接
            return new Date(uc.getDate());// 生成连接对象
        } catch (IOException e) {
            return new Date();
        }

    }

    // ==========================================================================
    // from beidou。
    // ==========================================================================

    /**
     * 获取今日0时
     */
    public static Calendar getCurDateCeil() {
        Calendar cal = new GregorianCalendar();
        return new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取今日23时
     */
    public static Calendar getCurDateFloor() {
        Calendar cal = new GregorianCalendar();
        return new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                23, 0);
    }

    public static Date getWeekBegin(Calendar tmp) {
        if (tmp == null) {
            return null;
        }

        Calendar ctmp =
                new GregorianCalendar(tmp.get(Calendar.YEAR), tmp.get(Calendar.MONTH), tmp.get(Calendar.DAY_OF_MONTH));

        int dw = ctmp.get(Calendar.DAY_OF_WEEK);
        while (dw != Calendar.MONDAY) {
            ctmp.add(Calendar.DATE, -1);
            dw = ctmp.get(Calendar.DAY_OF_WEEK);
        }
        return ctmp.getTime();
    }

    public static Date getWeekEnd(Calendar tmp) {
        if (tmp == null) {
            return null;
        }

        Calendar ctmp =
                new GregorianCalendar(tmp.get(Calendar.YEAR), tmp.get(Calendar.MONTH), tmp.get(Calendar.DAY_OF_MONTH),
                        23, 0);

        int dw = ctmp.get(Calendar.DAY_OF_WEEK);
        while (dw != Calendar.SUNDAY) {
            ctmp.add(Calendar.DATE, 1);
            dw = ctmp.get(Calendar.DAY_OF_WEEK);
        }
        return ctmp.getTime();
    }

    public static Date getQuarterEnd(Calendar tmp) {
        if (tmp == null) {
            return null;
        }

        Calendar ctmp =
                new GregorianCalendar(tmp.get(Calendar.YEAR), tmp.get(Calendar.MONTH), tmp.get(Calendar.DAY_OF_MONTH),
                        23, 0);

        int month = ctmp.get(Calendar.MONTH);
        int offset = 2 - (month % 3);
        ctmp.add(Calendar.MONTH, offset);

        return getEndOfMonth(ctmp.getTime());
    }

    public static Date getQuarterBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);
        int offset = -(month % 3); // TODO: 这里有问题，month要+1再mod
        cal.add(Calendar.MONTH, offset);

        return getFirstOfMonth(cal.getTime());
    }

    public static Date getQuarterEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);
        int offset = 2 - (month % 3);
        cal.add(Calendar.MONTH, offset);

        return getEndOfMonth(cal.getTime());
    }

    public static Date getYearBegin(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);

        return cal.getTime();
    }

    public static Date getYearEnd(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);

        return getEndOfMonth(cal.getTime());
    }

    /**
     * 计算两个日期之间相差的天数
     * bdate - smdate 参数2 减 参数1 得到相差天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(CHINESE_PATTERN);
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

}
