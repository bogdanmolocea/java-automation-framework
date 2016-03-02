package com.github.bogdanlivadariu.java.automation.framework.components.interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * Interface for Selectable components.
 */
public interface Selectable {

    /**
     * Select by visible text action.
     * @param text
     */
    void selectByVisibleText(String text);

    /**
     * Select by value action.
     * @param value
     */
    void selectByValue(String value);

    /**
     * Select by index action.
     * @param index
     */
    void selectByIndex(int index);

    /**
     * Extracts the first selected option.
     * @return
     */
    WebElement getFirstSelectedOption();

    /**
     * Verifies if the option exists in the select component.
     * @param optionName
     * @return
     */
    boolean isOptionAvailable(String optionName);

    /**
     * Deselects an option from a multiple select.
     * @param index
     */
    void deselectByIndex(int index);

    /**
     * Extracts all options from the drop-down.
     * @return
     */
    List< ? > getSelectOptions();
}
