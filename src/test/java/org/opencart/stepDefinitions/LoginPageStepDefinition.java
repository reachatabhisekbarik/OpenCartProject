package org.opencart.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.opencart.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import utils.CustomUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginPageStepDefinition {

    public WebDriver driver;
    public LoginPage loginPage;
    public Scenario scenario;


    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        String browserName = CustomUtilities.getConfigProperty("browser");
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new SafariDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        scenario.attach(fileContent, "image/png", "screenshot");
    }

    @Given("I am on the OpenCart login page")
    public void iAmOnTheOpenCartLoginPage() {

        scenario.log("Opened Login Page");
        scenario.log("Tag Names: \n" + scenario.getSourceTagNames().toString());
        scenario.log("getStatus:" + String.valueOf(scenario.getStatus()));


        String autUrl = CustomUtilities.getConfigProperty("url");
        driver.get(autUrl);
        loginPage = new LoginPage(driver);
    }

    @Given("I have entered a valid username and password")
    public void iHaveEnteredAValidUsernameAndPassword() {
        scenario.log("Entered valid creds");
        loginPage.enterEmail("qatestertest@gmail.com");
        loginPage.enterPassword("Test@123");
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        loginPage.clickOnLoginButton();
    }


    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        boolean isUserIsOnLoginPage = loginPage.verifyUserIsOnLoginPage();
        Assert.assertTrue(isUserIsOnLoginPage);
    }

    @Given("I have entered invalid {string} and {string}")
    public void iHaveEnteredInvalidAnd(String username, String password) {
        scenario.log("Provided Invalid credentials");
        String invalidEmail = CustomUtilities.generateRandomAlphanumeric(10) + "@gmail.com";
        String invalidPassword = CustomUtilities.generateRandomAlphanumeric(20);
        loginPage.enterEmail(invalidEmail);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickOnLoginButton();
    }

    @Then("I should see an error message indicating {string}")
    public void iShouldSeeAnErrorMessageIndicating(String string) {
        String errorMessage = loginPage.getInvalidLoginAlertMessage().getText();
        Assert.assertEquals(string, errorMessage);
    }

    @When("I click on the Forgotten Password link")
    public void iClickOnTheForgottenPasswordLink() {
        loginPage.clickOnForgotPasswordHyperLink();

    }

    @Then("I should be redirected to the password reset page")
    public void iShouldBeRedirectedToThePasswordResetPage() {
        String url = loginPage.clickOnForgotPasswordAndGetForgotPasswordUrl(driver);
        Assert.assertTrue(url.contains("account/forgotten"));

    }
}


