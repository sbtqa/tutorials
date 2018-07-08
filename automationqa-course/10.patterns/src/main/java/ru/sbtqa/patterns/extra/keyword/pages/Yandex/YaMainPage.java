package ru.sbtqa.patterns.extra.keyword.pages.Yandex;

import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static ru.sbtqa.tag.pagefactory.PageFactory.initElements;

@PageEntry(title = "Яндекс")
public class YaMainPage extends Page {

    @ElementTitle("Ссылка на Яндекс Маркет")
    @FindBy(xpath = ".//a[@data-id='market']")
    private Link yandexMarket;

    public YaMainPage() {
        initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

    @ActionTitle("переходит в")
    public void navigate_to(String dest) {
        switch (dest) {
            case "Яндекс Маркет":
                yandexMarket.click();
                break;
            default:
                throw new IllegalArgumentException(dest + " не определен!");
        }
    }
}
