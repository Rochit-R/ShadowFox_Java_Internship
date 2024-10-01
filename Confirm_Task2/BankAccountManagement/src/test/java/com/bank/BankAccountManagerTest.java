package com.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountManagerTest {
    private BankAccountManager manager;

    @BeforeEach
    public void setUp() {
        manager = new BankAccountManager();
        manager.createAccount("12345", "John Doe", 1000.0);
    }

    @Test
    public void testCreateAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.createAccount("12345", "Jane Doe", 500.0);
        });
    }

    @Test
    public void testDeposit() {
        String result = manager.deposit("12345", 500.0);
        assertEquals("Deposit successful.", result);
        assertEquals(1500.0, manager.getBalance("12345"));
    }

    @Test
    public void testWithdraw() {
        String result = manager.withdraw("12345", 200.0);
        assertEquals("Withdrawal successful.", result);
        assertEquals(800.0, manager.getBalance("12345"));
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        String result = manager.withdraw("12345", 2000.0);
        assertEquals("Withdrawal failed: Insufficient balance.", result);
    }

    @Test
    public void testDepositAccountNotFound() {
        String result = manager.deposit("67890", 500.0);
        assertEquals("Deposit failed: Account not found.", result);
    }

    @Test
    public void testWithdrawAccountNotFound() {
        String result = manager.withdraw("67890", 500.0);
        assertEquals("Withdrawal failed: Account not found.", result);
    }
}
