package bank_account_system.com.bank.management;

import bank_account_system.com.bank.exceptions.InsufficientFundsException;
import bank_account_system.com.bank.exceptions.InvalidAccountNumberException;
import bank_account_system.com.bank.models.Account;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import bank_account_system.com.bank.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bank {

    private final HashMap<String, Account> accounts = new HashMap<>();
    private final Deque<String> transactionHistory = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(Bank.class);

    public void openAccount(Account account) {
        if (!accounts.containsKey(account.getAccountNumber())) {
            accounts.put(account.getAccountNumber(), account);
            logger.info("Opened {} {} for {} (${})\n", account.getAccountType(), account.getAccountNumber(), account.getOwner().getName(), account.getBalance());
        } else
            logger.error("Account already exists");
    }

    public boolean lookUpAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber))
            return true;
        else
            throw new InvalidAccountNumberException("No account found");
    }

    public void depositAccount(String accountNumber, double amount) {
        if (lookUpAccount(accountNumber)) {
            Account account = accounts.get(accountNumber);
            account.deposit(amount);
            logger.info("Deposited {} in your {} your new balance is: {}\n", amount, account.getAccountType(), account.getBalance());
            transactionHistory.offer(account.getOwner().getName() + " deposited $" + amount + " in account " + accountNumber);
        }
    }

    public void withdrawAccount(String accountNumber, double amount) throws InsufficientFundsException {
        if (lookUpAccount(accountNumber)) {
            Account account = accounts.get(accountNumber);
            account.withdraw(amount);
            logger.info("Withdrew {} from your {}, your new balance is: {}\n", amount, account.getAccountType(), account.getBalance());
            transactionHistory.offer(account.getOwner().getName() + " withdrew $" + amount + " from account " + accountNumber);
        }
    }

    public Deque<String> getTransactionHistory() {
        return new LinkedList<>(transactionHistory);
    }
}
