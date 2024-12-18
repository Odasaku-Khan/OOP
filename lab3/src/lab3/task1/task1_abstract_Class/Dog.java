package lab3.task1.task1_abstract_Class;

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    @Override
    void makeSound() {
        System.out.println(name + " says: Woof!");
    }
}