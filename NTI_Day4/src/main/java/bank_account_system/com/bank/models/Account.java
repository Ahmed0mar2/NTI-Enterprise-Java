package bank_account_system.com.bank.models;

import bank_account_system.com.bank.exceptions.InsufficientFundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private Customer owner;
    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    public Account(double balance, String accountNumber, Customer owner) {
        if (accountNumber == null || accountNumber.isEmpty())
            logger.error("Account number must have a value");
        else
            this.accountNumber = accountNumber;
        if (balance < 0)
            logger.error("Balance cannot be negative");
        else {
            this.balance = balance;
        }
        this.owner = owner;
    }

    public abstract String getAccountType();

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            logger.error("You cant deposit negative or zero amount");
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        } else {
            balance -= amount;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }
}
