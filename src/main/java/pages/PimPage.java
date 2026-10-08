package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtil;

import java.time.Duration;

public class PimPage extends BasePage {
    private static final Logger logger = LoggerUtil.getLogger(PimPage.class);

    /**
     * Default constructor uses thread-local WebDriver from DriverFactory and default explicit wait.
     */
    public PimPage() {
        super();
    }

    // Locators

    private static By addEmployee = By.xpath("//a[text()='Add Employee']");
    private static By firstName = By.name("firstName");
    private static By middleName = By.name("middleName");
    private static By lastName = By.name("lastName");
    private static By empID = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private static By saveBtn = By.xpath("//button[text()=' Save ']");
    private static By empSuccessMessage = By.xpath("//*[text()='Success']");

    public void clickOnAddEmployee(){
        driver.findElement(this.addEmployee).click();
    }


    public void addEmployee(String fName, String mName, String lName) throws InterruptedException {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(middleName).sendKeys(mName);
        driver.findElement(lastName).sendKeys(lName);
        Thread.sleep(2000);
        String empID = driver.findElement(this.empID).getAttribute("value");
        System.out.println("empID : : "+empID);
        System.out.println(empID.getClass().getSimpleName());
        int empNumber =  Integer.parseInt(empID)+1;
        Thread.sleep(2000);
        driver.findElement(this.empID).sendKeys(Keys.CONTROL+"a");
        driver.findElement(this.empID).sendKeys(Keys.DELETE);
        driver.findElement(this.empID).sendKeys(String.valueOf(empNumber));
        Thread.sleep(4000);
        driver.findElement(saveBtn).click();

    }
    public boolean isSuccessEmployeeMessageDisplayed() throws InterruptedException {
        Thread.sleep(3000);

        return driver.findElement(this.empSuccessMessage).isDisplayed();
    }


}
