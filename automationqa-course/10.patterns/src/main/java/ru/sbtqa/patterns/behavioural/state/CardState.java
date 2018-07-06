package ru.sbtqa.patterns.behavioural.state;

public interface CardState {
    void withdraw(Card card, int amount);

    void deposit(Card card, int amount);
}
