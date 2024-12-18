package lab3.task4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice", 50000, new Date(), "INS123");
        Employee emp2 = new Employee("Bob", 60000, new Date(), "INS124");
        Employee emp3 = new Employee("Charlie", 50000, new Date(), "INS125");

        Manager mgr1 = new Manager("Diana", 80000, new Date(), "INS126", 10000);
        mgr1.addTeamMember(emp1);
        mgr1.addTeamMember(emp2);

        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(mgr1);

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(mgr1);

        System.out.println("\nSorted by salary:");
        Collections.sort(employees);
        employees.forEach(System.out::println);

        System.out.println("\nSorted by name:");
        employees.sort(new ComparatorName());
        employees.forEach(System.out::println);

        System.out.println("\nSorted by hire date:");
        employees.sort(new HireDayCompare());
        employees.forEach(System.out::println);

        System.out.println("\nCloning:");
        Manager clonedManager = mgr1.clone();
        System.out.println("Original Manager: " + mgr1);
        System.out.println("Cloned Manager: " + clonedManager);
    }
}
