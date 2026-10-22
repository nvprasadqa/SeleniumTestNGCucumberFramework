package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.LoggerUtil;

public class ClaimPage extends BasePage {
    private static final Logger logger = LoggerUtil.getLogger(ClaimPage.class);
    /**
     * Default constructor uses thread-local WebDriver from DriverFactory and default explicit wait.
     */
    public ClaimPage() {
        super();// call super class consturctor with 0 paramters
    }
    private static By claimTab = By.xpath("//span[text()='Claim']");

    private static By submitClaim = By.xpath("//a[text()='Submit Claim']");

    private static By eventField = By.xpath("//label[text()='Event']/following::div[1]");
    private static By currencyField = By.xpath("//label[text()='Currency']/following::div[1]");
    private static By remarksField = By.xpath("//textarea");
    private static By createBtn = By.xpath("//button[@type='submit']");


    public By getEvent(String eventType){
        return By.xpath("//span[text()='"+eventType+"']");
    }

    public By getCurrency(String currencyType){
        return By.xpath("//span[text()='"+currencyType+"']");
    }

    public void clickOnClaimPage() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(claimTab).click();
    }

//    public void clickOnSubmitClaim() throws InterruptedException {
//
//        driver.findElement(submitClaim).click();
//    }

    public void submitClaim(String event, String currency, String data){

        driver.findElement(submitClaim).click();

        driver.findElement(eventField).click();
        waitClickable(getEvent(event)).click();
        driver.findElement(currencyField).click();
        waitClickable(getCurrency(currency)).click();
        driver.findElement(remarksField).sendKeys(data);
        driver.findElement(createBtn).click();

    }




}
