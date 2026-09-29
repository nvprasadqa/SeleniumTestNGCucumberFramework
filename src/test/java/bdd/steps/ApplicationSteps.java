package bdd.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.HomePage;
import utils.LoggerUtil;

/**
 * Step definitions for Application search feature.
 *
 * Scenarios covered:
 * - Open home page
 * - Search for a query
 * - Assert the page title contains an expected string
 */
public class ApplicationSteps {

    private static final Logger logger = LoggerUtil.getLogger(ApplicationSteps.class);
    private final HomePage homePage = new HomePage();

    /**
     * Open the configured base URL (Google in current config).
     */
    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        logger.info("Step: I am on the home page");
        homePage.goTo();
    }

    /**
     * Assert that the current page title contains the expected substring.
     * @param expected part of the title expected to be present
     */
    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expected) {
        String title = homePage.getTitle();
        logger.info("Step: Asserting title contains '{}'. Actual='{}'", expected, title);
        Assert.assertTrue(title.toLowerCase().contains(expected.toLowerCase()),
                "Expected title to contain '" + expected + "' but was '" + title + "'");
    }

    @And("total links count should be {int}")
    public void total_links_count_should_be(Integer count) {
        int actualCount = homePage.getAllLinksCount();
        Assert.assertEquals(actualCount, count, "Expected links to contain more than 0");
    }
}