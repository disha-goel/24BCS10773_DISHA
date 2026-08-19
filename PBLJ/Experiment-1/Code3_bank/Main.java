class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        if (amount >= 0) {
            balance = amount;
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC101", 5000);

        account.deposit(2000);
        account.withdraw(1500);

        System.out.println("Final Balance: " + account.getBalance());

    }
}

/*
Output
Final Balance: 5500.0
*/

