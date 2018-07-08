package ru.sbtqa.patterns.behavioural.state;

public class ExpiredCardState implements CardState {
    @Override
    public void withdraw(Card card, int amount) {
        throw new IllegalStateException("Карта просрочена");
    }

    @Override
    public void deposit(Card card, int amount) {
        throw new IllegalStateException("Карта просрочена");
    }
}
