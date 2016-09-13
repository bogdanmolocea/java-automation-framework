package com.github.bogdanlivadariu.java.automation.framework.components.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.github.bogdanlivadariu.java.automation.framework.webdriver.WebDriverInstance;

public class BaseComponent {

    private static Logger logger = LogManager.getLogger(BaseComponent.class);

    private static int TIMEOUT_SECONDS = 60;

    private static int POOLING_PERIOD = 1;

    protected WebElement baseWebElement;

    /**
     * Constructor of BaseComponentClass.
     * @param element {@link WebElement}
     */
    public BaseComponent(WebElement element) {
        this.baseWebElement = element;
    }

    /**
     * Constructor of BaseComponentClass.
     * @param element {@link WebElement}
     * @param locator {@link By}
     */
    public BaseComponent(WebElement element, By locator) {
        this.baseWebElement = getDescendant(element, locator);
    }

    /**
     * Searches for a Descendant.
     * @param descendantLocator {@link By}
     * @return {@link WebElement}
     */
    public WebElement getDescendant(final By descendantLocator) {
        WebElement descendantFound = null;
        baseWebElement = getFluentWait().until(ExpectedConditions.visibilityOf(baseWebElement));
        try {
            String parentLocator = getLocatorForWebElement();
            logger.info("Looking in parent: " + parentLocator + " for descendant: " + descendantLocator);
            descendantFound = baseWebElement.findElement(descendantLocator);
        } catch (NoSuchElementException e) {
            logger.error("Exception when searching for descendant: " + descendantLocator, e);
            return descendantFound;
        }
        return descendantFound;
    }

    /**
     * Searches for all Descendants.
     * @param descendantsLocator {@link By}
     * @return {@link List} of {@link WebElement}
     */
    public List<WebElement> getDescendants(final By descendantsLocator) {
        List<WebElement> descendantsFound = null;
        try {
            String parentLocator = getLocatorForWebElement();
            logger.info("Looking in parent: " + parentLocator + " for descendants: " + descendantsLocator);
            WebElement parentFound = getFluentWait().until(ExpectedConditions.visibilityOf(baseWebElement));
            descendantsFound = parentFound.findElements(descendantsLocator);
        } catch (NoSuchElementException e) {
            logger.error("Exception when searching for descendants: " + descendantsLocator, e);
            return descendantsFound;
        }
        return descendantsFound;
    }

    /**
     * Searches for a Descendant in the provided parent.
     * @param parent {@link WebElement}
     * @param descendantLocator {@link By}
     * @return {@link WebElement}
     */
    public WebElement getDescendant(final WebElement parent, final By descendantLocator) {
        WebElement descendantFound = null;
        try {
            String parentLocator = getLocatorForWebElement(parent);
            logger.info("Looking in parent: " + parentLocator + " for descendant: " + descendantLocator);
            WebElement parentFound = getFluentWait().until(ExpectedConditions.visibilityOf(parent));
            descendantFound = parentFound.findElement(descendantLocator);
        } catch (NoSuchElementException e) {
            logger.error("Exception when searching for descendant: " + descendantLocator, e);
            return descendantFound;
        }
        return descendantFound;
    }

