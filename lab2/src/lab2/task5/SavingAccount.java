package lab2.task5;

public class SavingAccount extends Account {
	private double iRate;

	
	public SavingAccount(int num, double iRate) {
		super(num);
		this.iRate = iRate;
	}
	
	public void addIntRate() {
		super.deposit(balance * (iRate / 100));
		
	}
	@Override
    public String toString() {
        return super.toString() + ", Процентная ставка: " + iRate + "%";
    }
	
}
