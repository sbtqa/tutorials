package ru.sbtqa.patterns.ui.pageobject.pages.yandexmarket;

import ru.sbtqa.patterns.ui.pageobject.elements.YandexMarket.HeaderBlock;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageEntry(title = "Яндекс Маркет", url = "https://market.yandex.ru")
public class YaMarketPage extends Page {

    @ElementTitle("Меню поиска")
    private HeaderBlock headerBlock;

    public YaMarketPage() {
        PageFactory.initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())),this);
    }
}
