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

    // 获取SD卡的根目录
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
        sb.append("年");
        sb.append( (now.get(Calendar.MONTH) + 1) );
        sb.append("月");
        sb.append(now.get(Calendar.DAY_OF_MONTH));
        sb.append("日");
        sb.append("  " + now.get(Calendar.HOUR_OF_DAY));
        sb.append(":" + now.get(Calendar.MINUTE));
        return sb.toString();

    }

    /**
     *
     * @param date 输入时间
     * @param date2 当前时间
     * @return
     */
    public static long calculateTime(String date,String date2){
        long day=0;

        try {

            String dates = spliteString(date, "日", "index", "front"); // 日期
            String datese = spliteString(date2, "日", "index", "front"); // 日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy年MM月dd");
            System.out.print("dates" + dates);
            System.out.print("datese" + datese);
            Date nows = df.parse(dates);
            Date nowe = df2.parse(datese);
            long l = nows.getTime() - nowe.getTime();
             day = l / (24 * 60 * 60 * 1000);


        } catch (Exception e) {
            System.out.println("出现错误.....");
        }
        return day;

    }

    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;

        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 閸欐牕绶辩?涙顑佹稉鑼儑娑擄拷濞嗏?冲毉閻滄壆娈戞担宥囩枂
        } else {
            loc = srcStr.lastIndexOf(pattern); // 閺堬拷閸氬簼绔存稉顏勫爱闁板秳瑕嗛惃鍕秴缂冿拷
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 閹搭亜褰囩?涙劒瑕?
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 閹搭亜褰囩?涙劒瑕?
        }
        return result;
    }
}
