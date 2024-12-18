package lab3.task1.task1_interface;

public class Halyk implements Payable {

	@Override
	public void Payment(int amount) {
		System.out.println("Payed by HalykQR "+amount);
		
	}
}
