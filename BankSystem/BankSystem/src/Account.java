public class Account {
    private int accountId;
    private String userName;
    private double balance;

    public Account(int accountId, String userName, double initialBalance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException("Not enough funds in the account");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountId() {
        return accountId;
    }
}
