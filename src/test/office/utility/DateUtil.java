package test.office.utility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhaolisong on 06/06/2017.
 */
public class DateUtil {

    public static final String TIME_HH_MM = "HH:mm:ss.SSS";
    public static final String DATE_YYYY_MM_DD_HH_MM_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_YYYYMMDDHHMMssSSS = "yyyyMMddHHmmssSSS";

    public static DateUtil newInstance() {
        return new DateUtil();
    }

    public long getHourMin(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_HH_MM);
        try {
            datetime += "00.000";
            Date time = sdf.parse(datetime);
            return time.getTime();
        } catch (Exception e) {
            return Long.MIN_VALUE;
        }
    }
    public Date getTime(String datetime, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date time = sdf.parse(datetime);
            return time;
        } catch (Exception e) {
            return null;
        }
    }

    public String timeToString(Date time, String pattern) {
        if (null==time) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.format(time);
        } catch (Exception e) {
            return "";
        }
    }

    public String timeToCn(Date date) {
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day   = cal.get(Calendar.DAY_OF_MONTH);
        return year + "年" + (month>9 ? "" : " ") + month + "月" +(day>9 ? "" : " ") + day + "日";
    }


}
