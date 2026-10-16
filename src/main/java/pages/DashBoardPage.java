package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LoggerUtil;

import java.util.List;

public class DashBoardPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);
    // Locators
    private final By pimTab = By.xpath("//span[text()='PIM']");
    private final By dashboardtabs = By.xpath("//ul[@class='oxd-main-menu']/li");

    public void clickOnPimTab(){
        driver.findElement(this.pimTab).click();
    }
    public int getAllDashboardTabs(){
        return driver.findElements(dashboardtabs).size();

    }

    public void clickAllTabs() throws InterruptedException {
        List<WebElement> tabs = driver.findElements(dashboardtabs);
        logger.info("tabs size: "+tabs.size());
        for(int i =0;i<tabs.size();i++){
            logger.info("element xpath value: "+tabs.get(i));
            logger.info("element xpath text: "+tabs.get(i).getText());
           // tabs.get(i).click();
            jsClick(tabs.get(i));
           // waitClickableElementRefreshed(tabs.get(i));

        }

    }
}
