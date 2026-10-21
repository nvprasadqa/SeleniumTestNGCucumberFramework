package bdd.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import pages.BuzzPage;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.PimPage;
import utils.LoggerUtil;

import java.util.List;
import java.util.Map;

public class OrangeHrmSteps {

    private static final Logger logger = LoggerUtil.getLogger(OrangeHrmSteps.class);
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    BuzzPage buzzPage  = new BuzzPage();
    PimPage pimPage = new PimPage();
    public String firstName = "";
    public String lastName = "";
    public int dashBoardTabsCount = 0;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        logger.info("Step: I am on the login page");
        loginPage.goTo();
    }

    @When("User enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String username, String password) throws InterruptedException {
        loginPage.loginInToTheApplication(username, password);


    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
       loginPage.clickOnLoginButton();
    }


    @Then("User should be navigated to dashboard with url contains contains {string}")
    public void userShouldBeNavigatedToDashbaordWithUrlContainsContains(String urlText) {
        String currentURL = dashBoardPage.getCurrentUrl();
        Assert.assertTrue(currentURL.contains(urlText), "validating url text in the current url");
    }

    @Then("User should see an error message as {string}")
    public void userShouldSeeAnErrorMessageAs(String errorMessage) throws InterruptedException {
        boolean results = loginPage.isLoginDynamicErrorMessageDisplayed(errorMessage);
        Thread.sleep(2000);
        Assert.assertTrue(results, "Validating invalid credentials message.");


    }

    @Then("User should see an error message")
    public void userShouldSeeAnErrorMessage() throws InterruptedException {
        boolean results = loginPage.isLoginErrorMessageDisplayed();
        Thread.sleep(2000);
        Assert.assertTrue(results, "Validating invalid credentials message.");
    }

    @When("i navigate to PIM page")
    public void iNavinateToPIMPage() {
        dashBoardPage.clickOnPimTab();

    }

    @And("i add below employees")
    public void iAddBelowEmployees(DataTable dataTable) throws InterruptedException {
        pimPage.clickOnAddEmployee();
        // using asList apporach
//        List<List<String>> empList = dataTable.asLists(String.class);
//         for(List<String> emp: empList){
//             String fName = emp.get(0);
//             String mName = emp.get(1);
//             String lName = emp.get(2);
//
//
//
//             pimPage.addEmployee(fName,mName,lName);
//
//         }

        List<Map<String, String>> data = dataTable.asMaps(
                String.class, String.class
        );

        for(Map<String, String> employee: data) {
            firstName = employee.get("firstName");
            String mName = employee.get("middleName");
             lastName = employee.get("lastName");
            pimPage.addEmployee(firstName, mName, lastName);

        }


    }

    @Then("I should see employess added successfully")
    public void iShouldSeeEmployessAddedSuccessfully() throws InterruptedException {
        boolean results = pimPage.isSuccessEmployeeMessageDisplayed();
        Assert.assertTrue(results, "validating sucessful employee");

    }

    @And("I should validate same employee in Personal details Screen")
    public void iShouldValidateSameEmployeeInPersonalDetailsScreeen() {
        pimPage.isEmployeeNameDisplayed(firstName+" "+lastName);

    }

    @When("I provide employee personal details in Personal Details Screen")
    public void iProvideEmployeePersonalDetailsInPersonalDetailsScreen(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(
                String.class, String.class
        );

        for(Map<String, String> employee: data) {
           String dlNumber = employee.get("dLNumber");
            String dlExpiry = employee.get("licenceExpiryDate");
            String nationality = employee.get("Nationality");
            String gender = employee.get("Gender");
            pimPage.addPersonalDetails(dlNumber, dlExpiry, nationality, gender);

        }
    }

    @When("User Scrolls page using JavaScript executor")
    public void userScrollsPageUsingJavaScriptExecutor() throws InterruptedException {

        buzzPage.scrollTObuzzElement();
        
    }

    @And("User click on element using JavaScript executor")
    public void userClickOnElementUsingJavaScriptExecutor() throws InterruptedException {
        buzzPage.clickOnBuzzPage();
        
    }

    @When("User performs mouse hover on question mark icon")
    public void userPerformsMouseHoverOnQuestionMarkIcon() throws InterruptedException {
        buzzPage.mouseHoverHelpIcon();


        
    }

    @And("User post a commnent and click on post")
    public void userPostACommnentAndClickOnPost() {
        buzzPage.postNewsFeed();

    }

    @When("User finds all the tabs on dashboard screen")
    public void userFindsAllTheTabsOnDashboardScreen() {
         dashBoardTabsCount = dashBoardPage.getAllDashboardTabs();
    }

    @Then("User should be able to validate the tabs count as {int}")
    public void userShouldBeAbleToValidateTheTabsCountAs(int count) {
        logger.info(dashBoardTabsCount+" count");

        Assert.assertEquals(count, dashBoardTabsCount, "Validating the tab count on the dashboard");

    }

    @And("User clicks on each tab")
    public void userClicksOnEachTab() throws InterruptedException {
        dashBoardPage.clickAllTabs();

    }
}





