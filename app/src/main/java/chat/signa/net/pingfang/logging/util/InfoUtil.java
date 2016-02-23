package chat.signa.net.pingfang.logging.util;

import android.os.Environment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/8.
 */
public class InfoUtil {

    public static boolean isSDCardMounted() {
        // return Environment.getExternalStorageState().equals("mounted");
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    // ��ȡSD���ĸ�Ŀ¼
    public static String getSDCardBaseDir() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }


    public static String timeInfo(){
        StringBuffer sb=new StringBuffer();
        Calendar now = Calendar.getInstance();
        sb.append(now.get(Calendar.YEAR) );
        sb.append("��");
        sb.append( (now.get(Calendar.MONTH) + 1) );
        sb.append("��");
        sb.append(now.get(Calendar.DAY_OF_MONTH));
        sb.append("��");
        sb.append("  " + now.get(Calendar.HOUR_OF_DAY));
        sb.append(":" + now.get(Calendar.MINUTE));
        return sb.toString();

    }

    /**
     *
     * @param date ����ʱ��
     * @param date2 ��ǰʱ��
     * @return
     */
    public static long calculateTime(String date,String date2){
        long day=0;

        try {

            String dates = spliteString(date, "��", "index", "front"); // ����
            String datese = spliteString(date2, "��", "index", "front"); // ����
            SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy��MM��dd");
            System.out.print("dates" + dates);
            System.out.print("datese" + datese);
            Date nows = df.parse(dates);
            Date nowe = df2.parse(datese);
            long l = nows.getTime() - nowe.getTime();
             day = l / (24 * 60 * 60 * 1000);


        } catch (Exception e) {
            System.out.println("���ִ���.....");
        }
        return day;

    }

    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;

        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 鍙栧緱�?�楃涓茬涓�娆�?�嚭鐜扮殑浣嶇疆
        } else {
            loc = srcStr.lastIndexOf(pattern); // 鏈�鍚庝竴涓尮閰嶄覆鐨勪綅缃�
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 鎴彇�?�愪�?
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 鎴彇�?�愪�?
        }
        return result;
    }
}
