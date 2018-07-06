package ru.sbtqa.patterns.extra.keyword.elements.YandexMarket;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("Меню поиска")
@FindBy(xpath = "//div[@class='header2__main']")
public class HeaderBlock extends HtmlElement {

    @Name("Строка поиска")
    @FindBy(xpath = "//input[@id='header-search']")
    private TextInput searchBar;

    @ActionTitle("выполняет поиск")
    public void search(String text) {
        searchBar.clear();
        searchBar.sendKeys(text);
        searchBar.sendKeys(Keys.ENTER);
    }
}