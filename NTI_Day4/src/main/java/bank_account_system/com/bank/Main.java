package bank_account_system.com.bank;

import bank_account_system.com.bank.exceptions.InvalidAccountNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bank_account_system.com.bank.exceptions.InsufficientFundsException;
import bank_account_system.com.bank.management.Bank;
import bank_account_system.com.bank.models.Account;
import bank_account_system.com.bank.models.CheckingAccount;
import bank_account_system.com.bank.models.Customer;
import bank_account_system.com.bank.models.SavingAccount;

import java.util.Deque;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Bank CIB = new Bank();
        Customer c1 = new Customer("CJ", "cj@email.com", "1");
        Customer c2 = new Customer("Big Smoke", "bs@email.com", "2");
        Account sa = new SavingAccount(0, "1", c1, 0);
        Account ca = new CheckingAccount(0, "2", c2, 0);
        CIB.openAccount(sa);
        CIB.openAccount(ca);
        CIB.depositAccount("1", 250);

        try {
            CIB.withdrawAccount("2", 350);
        } catch (InsufficientFundsException e) {
            logger.error(e.getMessage());
        }

        try {
            CIB.withdrawAccount("2", 100000);
        } catch (InsufficientFundsException e) {
            logger.error(e.getMessage());
        }

        try {
            CIB.lookUpAccount("8");
        } catch (InvalidAccountNumberException e) {
            logger.error(e.getMessage());
        }

        System.out.println("========= Transaction History =========");
        Deque<String> d = CIB.getTransactionHistory();
        while (!d.isEmpty()) {
            logger.info(d.poll());
        }
    }
}
