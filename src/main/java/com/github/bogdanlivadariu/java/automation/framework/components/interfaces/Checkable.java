package com.github.bogdanlivadariu.java.automation.framework.components.interfaces;

/**
 * Interface for Checkable components.
 */
public interface Checkable {

    /**
     * Verifies if the component is checked.
     * @return
     */
    boolean isChecked();

    /**
     * Marks the check box as checked.
     */
    void check();

    /**
     * Marks the check box as unchecked.
     */
    void unCheck();
}
