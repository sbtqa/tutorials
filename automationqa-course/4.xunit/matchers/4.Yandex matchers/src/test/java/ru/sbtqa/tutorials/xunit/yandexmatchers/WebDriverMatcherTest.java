package ru.sbtqa.tutorials.xunit.yandexmatchers;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import ru.sbtqa.tutorials.xunit.yandexmatchers.core.modules.DriverModule;
import ru.sbtqa.tutorials.xunit.yandexmatchers.core.pages.Google;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Every.everyItem;
import static ru.yandex.qatools.matchers.decorators.MatcherDecorators.should;
import static ru.yandex.qatools.matchers.decorators.MatcherDecorators.timeoutHasExpired;
import static ru.yandex.qatools.matchers.decorators.WaiterMatcherDecorator.decorateMatcherWithWaiter;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static ru.yandex.qatools.matchers.webdriver.EnabledMatcher.enabled;
import static ru.yandex.qatools.matchers.webdriver.ExistsMatcher.exists;
import static ru.yandex.qatools.matchers.webdriver.TagNameMatcher.tagName;


/**
 * Примеры использования стандартных матчеров библиотеки Yandex (Webdriver Matchers)
 */
@Guice(modules = {DriverModule.class})
public class WebDriverMatcherTest {

    @Inject
    private Google google;

    @Inject
    private WebDriver driver;

    /**
     * Используем матчер should и displayed из библиотек Yandex-Matchers.
     * Матчер everyItem используем из Hamcrest Core
     */
    @Test
    public void shouldDisplayedGooglePageWithResult() {
        google.goTo();
        google.getSearchWidget().searchFor("Sberbank - Russian Commercial Bank");
        assertThat(google.getGoogleSearchResult().getResults(), should(everyItem(displayed())));
    }

    /**
     * Используем матчер decorateMatcherWithWaiter, матчер декоратор should и мачтеры: enabled(), exists(), tagName("div")
     * из библиотеки Yandex-Matchers
     * Матчер everyItem, allOf используем из библиотеки Hamcrest Core
     *
     * В данном примере, проверяется список WebElement-ов, которые удовлетворяют определенным свойствам (См.описание используемых матчеров)
     * Если определенные условия не выполняются, то матчер-декоратор decorateMatcherWithWaiter(..) будет пытаться выполнить проверки
     * в заданный вами промежуток времени. В примере - 5 секунд
     */
    @Test
    public void shouldDisplayedPageWithResultWithDecorateMatcherWithWaiter() {
        google.goTo();
        google.getSearchWidget().searchFor("Sberbank");
        List<WebElement> resultList = google.getGoogleSearchResult().getResults();
        assertThat(resultList,
                decorateMatcherWithWaiter(
                        should(everyItem(allOf(enabled(), exists(), tagName("div")))),
                        timeoutHasExpired(5000)));
    }


    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


