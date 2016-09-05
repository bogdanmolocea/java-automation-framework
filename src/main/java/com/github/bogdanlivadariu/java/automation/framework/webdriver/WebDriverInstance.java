package com.github.bogdanlivadariu.java.automation.framework.webdriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Used to initialize/persist sessions of {@link WebDriver}
 */
public class WebDriverInstance {

    private static Logger logger = LogManager.getLogger(WebDriverInstance.class);

    private static WebDriver driver = null;

    /**
     * Call this method to get the driver instance.
     * @return {@link WebDriver}
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            logger.error("WebDriver instance has not been set, "
                + "make sure you use the setter right after the driver is initialized");
            throw new NullPointerException("Webdriver instance is null, make sure you "
                + "use the setter right after the driver instance is created.");
        }
        return driver;
    }

    /**
     * Use this method to set the driver instance.
     * @param driver {@link WebDriver}
     */
    public static void setDriver(WebDriver driver) {
        WebDriverInstance.driver = driver;
        logger.info("WebDriver instance has been set.");
    }
}