    /**
     * Extracts the value of an attribute.
     * @param attributeName {@link String}
     * @return {@link String} representing the value of the the attributeName
     */
    public String getAttributeValue(String attributeName) {
        baseWebElement = getFluentWait().until(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement();
        logger.info("Get attribute : " + attributeName + " from locator: " + locator);
        return baseWebElement.getAttribute(attributeName);
    }

    /**
     * Returns the text of the webElement.
     * @return {@link String}
     */
    public String getText() {
        String returnedText;
        try {
            baseWebElement = getFluentWait().until(ExpectedConditions.visibilityOf(baseWebElement));
            String locator = getLocatorForWebElement();
            logger.info("Get Text from: " + locator);
            returnedText = baseWebElement.getText();
        } catch (Exception e) {
            logger.error("Exception on .getTextFromWebElement()", e);
            returnedText = "";
        }
        return returnedText;
    }

    /**
     * Checks if the webElement is displayed.
     * @return {@link Boolean}
     */
    public boolean isDisplayed() {
        boolean found;
        try {
            String locator = getLocatorForWebElement();
            logger.info("Check if " + locator + " is displayed ");
            found = getCurrentWebElement().isDisplayed();
        } catch (Exception e) {
            found = false;
            logger.error("Exception on .isDisplayed()", e);
        }
        return found;
    }

    /**
     * This method returns the enabled state of a WebElement.
     * @return {@link Boolean}
     */
    public Boolean isEnabled() {
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Verify that the WebElement " + locator + "is enabled");
        return baseWebElement.isEnabled();
    }

    /**
     * Send keys to the WebElement.
     * @param keys {@link Keys}
     */
    public void sendKeys(Keys keys) {
        // delay();
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Sending to: " + locator + " keys: " + keys);
        baseWebElement.sendKeys(keys);
    }

    /**
     * Send text to the WebElement.
     * @param text {@link String}
     */
    public void sendKeys(String text) {
        // delay();
        baseWebElement = waitForElement(ExpectedConditions.visibilityOf(baseWebElement));
        String locator = getLocatorForWebElement(baseWebElement);
        logger.info("Sending to: " + locator + " text: " + text);
        baseWebElement.sendKeys(text);
    }

    /**
     * Get the parent of the current WebElement.
     * @return {@link WebElement}
     */
    public WebElement getParent() {
        return getDescendant(By.xpath(".."));
    }

    /**
     * Get the current instance of the WebElement.
     * @return {@link WebElement}
     */
    public WebElement getCurrentWebElement() {
        return baseWebElement;
    }

    /**
     * Wait for the webElement to be visible given an expected condition.
     * @param cond {@link ExpectedCondition}
     * @return {@link WebElement}
     */
    public WebElement waitForElement(ExpectedCondition< ? > cond) {
        return (WebElement) getFluentWait().until(cond);
    }

    /**
     * Waits for the webElement to be visible.
     */
    public void waitForElement() {
        getFluentWait().until(ExpectedConditions.visibilityOf(baseWebElement));
    }

    private Wait<WebDriver> getFluentWait() {
        Wait<WebDriver> wait = new FluentWait<>(WebDriverInstance.getDriver())
            .withTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).pollingEvery(POOLING_PERIOD, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
            .ignoring(StaleElementReferenceException.class);
        return wait;
    }

    /**
     * Extracts the locator used to identify the webElement in the DOM.
     * @param element {@link WebElement}
     * @return {@link String}
     */
    protected String getLocatorForWebElement(WebElement element) {
        if (element == null) {
            return "Element is null";
        }
        return element.toString().substring(element.toString().indexOf("->"), element.toString().length());
    }

    /**
     * Extracts the locator used to identify the webElement in the DOM.
     * @return {@link String}
     */
    protected String getLocatorForWebElement() {
        if (baseWebElement == null) {
            return "Element is null";
        }
        String locator;
        try {
            locator = baseWebElement.toString().substring(baseWebElement.toString().indexOf("->"),
                baseWebElement.toString().length());
        } catch (Exception e) {
            logger.catching(e);
            locator = "Element might no longer be present in the page";
        }
        return locator;
    }

    /**
     * Scrolls the component into view on top of the page.
     */
    public void scrollIntoView() {
        scrollIntoView(true);
    }

    /**
     * Scroll component into view based on the param.
     * @param scrollTop true to scroll element on top of the page, false to scroll it on the bottom of the page
     */
    public void scrollIntoView(boolean scrollTop) {
        String script = String.format("return arguments[0].scrollIntoView(%s);", scrollTop);
        ((JavascriptExecutor) WebDriverInstance.getDriver()).executeScript(script, getCurrentWebElement());
    }

    /**
     * Checks if the element is present in the DOM
     * @return true if element is present in the DOM, false otherwise
     */
    public Boolean isPresent() {
        try {
            if (baseWebElement.isDisplayed()) {
                return true;
            } else if (baseWebElement.getSize().getHeight() > 0 && baseWebElement.getSize().getWidth() > 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
