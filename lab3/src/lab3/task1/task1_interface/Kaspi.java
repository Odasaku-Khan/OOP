package lab3.task1.task1_interface;

public class Kaspi implements Payable {

	@Override
	public void Payment(int amount) {
		System.out.println("Payed by KaspiQR "+amount);
	}
}
