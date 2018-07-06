package ru.sbtqa.patterns.behavioural;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sbtqa.patterns.behavioural.strategy.LoginStrategies;
import ru.sbtqa.patterns.behavioural.strategy.old.ApiLoginStrategy;
import ru.sbtqa.patterns.behavioural.strategy.old.LoginStrategy;

/**
 * @author Alexey Rumyantsev
 */
class StrategyTest {

    private LoginStrategy strategy;

    @Test
    void strategyTest() {
        strategy = new ApiLoginStrategy();
        strategy.login(new ChromeDriver());
    }

    @Test
    void lambdaStrategyTest() {
        strategy = LoginStrategies::formLogin;
        strategy.login(new ChromeDriver());
    }
}
