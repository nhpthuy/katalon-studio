package com.kms.katalon.core.webui.common.internal;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.kms.katalon.core.logging.KeywordLogger;
import com.kms.katalon.core.webui.driver.DriverFactory;
import com.kms.katalon.core.webui.driver.SmartWaitWebDriver;
import com.kms.katalon.core.webui.driver.SmartWaitWebEventListener;

public class SmartWaitHelper {
    private static final KeywordLogger logger = KeywordLogger.getInstance(SmartWaitHelper.class);

    public static SmartWaitWebEventListener getEventListener() {
        return new SmartWaitWebEventListener();
    }

    public static SmartWaitWebDriver getSmartWaitWebDriver(WebDriver currentWebDriver) {
        return new SmartWaitWebDriver(currentWebDriver);
    }

    public static void doSmartWait() {
        WebDriver main = DriverFactory.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) main;
        try {
            js.executeAsyncScript(WAIT_AJAX_SCRIPT);
            js.executeAsyncScript(WAIT_DOM_SCRIPT);
        } catch (Exception e) {
            // Ignore exceptions for now
        }
    }

    private static String WAIT_AJAX_SCRIPT = "\tvar callback = arguments[arguments.length - 1].bind(this);\r\n\twindow.katalonWaiter.katalon_smart_waiter_do_ajax_wait(callback);";

    private static String WAIT_DOM_SCRIPT = "\tvar callback = arguments[arguments.length - 1].bind(this);\r\n\twindow.katalonWaiter.katalon_smart_waiter_do_dom_wait(callback);";
}
