package lab3.task4;
import java.util.Date;
import java.util.Objects;
public class Employee extends Person implements Comparable<Employee>,Cloneable {

	private double salary;
	private Date hireDay;
	private String insuranceNum;
	public Employee(String name,double salary,Date hireDay,String insuranceNum) {
		super(name);
		this.salary=salary;
		this.insuranceNum = insuranceNum;
		this.hireDay = hireDay;
	}
	


	public Date getHireDay() {
		return hireDay;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String getInsuranceNum() {
		return insuranceNum;
	}


	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDay +
                ", insuranceNumber='" + insuranceNum + '\'' +
                '}';
    }
	public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Employee employee = (Employee) obj;
        return Double.compare(employee.salary, salary) == 0 &&
               hireDay.equals(employee.hireDay) &&
               insuranceNum.equals(employee.insuranceNum);
    }
	public int hashCode() {
        return Objects.hash(super.hashCode(), salary, hireDay, insuranceNum);
    }
	public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }
	public Employee clone() {
        try {
            Employee cloned = (Employee) super.clone();
            cloned.hireDay = new Date(this.hireDay.getTime()); 
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); 
        }
    }

}
