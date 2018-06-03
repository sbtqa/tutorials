package ru.sbtqa.patterns.behavioural;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sbtqa.patterns.behavioural.strategy.LoginStrategies;
import ru.sbtqa.patterns.behavioural.strategy.old.ApiLoginStrategy;
import ru.sbtqa.patterns.behavioural.strategy.old.LoginStrategy;

/**
 * @author Alexey Rumyantsev
 */
public class StrategyTest {

    @Test
    public void strategyTest() {
        LoginStrategy strategy = new ApiLoginStrategy();
        strategy.login(new ChromeDriver());
    }

    @Test
    public void lambdaStrategyTest() {
        LoginStrategy strategy = LoginStrategies::apiLogin;
        strategy.login(new ChromeDriver());
    }
}
