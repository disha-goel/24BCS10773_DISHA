public class InsufficientFundsException extends Exception {
    private double missingAmount;

    public InsufficientFundsException(double missingAmount) {
        super("Insufficient funds. Missing amount: $" + missingAmount);
        this.missingAmount = missingAmount;
    }

    public double getMissingAmount() {
        return missingAmount;
    }
}