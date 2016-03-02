package com.github.bogdanlivadariu.java.automation.framework.components.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.bogdanlivadariu.java.automation.framework.components.interfaces.Checkable;

/**
 * Component for CheckBox.
 */
public class CheckBoxComponent extends BaseComponent implements Checkable {

    private static Logger logger = LogManager.getLogger(CheckBoxComponent.class);

    /**
     * Constructor for component.
     * @param element
     */
    public CheckBoxComponent(WebElement element) {
        super(element);
    }

    /**
     * Constructor for the component.
     * @param element
     * @param locator
     */
    public CheckBoxComponent(WebElement element, By locator) {
        super(element, locator);
    }

    @Override
    public boolean isChecked() {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Verifies if " + locator + " is checked");
        return baseWebElement.isSelected();
    }

    @Override
    public void check() {
        if (!isChecked()) {
            waitForElement();
            String locator = getLocatorForWebElement();
            logger.info("Set the " + locator + " to checked");
            baseWebElement.click();
        }
    }

    @Override
    public void unCheck() {
        if (isChecked()) {
            waitForElement();
            String locator = getLocatorForWebElement();
            logger.info("Set the " + locator + " to unchecked");
            baseWebElement.click();
        }
    }
}
