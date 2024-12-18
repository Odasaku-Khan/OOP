package lab3.task3;
import java.util.HashSet;
import java.util.Set;

public class Hash<E> implements Mycollection<E>{
	private final Set<E> set= new HashSet<>();

	@Override
	public boolean remove(E element) {
		return set.remove(element);
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean add(E element) {
		return set.add(element);
	}
	public String toString() {
        return set.toString();
    }
}
