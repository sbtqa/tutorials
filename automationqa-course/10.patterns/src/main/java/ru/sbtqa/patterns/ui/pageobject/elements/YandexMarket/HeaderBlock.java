package ru.sbtqa.patterns.ui.pageobject.elements.YandexMarket;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("Меню поиска")
@FindBy(xpath = "//div[@class='header2__main']")
public class HeaderBlock extends HtmlElement {

    private WebDriverWait wait = new WebDriverWait(PageFactory.getWebDriver(), 10);

    private static String savedTitle;

    public static void setSavedTitle(String title) {
        savedTitle = title;
    }

    public static String getSavedTitle() {
        return savedTitle;
    }

    @Name("Строка поиска")
    @FindBy(xpath = "//input[@id='header-search']")
    public TextInput searchString;

    @ActionTitle("выполняет поиск")
    public void startSearch(String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(searchString)));
        searchString.clear();
        searchString.sendKeys(text);
        searchString.sendKeys(Keys.ENTER);
    }

    public void searchSavedTitle(){
        startSearch(savedTitle);
    }
}