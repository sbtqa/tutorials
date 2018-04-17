package ru.sberbank.example;

/**
 *
 */
public class InsufficientFundsException extends Exception {
  public InsufficientFundsException() {
    super("Insufficient funds");
  }
}
