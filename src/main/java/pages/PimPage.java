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
        super();// call super class consturctor with 0 paramters
    }

    // Locators
    private static By addEmployee = By.xpath("//a[text()='Add Employee']");
    private static By firstName = By.name("firstName");
    private static By middleName = By.name("middleName");
    private static By lastName = By.name("lastName");
    private static By empID = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private static By saveBtn = By.xpath("//button[text()=' Save ']");
    private static By empSuccessMessage = By.xpath("//*[text()='Success']");
    private static By dlNumber = By.xpath("//label[contains(text(), 'Driver')]/parent::div/following-sibling::div//input");
    private static By expiryDate = By.xpath("//label[text()='License Expiry Date']/parent::div/following-sibling::div//input");
    private static By nationality = By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private static By yearDropdown = By.xpath("//div[contains(@class, 'oxd-calendar-selector-year')]");
    private static By monthDrpdown = By.xpath("//div[@class='oxd-calendar-selector-month-selected']");





    public By getDynamicEmpName(String name){

        return By.xpath("//h6[text()='"+name+"']");
    }

    public By getDynamicGender(String genderName){

        return By.xpath("//label[text()='"+genderName+"']");
    }

    public By getDynamicNationality(String nationality){

        return By.xpath("//*[text()='"+nationality+"']");
    }
    public By getDynamicYear(String year){
        return By.xpath("//li[text()='"+year+"']");
    }

    public By getDynamicMonth(String month){
        return By.xpath("//li[text()='"+month+"']");
    }

    public By getDynamicDay(String day){
        return By.xpath("//div[text()='"+day+"']");
    }


    public void clickOnAddEmployee(){
        driver.findElement(this.addEmployee).click();
    }


    public void addEmployee(String fName, String mName, String lName) throws InterruptedException {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(middleName).sendKeys(mName);
        driver.findElement(lastName).sendKeys(lName);
        Thread.sleep(2000);
        String empID = driver.findElement(this.empID).getAttribute("value");
        int empNumber =  Integer.parseInt(empID)+1;
        Thread.sleep(2000);
        driver.findElement(this.empID).sendKeys(Keys.CONTROL+"a");
        driver.findElement(this.empID).sendKeys(Keys.DELETE);
        driver.findElement(this.empID).sendKeys(String.valueOf(empNumber));
        Thread.sleep(4000);
        driver.findElement(saveBtn).click();

    }
    public boolean isSuccessEmployeeMessageDisplayed() throws InterruptedException {
        Thread.sleep(2000);

        return driver.findElement(this.empSuccessMessage).isDisplayed();
    }

    public boolean isEmployeeNameDisplayed(String empName){

        By dynamicName = getDynamicEmpName(empName);
        waitVisible(dynamicName);
        return driver.findElement(dynamicName).isDisplayed();

    }

     public void addPersonalDetails(String dlNumber, String dlExpiry, String nationality, String gender) throws InterruptedException {
        driver.findElement(this.dlNumber).sendKeys(dlNumber);
        //waitVisible(this.expiryDate).sendKeys(dlExpiry);
         Thread.sleep(4000);
         selectLicenseExpiryDate("2026", "March", "12");
         Thread.sleep(4000);
        driver.findElement(this.nationality).click();
        driver.findElement(getDynamicNationality(nationality)).click();
        driver.findElement(getDynamicGender(gender)).click();



     }

     public void selectLicenseExpiryDate(String year, String month, String day) throws InterruptedException {

        driver.findElement(this.expiryDate).click();
        waitClickable(yearDropdown).click();
        Thread.sleep(2000);
        waitClickable(getDynamicYear(year)).click();
        waitClickable(monthDrpdown).click();
         Thread.sleep(2000);
        waitClickable(getDynamicMonth(month)).click();
        waitClickable(getDynamicDay(day)).click();

     }
}
