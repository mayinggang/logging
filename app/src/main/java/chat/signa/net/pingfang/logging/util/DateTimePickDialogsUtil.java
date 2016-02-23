package chat.signa.net.pingfang.logging.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import chat.signa.net.pingfang.logging.R;

/**
 * Created by Administrator on 2016/1/8.
 */
public class DateTimePickDialogsUtil implements DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener {


    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;
    private String dateTime;
    private String initDateTime;
    private Activity activity;
    String date,time,yearStr,monthAndDay,monthStr,dayStr,hourStr,minuteStr;


    /**
     * 鏃ユ湡鏃堕棿寮瑰嚭閫夋嫨妗嗘瀯閫犲嚱锟???
     *
     * @param activity
     *            锛氳皟鐢ㄧ殑鐖禷ctivity
     * @param initDateTime
     *            鍒濆鏃ユ湡鏃堕棿鍊硷紝浣滀负寮瑰嚭绐楀彛鐨勬爣棰樺拰鏃ユ湡鏃堕棿鍒濆锟???
     */
    public DateTimePickDialogsUtil(Activity activity, String initDateTime) {
        this.activity = activity;
        this.initDateTime = initDateTime;


    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            calendar = this.getCalendarByInintData(initDateTime);
        } else {
            //getString(R.string.image_get_file_null)
            initDateTime = calendar.get(Calendar.YEAR) +activity.getString(R.string.yeal)
                    + calendar.get(Calendar.MONTH) + activity.getString(R.string.yue)
                    + calendar.get(Calendar.DAY_OF_MONTH) + activity.getString(R.string.day)
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    /**
     * 寮瑰嚭鏃ユ湡鏃堕棿閫夋嫨妗嗘柟锟???
     *
     * @param inputDate
     *            :涓洪渶瑕佽缃殑鏃ユ湡鏃堕棿鏂囨湰缂栬緫锟???
     * @return
     */
    public AlertDialog dateTimePicKDialog(final EditText inputDate) {
        LinearLayout dateTimeLayout = (LinearLayout) activity
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(datePicker, timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);

        ad = new AlertDialog.Builder(activity)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton(activity.getString(R.string.isok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText(dateTime);
                    }
                })
                .setNegativeButton(activity.getString(R.string.ison), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();

        onDateChanged(null, 0, 0, 0);
        return ad;
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // 鑾峰緱鏃ュ巻瀹炰緥
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+activity.getString(R.string.yeal)+"MM"+activity.getString(R.string.yue)+"dd"+activity.getString(R.string.day)+" HH:mm");

        dateTime = sdf.format(calendar.getTime());
        ad.setTitle(dateTime);
    }

    /**
     * 瀹炵幇灏嗗垵濮嬫棩鏈熸椂锟???2012锟???07锟???02锟??? 16:45 鎷嗗垎鎴愬勾 锟??? 锟??? 锟??? 锟??? 锟???,骞惰祴鍊肩粰calendar
     *
     * @param initDateTime
     *            鍒濆鏃ユ湡鏃堕棿锟??? 瀛楃涓插瀷
     * @return Calendar
     */


    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();
        String yue=activity.getString(R.string.day),yeal=activity.getString(R.string.yeal),mon=activity.getString(R.string.yue);
          // 灏嗗垵濮嬫棩鏈熸椂锟???2012锟???07锟???02锟??? 16:45 鎷嗗垎鎴愬勾 锟??? 锟??? 锟??? 锟??? 锟???
           date = spliteString(initDateTime, yue, "index", "front"); // 鏃ユ湡
        time = spliteString(initDateTime, yue, "index", "back"); // 鏃堕棿

           yearStr = spliteString(date, yeal, "index", "front"); // 骞翠唤
           monthAndDay = spliteString(date, yeal, "index", "back"); // 鏈堟棩zz

           monthStr = spliteString(monthAndDay, mon, "index", "front"); // 锟???
           dayStr = spliteString(monthAndDay, mon, "index", "back"); // 锟???

           hourStr = spliteString(time, ":", "index", "front"); // 锟???
           minuteStr = spliteString(time, ":", "index", "back"); // 锟???


        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay, currentHour,
                currentMinute);
        return calendar;
    }

    /**
     * 鎴彇瀛愪覆
     *
     * @param srcStr
     *            婧愪覆
     * @param pattern
     *            鍖归厤妯″紡
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;

        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 鍙栧緱瀛楃涓茬锟???娆″嚭鐜扮殑浣嶇疆
        } else {
            loc = srcStr.lastIndexOf(pattern); // 锟???鍚庝竴涓尮閰嶄覆鐨勪綅锟???
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 鎴彇瀛愪覆
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 鎴彇瀛愪覆
        }
        return result;
    }
}
