package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.LoggerUtil;

public class BuzzPage extends BasePage {
    private static final Logger logger = LoggerUtil.getLogger(BuzzPage.class);
    /**
     * Default constructor uses thread-local WebDriver from DriverFactory and default explicit wait.
     */
    public BuzzPage() {
        super();// call super class consturctor with 0 paramters
    }

    // Locators
    private static By  buzzTab = By.xpath("//span[text()='Buzz']");
    private static By helpIcon = By.cssSelector("button[title='Helpjhjh']");
    private static By postInput = By.xpath("//textarea[@placeholder=\"What's on your mind?\"]");
    private static By postBtn = By.cssSelector("button[type='submit']");


    public void scrollTObuzzElement() throws InterruptedException {
        Thread.sleep(3000);
        scrollIntoView(buzzTab);
    }

    public void clickOnBuzzPage() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(buzzTab).click();
    }
    public void mouseHoverHelpIcon() throws InterruptedException {
        Thread.sleep(3000);
        //hover(helpIcon);
    }

    public void postNewsFeed(){
        this.driver.findElement(postInput).sendKeys("Hi I am posting");
        this.driver.findElement(postBtn).click();


    }



}
