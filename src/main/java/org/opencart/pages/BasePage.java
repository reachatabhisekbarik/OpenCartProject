package org.opencart.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
