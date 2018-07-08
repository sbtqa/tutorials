package ru.sbtqa.patterns.extra.keyword.elements.YandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class ProductCard extends TypifiedElement {

    public ProductCard(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getProductName() {
        return getWrappedElement()
                .findElement(By.xpath(".//a[contains(@class,'n-link_theme_blue')]"))
                .getText();
    }
}
