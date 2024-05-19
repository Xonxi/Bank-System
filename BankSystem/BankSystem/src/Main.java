
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("MyBank", 10, 0.05);

        bank.createAccount(1, "Alice", 1000);
        bank.createAccount(2, "Bob", 500);

        Transaction flatFeeTransaction = new FlatFeeTransaction(100, 1, 2, "Payment", 10);
        Transaction percentFeeTransaction = new PercentFeeTransaction(200, 2, 1, "Transfer", 5);

        try {
            bank.performTransaction(flatFeeTransaction);
            bank.performTransaction(percentFeeTransaction);
        } catch (InsufficientFundsException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }

        System.out.println("Total transaction fee amount: $" + bank.getTotalTransactionFeeAmount());
        System.out.println("Total transfer amount: $" + bank.getTotalTransferAmount());

        System.out.println("Account balances:");
        for (Account account : bank.getAccounts()) {
            System.out.println(account.getAccountId() + ": $" + account.getBalance());
        }
    }
}
