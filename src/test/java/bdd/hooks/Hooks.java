package bdd.hooks;



import config.ConfigManager;
import driver.BrowserContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;
import driver.DriverFactory;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

/**
 * Cucumber Hooks to manage WebDriver lifecycle and capture artifacts.
 *
 * Responsibilities:
 * - Before each scenario: ensure a thread-local WebDriver exists
 * - After each step (optional): could add step-level logging if needed
 * - After each scenario: on failure, capture screenshot and page source
 * - After all: quit thread-local WebDriver
 */
public class Hooks {


    private static final Logger logger = LoggerUtil.getLogger(Hooks.class);
    private LoginPage loginPage;

    /**
     * Before hook to initialize driver for the current thread.
     * DriverFactory is thread-safe and supports local/grid/cloud execution.
     */


    @Before(order = 0)
    public void setBrowserByTag(Scenario scenario) {
        // Default from config.properties
        String browser = ConfigManager.browser();

        // Override by tag: @chrome, @edge, @firefox
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.equalsIgnoreCase("@chrome")) browser = "chrome";
            if (tag.equalsIgnoreCase("@edge")) browser = "edge";
            if (tag.equalsIgnoreCase("@firefox")) browser = "firefox";
            if (tag.equalsIgnoreCase("@safari")) browser = "safari";
        }
        BrowserContext.set(browser);
        System.out.println("Scenario: " + scenario.getName() + " -> Browser: " + browser + " | Thread: " + Thread.currentThread().getId());
    }
    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        logger.info("Cucumber @Before - Initializing driver for scenario: {}", scenario.getName());
        logger.info("Execution config: browser={}, headless={}, executionType={}, baseUrl={}",
                ConfigManager.browser(), ConfigManager.headless(), ConfigManager.executionType(), ConfigManager.baseUrl());
        DriverFactory.getDriver(); // initializes if absent
    }

    /**
     * After hook to capture diagnostics on failure and cleanup the driver.
     */

    @Before(value = "@Login")
    public void loginIntotheApplication(){
        loginPage = new LoginPage();
        loginPage.goTo();
        loginPage.loginInToTheApplication(ConfigManager.userName(), ConfigManager.password());
        loginPage.clickOnLoginButton();
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.error("Scenario FAILED: {} | Capturing screenshot and page source", scenario.getName());
                // Screenshot
                if (DriverFactory.getDriver() instanceof TakesScreenshot) {
                    String path = ScreenshotUtil.capture(scenario.getName());
                    if (path != null) {
                        scenario.attach(("Saved screenshot: " + path).getBytes(), "text/plain", "screenshot-path");
                    }
                    String base64 = ScreenshotUtil.captureBase64();
                    if (base64 != null) {
                        scenario.attach(java.util.Base64.getDecoder().decode(base64), "image/png", "screenshot");
                    }
                }
                // Page source
                try {
                    String html = DriverFactory.getDriver().getPageSource();
                    scenario.attach(html.getBytes(), "text/html", "page-source");
                } catch (Exception e) {
                    logger.warn("Failed to attach page source", e);
                }
            } else {
                logger.info("Scenario PASSED: {}", scenario.getName());
            }
        } finally {
            logger.info("Quitting driver for scenario: {}", scenario.getName());
            DriverFactory.quitDriver();
        }
    }
}
