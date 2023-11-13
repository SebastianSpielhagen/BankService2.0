package org.spielhagen;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private List<Account> accounts;

    public BankService() {
        this.accounts = new ArrayList<>();
    }

    public String[] split(String accountNumber) {
        Account account = findAccount(accountNumber);
        List<String> newAccountNumbers = new ArrayList<>();
        if (account != null) {
            String[] accountOwners = account.getClient().getLastName().split(" ");
            double splitAmount = Math.floor(account.getBalance() / accountOwners.length);
            double remainder = account.getBalance() % accountOwners.length;

            for (int i = 0; i < accountOwners.length; i++) {
                String newAccountNumber = generateAccountNumber();
                Account newAccount = new Account(newAccountNumber, splitAmount, new Client("", accountOwners[i], ""));
                accounts.add(newAccount);
                newAccountNumbers.add(newAccountNumber);
            }

            for (int i = 0; i < remainder; i++) {
                accounts.get(i).setBalance(accounts.get(i).getBalance() + 1);
            }
        }
        return newAccountNumbers.toArray(new String[0]);
    }

    public String openAccount(Client customer) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, 0, customer);
        accounts.add(account);
        return accountNumber;
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            return true;
        }
        return false;
    }

    private String generateAccountNumber() {
        // Generate a random account number
        return String.valueOf((int) (Math.random() * 1000000000));
    }

    private Account findAccount(String accountNumber) {
        // Find an account by its account number
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
