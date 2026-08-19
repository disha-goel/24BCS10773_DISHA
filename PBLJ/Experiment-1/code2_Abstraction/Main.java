abstract class Bank {

    private int balance = 1000;

    public void getBalance() {
        System.out.println("Balance: " + balance);
    }

    public void setBalance(int value) {
        balance = value;
    }

    abstract void debit(int amount);

    abstract void credit(int amount);
}

class Sbi extends Bank {

    public void debit(int amount) {
        int current = 1000;
        current = current - amount;
        setBalance(current);
    }

    public void credit(int amount) {
        int current = 1000;
        current = current + amount;
        setBalance(current);
    }
}

public class Main {
    public static void main(String[] args) {

        Sbi s = new Sbi();

        s.getBalance();
        s.debit(300);
        s.getBalance();
        s.credit(500);
        s.getBalance();
    }
}

/*
Output
Balance: 1000
Balance: 700
Balance: 1500
*/