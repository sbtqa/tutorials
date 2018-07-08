package ru.sbtqa.patterns.behavioural.state;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class ActiveCardState implements CardState {

    @Override
    public void withdraw(Card card, int amount) {
        int balance = card.getBalance();
        assertThat(amount, lessThanOrEqualTo(balance));
        card.setBalance(balance - amount);
        System.out.println("Операция выполнена успешно");
    }

    @Override
    public void deposit(Card card, int amount) {
        card.setBalance(card.getBalance() + amount);
        System.out.println("Операция выполнена успешно");
    }
}
