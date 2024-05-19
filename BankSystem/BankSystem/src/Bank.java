import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
    }

    public void createAccount(int accountId, String userName, double initialBalance) {
        Account account = new Account(accountId, userName, initialBalance);
        accounts.add(account);
    }

    public void performTransaction(Transaction transaction) throws InsufficientFundsException {
        Account source = findAccountById(transaction.getOriginatingAccountId());
        Account destination = findAccountById(transaction.getResultingAccountId());

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Invalid account ID");
        }

        double transactionAmount = transaction.getAmount();
        double transactionFee = transaction.calculateFee();

        if (source.getBalance() < transactionAmount + transactionFee) {
            throw new InsufficientFundsException("Not enough funds in the source account");
        }

        source.withdraw(transactionAmount + transactionFee);
        destination.deposit(transactionAmount);

        totalTransactionFeeAmount += transactionFee;
        totalTransferAmount += transactionAmount;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    private Account findAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }
}
