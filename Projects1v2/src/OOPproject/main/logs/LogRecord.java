package OOPproject.main.logs;
import java.io.Serializable;
import OOPproject.main.datapart.DataRepository;
public class LogRecord implements Serializable {
    private static final long serialVersionUID = 8256548752459387945L;
	private int logId;
    private int userId;
    private String text;
    private String time;
    public LogRecord(int userId, String text, String time) {
        this.logId = DataRepository.getNextId();
        this.text = text;
        this.time = time;
        this.userId = userId;
    }
	public int getLogId() {
		return logId;
	}
	public int getUserId() {
		return userId;
	}
	public String getText() {
		return text;
	}
	public String getTime() {
		return time;
	}
	 public String toString() {
	        return "LogRecord{" +
	                "logId=" + logId +
	                ", userId=" + userId +
	                ", text='" + text + '\'' +
	                ", time='" + time + '\'' +
	                '}';
	    }
}
