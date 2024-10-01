package com.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BankAccountManager {
    private Map<String, BankAccount> accounts = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(BankAccountManager.class);

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account with this number already exists.");
        }
        accounts.put(accountNumber, new BankAccount(accountNumber, accountHolderName, initialBalance));
        logger.info("Account created for {} with account number {}", accountHolderName, accountNumber);
    }

    public String deposit(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            return "Deposit failed: Account not found.";
        }
        try {
            account.deposit(amount);
            return "Deposit successful.";
        } catch (IllegalArgumentException e) {
            return "Deposit failed: " + e.getMessage();
        }
    }

    public String withdraw(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            return "Withdrawal failed: Account not found.";
        }
        try {
            account.withdraw(amount);
            return "Withdrawal successful.";
        } catch (IllegalArgumentException e) {
            return "Withdrawal failed: " + e.getMessage();
        }
    }

    public double getBalance(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return account.getBalance();
    }
}
