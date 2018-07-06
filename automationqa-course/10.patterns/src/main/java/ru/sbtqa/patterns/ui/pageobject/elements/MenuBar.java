package ru.sbtqa.patterns.ui.pageobject.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

@Name("Меню поиска")
@FindBy(xpath = "//div[@class='navigation__region']")
public class MenuBar {

    @FindBy(xpath = ".//span[text()='Все']")
    private WebElement all;

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
            case "Все":
                all.click();
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
