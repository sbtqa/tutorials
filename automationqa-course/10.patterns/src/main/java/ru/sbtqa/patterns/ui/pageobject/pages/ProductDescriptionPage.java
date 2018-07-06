package ru.sbtqa.patterns.ui.pageobject.pages.yandexmarket;

import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.patterns.ui.pageobject.elements.YandexMarket.HeaderBlock;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageEntry(title = "Описание товара")
public class ProductDescriptionPage extends Page {

    private WebDriverWait wait = new WebDriverWait(PageFactory.getWebDriver(), 10);

    @FindBy(xpath = "//div[@class='n-title__text']/child::h1")
    private TextBlock title;

    public ProductDescriptionPage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())),this);
    }

    @ActionTitle("проверяет на соответствие запомненному значению")
    public void checkSavedTitle(){
        wait.until(ExpectedConditions.elementToBeClickable(title));
        if (!HeaderBlock.getSavedTitle().equals(title.getText())){
            Assert.fail("Наименование товара не соответствует запомненному значению\n\t'" + title.getText()+ "' != '" + HeaderBlock.getSavedTitle()+"'");
        }
    }
}
