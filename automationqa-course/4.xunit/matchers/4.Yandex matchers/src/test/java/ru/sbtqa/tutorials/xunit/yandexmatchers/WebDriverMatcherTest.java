package ru.sbtqa.tutorials.xunit.yandexmatchers;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import ru.sbtqa.tutorials.xunit.yandexmatchers.core.modules.DriverModule;
import ru.sbtqa.tutorials.xunit.yandexmatchers.core.pages.Google;
import ru.yandex.qatools.matchers.decorators.TimeoutWaiter;
import ru.yandex.qatools.matchers.webdriver.RefreshPageAction;
import ru.yandex.qatools.matchers.webdriver.TextMatcher;
import ru.yandex.qatools.matchers.webdriver.driver.CanFindElementMatcher;
import ru.yandex.qatools.matchers.webdriver.driver.HasTextMatcher;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;
import static ru.yandex.qatools.matchers.decorators.MatcherDecorators.should;
import static ru.yandex.qatools.matchers.decorators.MatcherDecorators.timeoutHasExpired;
import static ru.yandex.qatools.matchers.decorators.WaiterMatcherDecorator.decorateMatcherWithWaiter;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static ru.yandex.qatools.matchers.webdriver.EnabledMatcher.enabled;
import static ru.yandex.qatools.matchers.webdriver.ExistsMatcher.exists;
import static ru.yandex.qatools.matchers.webdriver.RefreshPageAction.pageRefresh;
import static ru.yandex.qatools.matchers.webdriver.TagNameMatcher.tagName;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;
import static ru.yandex.qatools.matchers.webdriver.driver.CanFindElementMatcher.canFindElement;
import static ru.yandex.qatools.matchers.webdriver.driver.HasTextMatcher.textOnCurrentPage;


/**
 * Примеры использования стандартных матчеров библиотеки Yandex-Matchers
 *
 * @author artsok
 */
@Guice(modules = {DriverModule.class})
public class WebDriverMatcherTest {

    @Inject
    private Google google;

    @Inject
    private WebDriver driver;

    @Inject
    private Actions actions;

    @BeforeMethod
    public void setUp() {
        google.goTo();
    }

    /**
     * Используем матчер should и displayed из библиотек Yandex-Matchers.
     * Матчер everyItem используем из Hamcrest Core
     */
    @Test
    public void shouldDisplayedGooglePageWithResult() {
        google.getSearchWidget().searchFor("Sberbank - Russian Commercial Bank");
        assertThat(google.getGoogleSearchResult().getResults(), should(everyItem(displayed())));
    }

    /**
     * Используем матчер decorateMatcherWithWaiter, матчер декоратор should и мачтеры: enabled(), exists(), tagName("div")
     * из библиотеки Yandex-Matchers
     * Матчер everyItem, allOf используем из библиотеки Hamcrest Core
     * <p>
     * В данном примере, проверяется список WebElement-ов, которые удовлетворяют определенным свойствам (См.описание используемых матчеров)
     * Если определенные условия не выполняются, то матчер-декоратор decorateMatcherWithWaiter(..) будет пытаться выполнить проверки
     * в заданный вами промежуток времени. В примере - 5 секунд
     */
    @Test
    public void shouldDisplayedPageWithResultWithDecorateMatcherWithWaiter() {
        google.getSearchWidget().searchFor("Sberbank");
        List<WebElement> resultList = google.getGoogleSearchResult().getResults();
        assertThat(resultList,
                decorateMatcherWithWaiter(
                        should(everyItem(allOf(enabled(), exists(), tagName("div")))),
                        timeoutHasExpired(5000)));
    }

    /**
     * Используем матчер-декоратор should и whileWaitingUntil из класса {@link ru.yandex.qatools.matchers.decorators.MatcherDecoratorsBuilder}
     * Матчеры everyItem, anyOf, containsString используем из библиотеки Hamcrest Core
     * Матчер text() используем {@link TextMatcher}
     * <p>
     * В данном примере, делаем поиск по тексту Sberbank-Technology и проверяем результат. Каждый {@link WebElement} проверяем,
     * что он содержит текст Sberbank/Сбербанк. Проверка будет пытаться выполнится в течение 5 секунд.
     * По истичению 5 секунд, тест упадет, если матчер отработал не корректно
     * <p>
     * В классе {@link TimeoutWaiter} есть статические методы для создания экземпляра класса.
     */
    @Test
    public void shouldDisplayedPageWithResultWithDecorateMatcherWithWaiterAnotherForm() {
        google.getSearchWidget().searchFor("Sberbank-Technology");
        List<WebElement> resultList = google.getGoogleSearchResult().getResults();
        assertThat(resultList,
                should(everyItem(
                        text(anyOf(
                                containsString("Sberbank"),
                                containsString("Сбербанк")))))
                        .whileWaitingUntil(new TimeoutWaiter(5000)));
    }

    /**
     * Негативный тест. Используем матчер-декоратор should и after {@link ru.yandex.qatools.matchers.decorators.MatcherDecoratorsBuilder}
     * В after(...) передаем {@link ru.yandex.qatools.matchers.decorators.Action}, а именно {@link RefreshPageAction}, т.е
     * находим вебэлемент {@link WebElement} и после обновления страницы проверяем, что он должен быть на странице.
     */
    @Test
    public void shouldDisplayedPageWithResultWithActionMatcherDecorator() {
        google.getSearchWidget().searchFor("TAG");
        assertThat(driver.findElement(By.className("rc")),
                should(exists()).after(pageRefresh(driver)));
    }

    /**
     * Проверка, что на странице присутствует вебэлемент.
     * Используется матчер canFindElement(...), класса {@link CanFindElementMatcher}
     */
    @Test
    public void shouldFindElementOnPage() {
        google.getSearchWidget().searchFor("Selenoid");
        assertThat(driver, canFindElement(By.xpath("//a[contains(., 'Selenoid - Aerokube')]")));
    }


    /**
     * Проверка, что на странице присутсвует текст "Automation". Работает по принципу matcher.matches(wd.getPageSource()
     * Используется матчер декоратор should из {@link ru.yandex.qatools.matchers.decorators.MatcherDecoratorsBuilder},
     * матчер textOnCurrentPage(...) из {@link HasTextMatcher}, матчер-декоратор is(...) из {@link org.hamcrest.Matchers}
     */
    @Test
    public void shouldFindTextOnCurrentPage() {
        google.getSearchWidget().searchFor("Automation");
        assertThat(driver, should(textOnCurrentPage(is(containsString("Automation")))));
    }

    /**
     *
     * Требует подключенного Junit 4
     * Проверка, что условие (матчер, как аргумент в методе inCase) будет выполнен.
     * Дальше если пред-условие выполняется, выполняется основной матчер everyItem(exists()), если нет, то everyItem(exists()) - не выполняется
     */
    @Test
    public void conditionMatcherDecoratorShouldWork() throws InterruptedException {
        google.getSearchWidget().searchFor("Allure");
        List<WebElement> resultList = google.getGoogleSearchResult().getResults();
        WebElement element = driver.findElement(By.xpath("//a[contains(text(), 'Войти')]"));
        assertThat(resultList, should(everyItem(exists())).inCase(element, notNullValue()));
    }


    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


