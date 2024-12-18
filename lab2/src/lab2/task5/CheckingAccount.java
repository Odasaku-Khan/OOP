package lab2.task5;

public class CheckingAccount extends Account{
	
	private int transactionCount; 
    private static final int FREE_TRANSACTIONS = 3; 
    private static final double TRANSACTION_FEE = 0.02; 

    
    public CheckingAccount(int a) {
        super(a);
        transactionCount = 0;
    }

   @Override
    public void deposit(double sum) {
        super.deposit(sum);
        transactionCount++;
        deductFee();
    }
    @Override
    public void withdraw(double sum) {
        super.withdraw(sum);
        transactionCount++;
        deductFee();
    }
    public void deductFee() {
        if (transactionCount > FREE_TRANSACTIONS) {
            super.withdraw(TRANSACTION_FEE);
        }
    }
    @Override
    public String toString() {
        return super.toString() + ", Транзакций: " + transactionCount;
    }
}