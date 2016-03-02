package com.github.bogdanlivadariu.java.automation.framework.components.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.bogdanlivadariu.java.automation.framework.components.interfaces.Clickable;
import com.github.bogdanlivadariu.java.automation.framework.webdriver.WebDriverInstance;

/**
 * Button component class.
 */
public class ButtonComponent extends BaseComponent implements Clickable {

    private static Logger logger = LogManager.getLogger(ButtonComponent.class);

    /**
     * Constructor of Button Component.
     * @param element
     */
    public ButtonComponent(WebElement element) {
        super(element);
    }

    /**
     * Constructor of Button Component.
     * @param element
     * @param locator
     */
    public ButtonComponent(WebElement element, By locator) {
        super(element, locator);
    }

    @Override
    public void click() {
        baseWebElement = waitForElement(ExpectedConditions.elementToBeClickable(baseWebElement));
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Clicking on " + locator);
        baseWebElement.click();
    }

    @Override
    public Object getLandingPage() throws Exception {
        throw new Exception("You should Override this method when ButtonComponent is instantiated");
    }

    @Override
    public void doubleClick() {
        Actions action = new Actions(WebDriverInstance.getDriver());
        baseWebElement = waitForElement(ExpectedConditions.elementToBeClickable(baseWebElement));
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Double click on " + locator);
        action.doubleClick(baseWebElement).perform();
    }
}
