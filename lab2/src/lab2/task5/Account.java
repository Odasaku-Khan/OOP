package lab2.task5;

public class Account {
	protected double balance;
	protected int accNumber;
	
	
	public Account(int num) {
		this.balance = 0.0;
		this.accNumber = num;
	}
	
	public void deposit(double sum) {
		balance += sum;
	}
	
	public void withdraw(double sum) {
		if(sum > balance) {
			System.out.println("Не хватает баланса, доступный баланс: " + balance);
			
		}
		else {
			balance -= sum;
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public int getAccountNumber() {
		return accNumber;
	}
	
	public void transfer(double amount, Account other) {
		if(amount > balance) {
			System.out.println("Не хватает баланса, доступный баланс: " + balance);
			
		}
		else {
			balance -= amount;
			other.deposit(amount);
		}
	}
	
	
    public String toString() {
        return "Счет #" + accNumber + ", баланс: " + balance;
    }
    
    public final void print() {
        System.out.println(toString());
    }
	
	
	
	
}	
