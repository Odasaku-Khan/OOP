package lab3.task4;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Manager extends Employee {
    private Vector<Employee> team;
    private double bonus;

    public Manager(String name, double salary, Date hireDate, String insuranceNumber, double bonus) {
        super(name, salary, hireDate, insuranceNumber);
        this.team = new Vector<>();
        this.bonus = bonus;
    }

    public Vector<Employee> getTeam() {
        return team;
    }

    public void addTeamMember(Employee employee) {
        team.add(employee);
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Manager{" +
                super.toString() +
                ", bonus=" + bonus +
                ", team=" + team +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Manager manager = (Manager) obj;
        return Double.compare(manager.bonus, bonus) == 0 && team.equals(manager.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus, team);
    }

    @Override
    public int compareTo(Employee other) {
        if (!(other instanceof Manager)) {
            return super.compareTo(other);
        }
        Manager otherManager = (Manager) other;
        int salaryComparison = Double.compare(this.getSalary(), otherManager.getSalary());
        if (salaryComparison != 0) return salaryComparison;
        return Double.compare(this.bonus, otherManager.bonus);
    }

    @Override
    public Manager clone() {
        Manager cloned = null;
		try {
			cloned = (Manager) super.clone();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cloned.team = new Vector<>(this.team); // Shallow copy for team
        return cloned;
    }
}

