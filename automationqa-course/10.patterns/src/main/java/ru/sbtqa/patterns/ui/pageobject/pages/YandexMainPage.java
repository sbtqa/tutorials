package ru.sbtqa.patterns.ui.pageobject.pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@PageEntry(title = "Яндекс")
public class YandexMainPage extends Page {

    @FindBy(id = "text")
    private TextInput search;

    @FindBy(xpath = ".//a[text()='Маркет']")
    private Link yandexMarket;

    public YandexMainPage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

    @ActionTitle("переходит в")
    public void navigate_to(String dest) {
        switch (dest) {
            case "Яндекс Маркет":
                yandexMarket.click();
                return;
            default:
                Assert.fail(dest + " не определено");
                break;
        }
    }

    public void search(String param) {
        search.sendKeys(param);
        search.sendKeys(Keys.ENTER);
    }
}
