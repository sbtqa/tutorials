package ru.sbtqa.tutorials.advanced.mockito;

import java.math.BigDecimal;

/**
 * Bank account.
 */
public interface BankAccount {
    /**
     * Returns the current balance on the bank account.
     *
     * @return the current balance on the bank account
     */
    BigDecimal getBalance();

    /**
     * Deposits the specified amount of funds to the account.
     *
     * @param amount the amount of funds to deposit
     */
    void deposit(BigDecimal amount);

    /**
     * Withdraws the specified amount of funds from the bank account.
     *
     * @param amount the amount of funds to withdraw
     * @throws InsufficientFundsException if there is insufficient funds on the bank account
     */
    void withdraw(BigDecimal amount) throws InsufficientFundsException;
}
