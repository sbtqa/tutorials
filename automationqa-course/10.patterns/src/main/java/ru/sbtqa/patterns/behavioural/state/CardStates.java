package ru.sbtqa.patterns.behavioural.state;

/**
 * @author Alexey Rumyantsev
 */
public enum CardStates {
    RELEASED(new ReleasedCardState()),
    ACTIVE(new ActiveCardState()),
    BLOCKED(new BlockedCardState()),
    EXPIRED(new ExpiredCardState());

    private CardState state;

    CardStates(CardState state) {
        this.state = state;
    }

    public CardState getState() {
        return state;
    }
}
