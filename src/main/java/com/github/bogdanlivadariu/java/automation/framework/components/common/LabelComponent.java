package com.github.bogdanlivadariu.java.automation.framework.components.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Label component class.
 */
public class LabelComponent extends BaseComponent {

    /**
     * Constructor for component.
     * @param element
     */
    public LabelComponent(WebElement element) {
        super(element);
    }

    /**
     * Constructor for component.
     * @param element
     * @param locator
     */
    public LabelComponent(WebElement element, By locator) {
        super(element, locator);
    }
}
