package lab3.task6;

public abstract class Person implements Comparable<Person>,Payable,Movable,Swimable {
    protected String name;
    protected int age;
    protected int salary;
    protected Animal a;

    public Person() {
    }
    public boolean canPay() {
        return this.salary > 2000;  
    }

    public Person(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void assignPet(Animal pet) {
        this.a = pet;
    }

    public void removePet() {
        this.a = null;
    }

    public Animal getPet() {
        return a;
    }

    public boolean hasPet() {
        return a != null;
    }

    public void leavePetWith(Person p) {
        if (p instanceof PhDStudent) {
            System.out.println("PhD students cannot take pets!");
        } else {
            p.assignPet(this.a);
            this.a = null;
        }
    }

    public void retrievePetFrom(Person p) {
        if (p.a != null) {
            this.assignPet(p.getPet());
            p.a = null;
        } else {
            System.out.println(p.name + " doesn't have a pet.");
        }
    }

    @Override
    public String toString() {
        return "Hello, my name is " + name + ". I am " + age + " years old, my pet is: " + a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person p = (Person) o;
        return age == p.age && name.equals(p.name) && ((a == null && p.a == null) || (a != null && a.equals(p.a)));
    }

    @Override
    public int hashCode() {
        int result = 101;
        result = result * 91 + age;
        result = result * 91 + name.hashCode();
        return result;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age); // Сравнение по возрасту
    }
}
