package ru.sbtqa.patterns.extra.keyword.pages.Yandex;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.datajack.Stash;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static ru.sbtqa.tag.pagefactory.PageFactory.initElements;

@PageEntry(title = "Описание товара")
public class ProductDescrPage extends Page {

    private WebDriverWait wait = new WebDriverWait(PageFactory.getWebDriver(), 10);

    @FindBy(xpath = "//div[@class='n-title__text']/h1")
    private TextBlock title;

    public ProductDescrPage() {
        initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

    @ActionTitle("проверяет на соответствие запомненному значению")
    public void checkSavedTitle() {
        wait.until(visibilityOf(title));
        assertThat(title.getText(), startsWith(Stash.getValue("title")));
    }
}
