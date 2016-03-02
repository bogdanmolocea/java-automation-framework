package com.github.bogdanlivadariu.java.automation.framework.components.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Button component class.
 */
public class MenuComponent extends BaseComponent {

    private static Logger logger = LogManager.getLogger(MenuComponent.class);

    /**
     * Constructor of Button Component.
     * @param element
     */
    public MenuComponent(WebElement element) {
        super(element);
    }

    /**
     * Constructor of Button Component.
     * @param element
     * @param locator
     */
    public MenuComponent(WebElement element, By locator) {
        super(element, locator);
    }

    public void click(String item) {
        baseWebElement = waitForElement(ExpectedConditions.elementToBeClickable(baseWebElement));
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Clicking on " + locator);
        getItem(item).click();
    }

    private WebElement getItem(String item) {
        return baseWebElement.findElement(By.xpath(".//li//*[text()='" + item + "']"));
    }

}
