import java.util.Scanner;
import java.util.InputMismatchException;

public class ATMMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount(500.00);

        try {
            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();

            account.withdraw(amount);

        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");

        } finally {
            System.out.println("ATM session terminated.");
            sc.close();
        }
    }
}

/*
Output
Enter withdrawal amount: 10000
Insufficient funds. Missing amount: $9500.0
ATM session terminated.
*/