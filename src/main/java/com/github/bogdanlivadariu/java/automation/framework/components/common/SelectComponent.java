package com.github.bogdanlivadariu.java.automation.framework.components.common;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.github.bogdanlivadariu.java.automation.framework.components.interfaces.Selectable;

/**
 * Select component class.
 */
public class SelectComponent extends BaseComponent implements Selectable {

    private static Logger logger = LogManager.getLogger(SelectComponent.class);

    /**
     * Constructor of SelectComponent.
     * @param element
     */
    public SelectComponent(WebElement element) {
        super(element);
    }

    /**
     * Constructor of SelectComponent.
     * @param element
     * @param locator
     */
    public SelectComponent(WebElement element, By locator) {
        super(element, locator);
    }

    @Override
    public void selectByVisibleText(String text) {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        Select select = new Select(baseWebElement);
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Selecting by text" + text + " on " + locator);
        select.selectByVisibleText(text);

    }

    @Override
    public void selectByValue(String value) {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        Select select = new Select(baseWebElement);
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Selecting by value " + value + " on " + locator);
        select.selectByValue(value);
    }

    @Override
    public void selectByIndex(int index) {
        if (index != -1) {
            baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
            Select select = new Select(baseWebElement);
            String locator = getLocatorForWebElement(baseWebElement);
            logger.info("Selecting by index " + index + " on " + locator);
            select.selectByIndex(index);
        }
    }

    @Override
    public void deselectByIndex(int index) {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        Select select = new Select(baseWebElement);
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Deselecting by index " + index + " on " + locator);
        select.deselectByIndex(index);
    }

    @Override
    public boolean isOptionAvailable(String optionName) {
        for (WebElement selectOption : getSelectOptions()) {
            if ((new LabelComponent(selectOption)).getText().equals(optionName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public WebElement getFirstSelectedOption() {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        Select select = new Select(baseWebElement);
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Get selected text from: " + locator);
        return select.getFirstSelectedOption();
    }

    @Override
    public List<WebElement> getSelectOptions() {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        Select select = new Select(baseWebElement);
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Get selectable options from: " + locator);
        return select.getOptions();
    }
}
