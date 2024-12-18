package lab3.task4;

import java.util.Comparator;

public class HireDayCompare implements Comparator<Employee>  {

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getHireDay().compareTo(o2.getHireDay());
	}

}
