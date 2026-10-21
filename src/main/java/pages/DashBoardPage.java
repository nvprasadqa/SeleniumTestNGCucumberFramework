package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashBoardPage extends BasePage {

    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);
    // Locators
    private final By pimTab = By.xpath("//span[text()='PIM']");
    private final By dashboardtabs = By.xpath("//ul[@class='oxd-main-menu']/li");

    public By getDynamicTab(String tabName) {

        return By.xpath("//span[text()='" + tabName + "']");
    }

    public void clickOnPimTab() {
        driver.findElement(this.pimTab).click();
    }

    public int getAllDashboardTabs() {
        return driver.findElements(dashboardtabs).size();

    }

    public List<String> getAllTabNames() {
        return driver.findElements(dashboardtabs).stream().map(ele->ele.getText()).collect(Collectors.toList());

//        List<WebElement> li = driver.findElements(dashboardtabs);
//
//        List<String> tabs = new ArrayList<>();
//        for (WebElement ele : li) {
//
//            tabs.add(ele.getText());
//        }
//        return tabs;

    }

    public void clickAllTabs() {
        List<String> names = getAllTabNames();
        for (String name : names) {
                driver.findElement(getDynamicTab(name)).click();
                logger.info("click one " + name);
            if (name.equals("Maintenance")) {
                driver.navigate().back();
            }
        }


    }
}
