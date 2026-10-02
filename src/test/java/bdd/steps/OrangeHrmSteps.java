package bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import pages.LoginPage;
import utils.LoggerUtil;

public class OrangeHrmSteps {

    private static final Logger logger = LoggerUtil.getLogger(OrangeHrmSteps.class);
    LoginPage loginPage = new LoginPage();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        logger.info("Step: I am on the login page");
        loginPage.goTo();
    }

    @When("User enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String username, String password) {
        loginPage.loginInToTheApplication(username, password);


    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
       loginPage.clickOnLoginButton();
    }


}





