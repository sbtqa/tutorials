package ru.sbtqa.patterns.structural.decorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Alexey Rumyantsev
 */
public class ConcreteButton implements Button {
    @FindBy(xpath = "//button")
    WebElement element;

    public ConcreteButton() {
        //init element
    }

    @Override
    public void click() {
        System.out.println("Clicked");
    }
}
