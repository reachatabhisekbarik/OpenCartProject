package org.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {


    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;

    @FindBy(linkText = "Forgotten Password")
    private WebElement forgottenPassword;

    @FindBy(linkText = "Logout")
    private WebElement logout;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement invalidLoginAlertMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getInvalidLoginAlertMessage() {
        return invalidLoginAlertMessage;
    }

    public void setInvalidLoginAlertMessage(WebElement invalidLoginAlertMessage) {
        this.invalidLoginAlertMessage = invalidLoginAlertMessage;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public void setEmailInput(WebElement emailInput) {
        this.emailInput = emailInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(WebElement passwordInput) {
        this.passwordInput = passwordInput;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(WebElement loginButton) {
        this.loginButton = loginButton;
    }

    public WebElement getForgottenPassword() {
        return forgottenPassword;
    }

    public void setForgottenPassword(WebElement forgottenPassword) {
        this.forgottenPassword = forgottenPassword;
    }

    public WebElement getLogout() {
        return logout;
    }

    public void setLogout(WebElement logout) {
        this.logout = logout;
    }


    public void enterEmail(String email) {
        this.getEmailInput().sendKeys(email);
    }

    public void enterPassword(String password) {
        this.getPasswordInput().sendKeys(password);
    }

    public void clickOnLoginButton() {
        this.getLoginButton().click();
    }

    public void clickOnForgotPasswordHyperLink() {
        this.getForgottenPassword().click();
    }

    public void clickOnLogout() {
        this.getLogout().click();
    }

    public boolean verifyUserIsOnLoginPage() {

        //logout link is displayed
        return this.getLogout().isDisplayed();
    }

    public boolean isValidLoginAlertMessageDisplayed() {
        return this.getInvalidLoginAlertMessage().isDisplayed();
    }

    public String clickOnForgotPasswordAndGetForgotPasswordUrl(WebDriver driver) {
        this.clickOnForgotPasswordHyperLink();
        return driver.getCurrentUrl();
    }

}