package ru.sbtqa.patterns.behavioural.state;

/**
 * @author Alexey Rumyantsev
 */
public class Card {
    private CardState state;

    private int balance;

    public Card() {
        this.balance = 0;
        this.state = new ReleasedCardState();
    }

    public void withdraw(int amount) {
        System.out.println("Снятие наличных на сумму " + amount + " руб");
        state.withdraw(amount);
    }

    public void deposit(int amount) {
        System.out.println("Пополнение счета на сумму " + amount + " руб");
        state.deposit(amount);
    }

    public void setState(CardStates state) {
        switch (state) {
            case ACTIVE:
                this.state = new ActiveCardState();
                break;
            case BLOCKED:
                this.state = new BlockedCardState();
                break;
            case EXPIRED:
                this.state = new ExpiredCardState();
                break;
            case RELEASED:
                this.state = new ReleasedCardState();
                break;
        }
    }
}
