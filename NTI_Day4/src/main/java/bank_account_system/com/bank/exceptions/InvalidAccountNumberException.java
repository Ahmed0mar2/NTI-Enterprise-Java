package bank_account_system.com.bank.exceptions;

public class InvalidAccountNumberException extends RuntimeException {
    public InvalidAccountNumberException(String message) {
        super(message);
    }
}
