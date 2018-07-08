package ru.sbtqa.patterns.behavioural.state;

/**
 * @author Alexey Rumyantsev
 */
public class Card {
    private CardStates state;

    private volatile int balance;

    public Card() {
        this.balance = 0;
        this.state = CardStates.RELEASED;
    }

    public void withdraw(int amount) {
        System.out.println("Снятие наличных на сумму " + amount + " руб");
        state.getState().withdraw(this, amount);
    }

    public void deposit(int amount) {
        System.out.println("Пополнение счета на сумму " + amount + " руб");
        state.getState().deposit(this, amount);
    }

    public int getBalance() {
        return balance;
    }

    void setBalance(int balance) {
        this.balance = balance;
    }

    public void setState(CardStates state) {
        this.state = state;
    }
}
