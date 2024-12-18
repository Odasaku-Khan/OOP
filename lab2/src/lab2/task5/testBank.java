package lab2.task5;

public class testBank {
	public static void main(String[] args) {
        
        Bank bank = new Bank();

        SavingAccount savings1 = new SavingAccount(101, 4.5);
        CheckingAccount checking1 = new CheckingAccount(102);

        SavingAccount savings2 = new SavingAccount(103, 7.4);
        CheckingAccount checking2 = new CheckingAccount(104);

        SavingAccount savings3 = new SavingAccount(105, 9.42);
        CheckingAccount checking3 = new CheckingAccount(106);

        bank.openAccount(savings1);
        bank.openAccount(checking1);
        bank.openAccount(savings3);
        bank.openAccount(checking2);
        bank.openAccount(savings2);
        bank.openAccount(checking3);
        

        savings1.deposit(7777);
        checking1.deposit(500);
        savings1.deposit(1000);
        savings1.deposit(1000);
        savings1.deposit(1000);
        savings1.deposit(1000);
        savings1.deposit(1000);
        savings1.deposit(1000);
        checking1.withdraw(500);

        bank.updateAccounts();

        savings1.transfer(6666, checking3);
        savings1.transfer(6666, checking2);

        bank.updateAccounts();
        
        savings2.deposit(1000);
        savings1.deposit(1000);
        savings2.deposit(1000);
        savings3.deposit(1000);
        savings3.deposit(1000);
        savings1.deposit(1000);
        checking2.withdraw(500);

        
        bank.updateAccounts();
        bank.displayAccounts();


        bank.displayAccounts();
        
        bank.displayAccounts();
    }

}
