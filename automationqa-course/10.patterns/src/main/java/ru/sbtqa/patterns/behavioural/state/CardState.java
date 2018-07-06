package ru.sbtqa.patterns.behavioural.state;

public interface CardState {
    void withdraw(int amount);

    void deposit(int amount);
}
