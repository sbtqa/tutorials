package ru.sbtqa.patterns.behavioural.state;

public class BlockedCardState implements CardState {
    @Override
    public void withdraw(int amount) {
        System.err.println("Отправлена заявка в службу безопасности");
        throw new IllegalStateException("Карта заблокирована");
    }

    @Override
    public void deposit(int amount) {
        System.err.println("Отправлена заявка в службу безопасности");
        throw new IllegalStateException("Карта заблокирована");
    }
}
