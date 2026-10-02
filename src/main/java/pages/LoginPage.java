package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.LoggerUtil;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);

    // Locators
    private final By userName = By.xpath("//input[@name='username']");
    private final By pwd = By.xpath("//input[@name='password']");
    private final By loginBtn = By.xpath("//button[@type='submit']");

    /**
     * Default constructor uses thread-local WebDriver from DriverFactory and default explicit wait.
     */
    public LoginPage() {
        super();
    }
    /**
     * Open the application base URL from configuration and wait for page to be stable.
     */
    public void goTo() {
        logger.info("Opening application base URL");
        openBaseUrl();
    }


    public void loginInToTheApplication(String userName, String password) {
        driver.findElement(this.userName).sendKeys(userName);
        driver.findElement(this.pwd).sendKeys(password);
    }

    public void clickOnLoginButton(){
        driver.findElement(this.loginBtn).click();
    }

}
