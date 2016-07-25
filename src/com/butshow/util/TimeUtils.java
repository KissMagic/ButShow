package com.butshow.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 时间常量类
 * @author Administrator
 *
 */
public class TimeUtils {
	

	/**
	 * 取得系统时间
	 * @param pattern eg:yyyy-MM-dd HH:mm:ss,SSS
	 * @return
	 */
    public static String getSysTime(String pattern) {

        return formatSysTime(new SimpleDateFormat(pattern));
    }
    
    public static Timestamp getSysTimestamp() {
    	return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 格式化系统时间
     * @param format
     * @return
     */
    private static String formatSysTime(SimpleDateFormat format) {

        String str = format.format(Calendar.getInstance().getTime());
        return str;
    }

    public static String format(Date date, String pattern) {

    	SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        String str = format.format(date);

		return str;
    }

    public static Date addMonth(Date date, int month) {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
    }

    public static Date getYesterday() {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
    }

    public static Date getLastmonth() {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
    }

    public static Date getLastyear() {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
    }

    public static Date getDate(int days) {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
    }

    public static Date getDate(Date date, int days) {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
    }

    public static Date getDateBySecond(Date date, int second) {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
    }

    public static Date getHour(int hours) {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return cal.getTime();
    }

    public static boolean validTime(String str, String pattern) {

    	DateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);

		Date date = null;
		try {
			date = (Date) formatter.parse(str);
		} catch (ParseException e) {
			return false;
		}

		return str.equals(formatter.format(date));
	}

    public static Date format(String str, String pattern) throws Exception {

    	DateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);

		Date date = null;
		try {
			date = (Date) formatter.parse(str);
		} catch (ParseException e) {
			throw new Exception();
		}

