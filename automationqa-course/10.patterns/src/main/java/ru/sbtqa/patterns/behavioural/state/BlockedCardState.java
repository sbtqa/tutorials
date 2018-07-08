package ru.sbtqa.patterns.behavioural.state;

public class BlockedCardState implements CardState {

    @Override
    public void withdraw(Card card, int amount) {
        System.err.println("Отправлена заявка в службу безопасности");
        throw new IllegalStateException("Карта заблокирована");
    }

    @Override
    public void deposit(Card card, int amount) {
        System.err.println("Отправлена заявка в службу безопасности");
        throw new IllegalStateException("Карта заблокирована");
    }
}
