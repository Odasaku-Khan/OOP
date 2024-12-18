package OOPproject.main.academicpart;
import java.io.Serializable;
import java.util.Vector;
import OOPproject.main.datapart.DataRepository;
public class News implements Serializable {
	private static final long serialVersionUID = -2519743572089100985L;
	private int newsID;
	private String title;
	private String content;
	private Vector<String> comments;
	private NewsTopic topic;
	public News() {}
	public News(String title, String content, NewsTopic topic) {
        this.newsID = DataRepository.getNextId();
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.comments = new Vector<>();
    }
	public int getNewsID() {
		return newsID;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Vector<String> getComments() {
		return comments;
	}
	public NewsTopic getTopic() {
		return topic;
	}
	public void addComment(String comment) {
		this.comments.add(comment);
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        News other = (News) obj;
        return newsID == other.newsID;
    }
	@Override
    public String toString() {
        return "News [newsID=" + newsID + ", title=" + title + ", content=" + content + ", comments=" + comments
                + ", topic=" + topic + "]";
    }
}
