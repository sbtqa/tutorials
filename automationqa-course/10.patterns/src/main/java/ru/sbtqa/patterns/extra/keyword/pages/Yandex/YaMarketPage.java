package ru.sbtqa.patterns.extra.keyword.pages.Yandex;

import ru.sbtqa.patterns.extra.keyword.elements.YandexMarket.HeaderBlock;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static ru.sbtqa.tag.pagefactory.PageFactory.initElements;

@PageEntry(title = "Яндекс Маркет")
public class YaMarketPage extends Page {

    @ElementTitle("Меню поиска")
    private HeaderBlock headerBlock;

    public YaMarketPage() {
        initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }
}
