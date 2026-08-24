package bank_account_system.com.bank.models;

public class SavingAccount extends Account {

    private double interestRate;

    public SavingAccount(double balance, String accountNumber, Customer owner, double interestRate) {
        super(balance, accountNumber, owner);
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

}
