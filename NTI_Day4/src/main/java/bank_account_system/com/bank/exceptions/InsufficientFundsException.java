package bank_account_system.com.bank.exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
