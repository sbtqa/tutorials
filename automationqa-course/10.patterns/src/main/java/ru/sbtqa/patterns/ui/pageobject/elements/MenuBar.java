package ru.sbtqa.patterns.ui.pageobject.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Меню поиска")
@FindBy(xpath = "//div[@class='navigation__region']")
public class MenuBar extends HtmlElement {

    @FindBy(xpath = ".//span[text()='Поиск']")
    private WebElement search;

    @FindBy(xpath = ".//span[text()='Картинки']")
    private WebElement pictures;

    @FindBy(xpath = ".//span[text()='Видео']")
    private WebElement video;

    @FindBy(xpath = ".//span[text()='Новости']")
    private WebElement news;

    @FindBy(xpath = ".//span[text()='Карты']")
    private WebElement maps;

    public void chooseFilter(String filter) {
        switch (filter) {
            case "Поиск":
                search.click();
                break;
            case "Картинки":
                pictures.click();
                break;
            case "Видео":
                video.click();
                break;
            case "Новости":
                news.click();
                break;
            case "Карты":
                maps.click();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный фильтр поиска");
        }
    }
}
