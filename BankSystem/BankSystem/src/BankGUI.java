import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGUI extends JFrame {
    private Bank bank;
    private JComboBox<Account> accountComboBox;
    private JLabel balanceLabel;
    private JTextField depositField;
    private JTextField withdrawField;

    public BankGUI(Bank bank) {
        this.bank = bank;

        setTitle("Bank System");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Select Account:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        accountComboBox = new JComboBox<>(bank.getAccounts().toArray(new Account[0]));
        panel.add(accountComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Account Balance:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        balanceLabel = new JLabel();
        panel.add(balanceLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Deposit:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        depositField = new JTextField();
        panel.add(depositField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Withdraw:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        withdrawField = new JTextField();
        panel.add(withdrawField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton viewBalanceButton = new JButton("View Balance");
        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account selectedAccount = (Account) accountComboBox.getSelectedItem();
                balanceLabel.setText("$" + selectedAccount.getBalance());
            }
        });
        panel.add(viewBalanceButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(depositField.getText());
                    Account selectedAccount = (Account) accountComboBox.getSelectedItem();
                    selectedAccount.deposit(amount);
                    JOptionPane.showMessageDialog(null, "Deposit Successful");
                    balanceLabel.setText("$" + selectedAccount.getBalance());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount");
                }
            }
        });
        panel.add(depositButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(withdrawField.getText());
                    Account selectedAccount = (Account) accountComboBox.getSelectedItem();
                    selectedAccount.withdraw(amount);
                    JOptionPane.showMessageDialog(null, "Withdrawal Successful");
                    balanceLabel.setText("$" + selectedAccount.getBalance());
                } catch (NumberFormatException | InsufficientFundsException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        panel.add(withdrawButton, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        Bank bank = new Bank("MyBank", 10, 0.05);
        bank.createAccount(1, "Alice", 1000);
        bank.createAccount(2, "Bob", 500);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankGUI(bank).setVisible(true);
            }
        });
    }
}
