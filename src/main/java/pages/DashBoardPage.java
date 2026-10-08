package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.LoggerUtil;

public class DashBoardPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);
    // Locators
    private final By pimTab = By.xpath("//span[text()='PIM']");

    public void clickOnPimTab(){
        driver.findElement(this.pimTab).click();
    }
}
