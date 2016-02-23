package chat.signa.net.pingfang.logging.entity;

/**
 * 通知公告信息
 * Created by Administrator on 2016/1/8.
 */
public class NoticeInfo {
    private int id;
    private String title;
    private String massage;
    private String time;


    public NoticeInfo() {

    }

    public NoticeInfo(int id, String time, String massage, String title) {
        this.id = id;
        this.time = time;
        this.massage = massage;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
