package com.github.bogdanlivadariu.java.automation.framework.components.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.bogdanlivadariu.java.automation.framework.components.interfaces.Editable;

/**
 * Component for Input Field.
 */
public class InputFieldComponent extends BaseComponent implements Editable {

    private static Logger logger = LogManager.getLogger(InputFieldComponent.class);

    /**
     * Constructor for component.
     * @param webElement
     */
    public InputFieldComponent(WebElement webElement) {
        super(webElement);
    }

    /**
     * Constructor for component.
     * @param webElement
     * @param locator
     */
    public InputFieldComponent(WebElement webElement, By locator) {
        super(webElement, locator);
    }

    @Override
    public void appendText(String text) {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement();
        logger.info("Typing " + text + " text on " + locator);
        sendKeys(text);
    }

    @Override
    public void setText(String text) {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement();
        logger.info("Typing " + text + " text on " + locator);
        baseWebElement.clear();
        sendKeys(text);
    }

    @Override
    public void clearText() {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement();
        logger.info("Clear Text from: " + locator);
        baseWebElement.clear();
    }

    @Override
    public String getText() {
        return getAttributeValue("value");
    }
}
