package lab2.task5;

import java.util.Vector;

public class Bank {
	private Vector<Account> accounts; 

    public Bank() {
        accounts = new Vector<>();
    }

    public void openAccount(Account account) {
        accounts.add(account);
    }

    public void closeAccount(Account account) {
        accounts.remove(account);
    }

    public void updateAccounts() {
        for (Account account : accounts) {
            if (account instanceof SavingAccount) {
                ((SavingAccount) account).addIntRate(); 
            } 
            else if (account instanceof CheckingAccount) {
                ((CheckingAccount) account).deductFee(); 
            }
        }
    }

    public void displayAccounts() {
        for (Account account : accounts) {
            account.print();
        }
    }
	
}
