package chat.signa.net.pingfang.logging.entity;

/**
 * Created by Administrator on 2016/1/28.
 */
public class LogInfo {
    private int id;
    private String startTime;
    private String project;
    private String stopTime;
    private String logMessage;
    private String remark;

    public LogInfo(int id, String startTime, String project, String stopTime, String logMessage, String remark) {
        this.id = id;
        this.startTime = startTime;
        this.project = project;
        this.stopTime = stopTime;
        this.logMessage = logMessage;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
