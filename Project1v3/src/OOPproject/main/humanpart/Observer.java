package OOPproject.main.humanpart;

public interface Observer {
	/**
	 * Update method called by the subject to notify the observer about a change.
	 *
	 * @param newsItem The identifier or information about the updated news.
	 */
    void update(int newsItem);
}