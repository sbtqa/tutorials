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
        Card visa = new Card();
        visa.setState(CardStates.ACTIVE);
        visa.deposit(1000);
        visa.setState(CardStates.BLOCKED);
        visa.withdraw(500);
    }
}
