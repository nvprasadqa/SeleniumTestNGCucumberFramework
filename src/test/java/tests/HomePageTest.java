package tests;
import base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import retry.RetryAnalyzer;
import utils.LoggerUtil;

/**
 * Sample TestNG test demonstrating:
 * - Extending BaseTest for standardized WebDriver lifecycle and logging
 * - Page Object usage with robust BasePage helpers
 * - Retry mechanism via RetryAnalyzer
 */
public class HomePageTest extends BaseTest {

    private final Logger logger = LoggerUtil.getLogger(HomePageTest.class);



    /**
     * Verify Google home page title contains the word "Google".
     * Steps:
     * 1) Open base URL (configured in config.properties)
     * 2) Read and assert page title
     */
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"smoke", "ui"})
    public void verifyApplicationTitle() {
        logger.info("Starting test: verifyApplicationTitle");
        HomePage homePage = new HomePage();
        homePage.goTo();
        String title = homePage.getTitle();
        logger.info("Asserting title contains 'The Internet' | actual='{}'", title);
        Assert.assertTrue(title.contains("The Internet"), "Title should contain 'The Internet'");
        logger.info("Completed test: verifyTheInternetHomePageTitle");
    }

    /**
     * Example of a simple interaction flow using the HomePage object.
     * Steps:
     * 1) Open base URL
     * 2) Execute a search query
     * 3) Validate title contains the query (best-effort validation)
     */
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "ui"})
    public void verifyApplicationHomePageLinks() {
        String query = "Selenium WebDriver";
        logger.info("Starting test: searchFlow with links='{}'", query);
        HomePage homePage = new HomePage();
        homePage.goTo();
        int linksCount = homePage.getAllLinksCount();
        logger.info("Total links found: {}", linksCount);
        Assert.assertEquals(linksCount, 46, "Validating Links Count");
        logger.info("Completed test: searchFlow");
    }
}
