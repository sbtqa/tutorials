package ru.sbtqa.patterns.behavioural.state;

public class ActiveCardState implements CardState {
    @Override
    public void withdraw(int amount) {
        System.out.println("Операция выполнена успешно");
    }

    @Override
    public void deposit(int amount) {
        System.out.println("Операция выполнена успешно");
    }
}
