package ru.sbtqa.patterns.behavioural.state;

public class ExpiredCardState implements CardState {
    @Override
    public void withdraw(int amount) {
        throw new IllegalStateException("Карта просрочена");
    }

    @Override
    public void deposit(int amount) {
        throw new IllegalStateException("Карта просрочена");
    }
}
