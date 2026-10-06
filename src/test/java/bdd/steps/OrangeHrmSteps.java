package bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.LoggerUtil;

public class OrangeHrmSteps {

    private static final Logger logger = LoggerUtil.getLogger(OrangeHrmSteps.class);
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        logger.info("Step: I am on the login page");
        loginPage.goTo();
    }

    @When("User enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String username, String password) throws InterruptedException {
        loginPage.loginInToTheApplication(username, password);


    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
       loginPage.clickOnLoginButton();
    }


    @Then("User should be navigated to dashboard with url contains contains {string}")
    public void userShouldBeNavigatedToDashbaordWithUrlContainsContains(String urlText) {
        String currentURL = dashBoardPage.getCurrentUrl();
        Assert.assertTrue(currentURL.contains(urlText), "validating url text in the current url");
    }

//    @Then("User should see an error message as {string}")
//    public void userShouldSeeAnErrorMessageAs(String errorMessage) {
//
//    }

    @Then("User should see an error message")
    public void userShouldSeeAnErrorMessage() throws InterruptedException {
        boolean results = loginPage.isLoginErrorMessageDisplayed();
        Thread.sleep(2000);
        Assert.assertTrue(results, "Validating invalid credentials message.");
    }
}





