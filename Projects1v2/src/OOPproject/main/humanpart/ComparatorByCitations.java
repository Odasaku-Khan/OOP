package OOPproject.main.humanpart;
import OOPproject.main.researchpart.*;

public class ComparatorByCitations extends ResearchPaperComporator {
	/**
     * Compares two ResearchPaper objects based on the number of citations.
     *
     * @param paper1 The first ResearchPaper object to compare.
     * @param paper2 The second ResearchPaper object to compare.
     * @return An integer representing the result of the comparison:
     *         - a negative value if paper1 has fewer citations than paper2,
     *         - 0 if both papers have the same number of citations,
     *         - a positive value if paper1 has more citations than paper2.
     */
    @Override
    public int compare(ResearchPaper paper1, ResearchPaper paper2) {
        return Integer.compare(paper1.getCitationsNumber(), paper2.getCitationsNumber());
    }
}