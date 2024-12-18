package lab3.task6;

import java.util.Collections;
import java.util.Vector;

public class PersonRegistry {
    private Vector<Person> clients;

    public PersonRegistry() {
        clients = new Vector<>();
    }

    public void addPerson(Person p) {
        clients.add(p);
    }

    public void removePerson(Person p) {
        clients.remove(p);
    }

    public void sortRegistry() {
        Collections.sort(clients); // Сортировка по возрасту
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person Registry:\n");
        for (Person p : clients) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}
