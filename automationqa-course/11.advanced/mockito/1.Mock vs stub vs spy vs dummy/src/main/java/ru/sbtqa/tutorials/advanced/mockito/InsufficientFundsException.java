package ru.sbtqa.tutorials.advanced.mockito;

/**
 * Thrown when an application attempts to withdraw funds when it is not acceptable. The most common
 * use case: there is not enough funds on the bank account and it is not allowed to have negative
 * balance.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient funds");
    }
}
