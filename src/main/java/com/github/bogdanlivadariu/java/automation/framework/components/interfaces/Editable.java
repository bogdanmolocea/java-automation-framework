package com.github.bogdanlivadariu.java.automation.framework.components.interfaces;

/**
 * Interface for editable components.
 */
public interface Editable {

    /**
     * Set text action.
     * @param text
     */
    void setText(String text);

    /**
     * Append text action.
     * @param text
     */
    void appendText(String text);

    /**
     * Clear text action.
     */
    void clearText();
}
