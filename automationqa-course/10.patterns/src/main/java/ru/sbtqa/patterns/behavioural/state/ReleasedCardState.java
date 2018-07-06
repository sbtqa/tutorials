package ru.sbtqa.patterns.behavioural.state;

/**
 * @author Alexey Rumyantsev
 */
public class ReleasedCardState implements CardState {

    @Override
    public void withdraw(Card card, int amount) {
        throw new IllegalStateException("Карта не активирована");
    }

    @Override
    public void deposit(Card card, int amount) {
        throw new IllegalStateException("Карта не активирована");
    }
}