		return date;
	}

    public static String getSysYear() {

        return getSysTime("yyyy");
    }

    public static String getSysTime() {

        return getSysTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getSysTimeS() {

        return getSysTime("yyyy-MM-dd HH:mm:ss,SSS");
    }

    public static String getSysTimeLong() {

        return getSysTime("yyyyMMddHHmmss");
    }

    public static String getSysTimeSLong() {

        return getSysTime("yyyyMMddHHmmssSSS");
    }

    public static String getSysdate() {

        return getSysTime("yyyy-MM-dd");
    }

    public static String getSysyearmonthInt() {

        return getSysTime("yyyyMM");
    }

    public static String getSysdateInt() {

        return getSysTime("yyyyMMdd");
    }

    public static String getSysdateTimeStart() {

        return getSysdate() + " 00:00:00";
    }

    public static String getSysdateTimeEnd() {

        return getSysdate() + " 23:59:59";
    }

    public static String getSysdateTimeEndLong() {

        return getSysdateInt() + "235959";
    }

    public static String getSysDateLocal() {

        return getSysTime("yyyy年MM月dd日");
    }

    public static String getTimeFormat(String str) throws Exception {

        return format(format(str, "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateFormat(String str) throws Exception {

        return format(format(str, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
    }

    public static String getDateFormatLocal(String str) throws Exception {

        return format(format(str, "yyyy-MM-dd HH:mm:ss"), "yyyy年MM月dd日");
    }

    public static String getYesterdayInt() {

        return format(getYesterday(), "yyyyMMdd");
    }

    public static String getYesterdayDate() {

        return format(getYesterday(), "yyyy-MM-dd");
    }

    private static int getMondayPlus() {

    	Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            return -6;
        } else {
        	return 2 - dayOfWeek;
        }
    }

    public static String getLastmondayInt() {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, getMondayPlus() - 7);

        return format(cal.getTime(), "yyyyMMdd");
    }

    public static String getLastmondayDate() {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, getMondayPlus() - 7);

        return format(cal.getTime(), "yyyy-MM-dd");
    }

    private static int getSundayPlus() {

    	Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            return -7;
        } else {
        	return 1 - dayOfWeek;
        }
    }

    public static String getLastsundayInt() {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, getSundayPlus());

        return format(cal.getTime(), "yyyyMMdd");
    }

    public static String getLastsundayDate() {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, getSundayPlus());

        return format(cal.getTime(), "yyyy-MM-dd");
    }

    public static String getLastmonthInt() {

        return format(getLastmonth(), "yyyyMM");
    }

    public static String getLastmonthDate() {

        return format(getLastmonth(), "yyyy-MM");
    }

    public static String getLastmonthenddayInt() {

        return format(getDate(getThismonthTime(1, 0, 0), -1), "yyyyMMdd");
    }

    public static String getLastmonthendDate() {

        return format(getDate(getThismonthTime(1, 0, 0), -1), "yyyy-MM-dd");
    }

    public static String getThismonthInt() {

        return format(getThismonthTime(1, 0, 0), "yyyyMM");
    }

    public static String getThismonthDate() {

        return format(getThismonthTime(1, 0, 0), "yyyy-MM");
    }

    public static String getMonthstarttime(String month) {

        return month + "01000000";
    }

    public static String getMonthendtime(String month) throws Exception {

    	Calendar cal = Calendar.getInstance();
		cal.setTime(format(month, "yyyyMM"));
		cal.add(Calendar.MONTH, 1);

        return format(getDate(cal.getTime(), -1), "yyyyMMdd") + "235959";
    }

    public static String getLastyearInt() {

        return format(getLastyear(), "yyyy");
    }

    public static String getDateInt(int days) {

        return format(getDate(days), "yyyyMMdd");
    }

    public static String getDateFormat(int days) {

        return format(getDate(days), "yyyy-MM-dd");
    }

    public static String getDateFormatLocal(int days) {

        return format(getDate(days), "yyyy年MM月dd日");
    }

    public static String getTimeFormatDays(int days) {

        return format(getDate(days), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimeFormatHours(int hours) {

        return format(getHour(hours), "yyyy-MM-dd HH:mm:ss");
    }

	public static Date getHourTime(int hour) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
//		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getHourTime(int hour, int minute) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getHourTime(int hour, int minute, int second) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getYesterdayHourTime(int hour) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getYesterdayHourTime(int hour, int minute) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getYesterdayHourTime(int hour, int minute, int second) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getTomorrowHourTime(int hour) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getTomorrowHourTime(int hour, int minute, int second) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getTomorrowHourTime(int hour, int minute) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getThismonthTime(int day, int hour, int minute) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getNextmonthTime(int day, int hour, int minute) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date time = calendar.getTime();

		return time;
	}
	
	public static String getSecondTime(String date,String pattern,int second) throws Exception {
		Date d = format(date, pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.SECOND, second);
		Date time = calendar.getTime();
		return format(time, pattern);
	}

	public static Date getMinute(int minute) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, minute);
		Date time = calendar.getTime();

		return time;
	}

	public static Date getSecond(int second) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, second);
		Date time = calendar.getTime();

		return time;
	}

	public static int countMonths(String end, String start, String pattern) throws Exception {

		Calendar calend = Calendar.getInstance();
		calend.setTime(format(end, pattern));

		Calendar calstart = Calendar.getInstance();
		calstart.setTime(format(start, pattern));

		return (calend.get(Calendar.YEAR) * 12 + calend.get(Calendar.MONTH)) 
				- (calstart.get(Calendar.YEAR) * 12 + calstart.get(Calendar.MONTH));
	}

	public static long subtract(String end, String start, String pattern) throws Exception {

	    return format(end, pattern).getTime() - format(start, pattern).getTime();
	}

	public static long subtractSSS(String start) throws Exception {

	    return subtract(getSysTimeS(), start, "yyyy-MM-dd HH:mm:ss,SSS");
	}

	public static long subtractSSS(String end, String start) throws Exception {

	    return subtract(end, start, "yyyy-MM-dd HH:mm:ss,SSS");
	}

	public static long subtractLong(String start) throws Exception {

	    return subtract(getSysTimeLong(), start, "yyyyMMddHHmmss");
	}

	public static long subtractLong(String end, String start) throws Exception {

	    return subtract(end, start, "yyyyMMddHHmmss");
	}


}
