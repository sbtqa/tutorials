package ru.sbtqa.patterns.behavioural;

import org.testng.annotations.Test;
import ru.sbtqa.patterns.behavioural.state.Card;
import ru.sbtqa.patterns.behavioural.state.CardStates;

/**
 * @author Alexey Rumyantsev
 */
public class StateTest {
    @Test
    public void test(){
        Card card = new Card();
        card.setState(CardStates.ACTIVE);
        card.deposit(1000);
        card.setState(CardStates.BLOCKED);
        card.withdraw(500);
    }
}
