package bank_account_system.com.bank.models;

public class CheckingAccount extends Account {

    private double overDraft;

    public CheckingAccount(double balance, String accountNumber, Customer owner, double overDraft) {
        super(balance, accountNumber, owner);
        this.overDraft = overDraft;
    }

    @Override
    public String getAccountType() {
        return "Checking Account";
    }

    public double getOverDraft() {
        return overDraft;
    }
}
