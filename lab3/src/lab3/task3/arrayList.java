package lab3.task3;

import java.util.ArrayList;
import java.util.List;

class arrayList<E> implements Mycollection<E> {
    private List<E> list;

    public arrayList() {
        this.list = new ArrayList<>();
    }

    @Override
    public boolean add(E element) {
        return list.add(element);
    }

    @Override
    public boolean remove(E element) {
        return list.remove(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
