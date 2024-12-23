package OOPproject.main.humanpart;
import java.io.Serializable;
import OOPproject.main.datapart.DataRepository;
public abstract class UserDecorator implements Serializable {
	private static final long serialVersionUID = 5722473828500210018L;
	/**
     * The identifier of the user being decorated.
     */
    protected Integer decoratedUser;
    /**
     * Default constructor.
     */
    public UserDecorator() {
    }
    /**
     * Parameterized constructor with the decorated user's identifier.
     *
     * @param decoratedUser The identifier of the user being decorated.
     */
    public UserDecorator(Integer decoratedUser) {
        this.decoratedUser = decoratedUser;
    }
    /**
     * Gets the decorated user object from the data repository.
     *
     * @return The decorated user object.
     */
    public User getDecoratedUser() {
        return DataRepository.getUserById(this.decoratedUser);
    }
    /**
     * Checks if this UserDecorator is equal to another object.
     *
     * @param obj The object to compare with this UserDecorator.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDecorator other = (UserDecorator) obj;
        return decoratedUser == other.decoratedUser;
    }
}