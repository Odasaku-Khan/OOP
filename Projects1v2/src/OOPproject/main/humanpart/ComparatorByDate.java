package OOPproject.main.humanpart;
import OOPproject.main.researchpart.*;
public class ComparatorByDate extends ResearchPaperComporator {
	/**
     * Compares two ResearchPaper objects based on their publication dates.
     *
     * @param paper1 The first ResearchPaper object to compare.
     * @param paper2 The second ResearchPaper object to compare.
     * @return An integer representing the result of the comparison:
     *         - a negative value if paper1 was published before paper2,
     *         - 0 if both papers have the same publication date,
     *         - a positive value if paper1 was published after paper2.
     */
    @Override
    public int compare(ResearchPaper paper1, ResearchPaper paper2) {
        return paper1.getPublicationDate().compareTo(paper2.getPublicationDate());
    }
}