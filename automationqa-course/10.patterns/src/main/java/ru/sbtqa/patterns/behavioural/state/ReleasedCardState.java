package ru.sbtqa.patterns.behavioural.state;

/**
 * @author Alexey Rumyantsev
 */
public class ReleasedCardState implements CardState {

    @Override
    public void withdraw(int amount) {
        throw new IllegalStateException("Карта не активирована");
    }

    @Override
    public void deposit(int amount) {
        throw new IllegalStateException("Карта не активирована");
    }
}
