package OOPproject.main.humanpart;

public class Employee extends User {
	private static final long serialVersionUID = -4039626440611189389L;
	/**
     * Default constructor for creating an Employee object.
     */
	private int salary;
    public Employee() {
		super();
	}
	public Employee(String name, String email, String password, Boolean isReseacher, int salary) {
		super(name, email, password, isReseacher);
		this.salary=salary;
	}
	/**
     * Overrides the equals method of the base class to compare Employee objects.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
	public int getSalary() {
		return salary;
	}
}
