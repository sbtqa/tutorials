package ru.sberbank.example;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 *
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

  @Override
  public void withdraw(BigDecimal amount)
          throws InsufficientFundsException {
    if (balance.compareTo(amount) >= 0) {
      balance = balance.subtract(amount);
    } else {
      throw new InsufficientFundsException();
    }
  }
}
