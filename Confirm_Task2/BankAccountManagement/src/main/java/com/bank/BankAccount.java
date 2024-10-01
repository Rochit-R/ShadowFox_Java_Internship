package com.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        logger.info("Account created for {} with initial balance {}", accountHolderName, initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        logger.info("Deposited {} to account {}", amount, accountNumber);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        logger.info("Withdrew {} from account {}", amount, accountNumber);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }
}
