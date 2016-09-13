package com.github.bogdanlivadariu.java.automation.framework.components.interfaces;

/**
 * Interface for Clickable components.
 */
public interface Clickable {

    /**
     * Click action.
     */
    void click();

    /**
     * Double click action.
     */
    void doubleClick();

    /**
     * In case the action redirects you to a new page, this method can be override to return the redirected page.
     * @return {@link Object}
     * @throws Exception
     */
    Object getLandingPage() throws Exception;
}
