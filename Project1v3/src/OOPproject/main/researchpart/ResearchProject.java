package OOPproject.main.researchpart;
import java.io.Serializable;
import java.util.Vector;
import OOPproject.main.datapart.DataRepository;
import OOPproject.main.humanpart.ResearcherDecorator;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = -1081015562294096053L;
	private int projectID;
    private String topic;
    private Vector<Integer> publishedPapers;
    private Vector<Integer> participants;

    /**
     * Constructs an empty ResearchProject object.
     */
    public ResearchProject() {
    }

    /**
     * Constructs a ResearchProject object with the specified topic.
     *
     * @param topic The topic of the research project.
     */
    public ResearchProject(String topic) {
        this.projectID = DataRepository.getNextId();
        this.topic = topic;
        this.publishedPapers = new Vector<>();
        this.participants = new Vector<>();
    }

    /**
     * Gets the unique identifier of the research project.
     *
     * @return The project ID.
     */
    public int getProjectID() {
        return this.projectID;
    }

    /**
     * Gets the topic of the research project.
     *
     * @return The topic.
     */
    public String getTopic() {
        return this.topic;
    }

    /**
     * Gets the list of published papers associated with the research project.
     *
     * @return A vector of ResearchPaper objects.
     */
    public Vector<ResearchPaper> getPublishedPapers() {
        Vector<ResearchPaper> papers = new Vector<>();
        for (Integer paperId : this.publishedPapers) {
            ResearchPaper paper = DataRepository.getResearchPaperById(paperId);
            if (paper != null) {
                papers.add(paper);
            }
        }
        return papers;
    }

    /**
     * Adds a paper to the list of published papers associated with the research project.
     *
     * @param paper The ID of the ResearchPaper to be added.
     */
    public void addPaper(int paper) {
        this.publishedPapers.add(paper);
    }

    /**
     * Gets the list of participants (ResearcherDecorator) involved in the research project.
     *
     * @return A vector of ResearcherDecorator objects.
     */
    public Vector<ResearcherDecorator> getParticipants() {
        Vector<ResearcherDecorator> participantsTmp = new Vector<>();
        for (Integer participantId : this.participants) {
            ResearcherDecorator participant = DataRepository.getResearcherById(participantId);
            if (participant != null) {
                participantsTmp.add(participant);
            }
        }
        return participantsTmp;
    }

    /**
     * Adds a participant to the list of participants involved in the research project.
     *
     * @param participant The ID of the ResearcherDecorator to be added.
     */
    public void addParticipant(int participant) {
        this.participants.add(participant);
    }

    /**
     * Checks if two ResearchProject objects are equal based on their project ID.
     *
     * @param obj The object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ResearchProject other = (ResearchProject) obj;
        return projectID == other.projectID;
    }

    /**
     * Returns a string representation of the ResearchProject object.
     *
     * @return A string representation containing the details of the research project.
     */
    @Override
    public String toString() {
        return "ResearchProject [projectID=" + projectID + ", topic=" + topic + ", publishedPapers=" + publishedPapers
                + ", participants=" + participants + "]";
    }
}
