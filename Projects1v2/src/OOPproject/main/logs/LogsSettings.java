package OOPproject.main.logs;
import java.io.*;
import java.util.Vector;
public class LogsSettings {
    private static Vector<LogRecord> logs;

    static {
        logs = new Vector<LogRecord>();
    }

    /**
     * Constructs a LogsSettings object.
     */
    public LogsSettings() {
    }

    /**
     * Adds a log record using serialization.
     *
     * @param logRecord The log record to be added.
     */
    public static void addLogRecord(LogRecord logRecord) {
        try {
            logs.add(logRecord);
            serialize(logs, "data/logs.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serialize(Object obj, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        }
    }

    /**
     * Retrieves logs from serialization.
     *
     */
    @SuppressWarnings("unchecked")
	public static void retrieveLogs() {
        try {
            logs = (Vector<LogRecord>) deserialize("data/logs.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return ois.readObject();
        }
    }

    /**
     * Gets the logs stored in the application. If logs are not loaded, it retrieves them from serialization.
     *
     * @return The vector of log records.
     */
    public static Vector<LogRecord> getLogs() {
        return logs;
    }
}