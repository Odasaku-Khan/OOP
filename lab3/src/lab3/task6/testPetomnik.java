package lab3.task6;

public class testPetomnik {

    public static void main(String[] args) {

        Person john = new Employee("John", 30, 4500, "Engineer");
        Person alice = new PhDStudent("Alice", 26, 3500, "Comp. Science");
        Person lize = new PhDStudent("Lize", 29, 1200, "Comp. Science");
        Animal murka = new Cat("Murka", 5);
        Animal rex = new Dog("Rex", 7);
        john.assignPet(murka);
        alice.assignPet(rex);
        System.out.println(murka.getSound());
        System.out.println(rex.getSound());
        PersonRegistry registry = new PersonRegistry();
        registry.addPerson(john);
        registry.addPerson(alice);
        registry.addPerson(lize);
        System.out.println(registry);
        registry.sortRegistry();
        System.out.println("\nComparing John and Alice:");
        int comparisonResult = john.compareTo(alice);
        if (comparisonResult < 0) {
            System.out.println("John is younger than Alice.");
        } else if (comparisonResult > 0) {
            System.out.println("John is older than Alice.");
        } else {
            System.out.println("John and Alice are of the same age.");
        }
        System.out.println("\nTrying to leave pet with a PhDStudent:");
        john.leavePetWith(lize);
        System.out.println("John's pet: " + john.getPet());
        System.out.println("Lize's pet: " + lize.getPet());
        System.out.println("Can John pay? " + john.canPay());
        System.out.println("Can Alice pay? " + alice.canPay());
        System.out.println("Can Lize pay? " + lize.canPay());
    }
}
