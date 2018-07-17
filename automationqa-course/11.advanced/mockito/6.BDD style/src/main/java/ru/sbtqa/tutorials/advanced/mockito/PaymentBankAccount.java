package ru.sbtqa.tutorials.advanced.mockito;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * Ordinary payment bank account that doesn't allows to have negative balance.
 */
public class PaymentBankAccount implements BankAccount {
    private BigDecimal balance = ZERO;

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    /**
     * Withdraws funds from the account. does not allow to withdraw funds more than there is on the
     * account.
     *
     * @param amount the amount of funds to withdraw
     * @throws InsufficientFundsException if not enough funds to withdraw specified amount (current
     *                                    balance < amount)
     */
    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new InsufficientFundsException();
        }
    }
}
