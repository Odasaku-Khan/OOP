package OOPproject.main.humanpart;
import java.util.Comparator;
import OOPproject.main.researchpart.*;

public class ResearchPaperComporator implements Comparator<ResearchPaper> {
	/**
     * Compares two research papers based on their number of citations.
     *
     * @param paper1 The first research paper to compare.
     * @param paper2 The second research paper to compare.
     * @return The result of the comparison based on the number of citations.
     */
    @Override
    public int compare(ResearchPaper paper1, ResearchPaper paper2) {
        return Integer.compare(paper1.getCitationsNumber(), paper2.getCitationsNumber());
    }
}
