package myProject.StepsDefinitions;

import config.web.WebDriverHelper;
import config.web.WebDriverProperties;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import myProject.web.Pages.EmergenciasPage;
import myProject.web.Pages.OrangeHRMPage;
import myProject.web.PagesObjects.OrangeHRMPageObjects;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import static config.web.WebDriverHelper.takeScreenShot;
import static myProject.StepsDefinitions.Hooks.scenario;

public class StepDefinitions {
   public WebDriver driver;

    public StepDefinitions(){driver = Hooks.driver;}

    OrangeHRMPage orangeHRMPage = new OrangeHRMPage();
    WebDriverProperties webDriverProperties =  new WebDriverProperties();

    EmergenciasPage emergenciasPage =  new EmergenciasPage();



    @Given("^an example scenario$")
    public void anExampleScenario() {
        driver.getCurrentUrl();
        driver.getTitle();
    }

    @When("^all step definitions are implemented$")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("^the scenario passes$")
    public void theScenarioPasses() {
    }

    @Given("^the User fill username text box$")
    public void userFillUsernameTextbox() {

     orangeHRMPage.fillUsername(driver, "Admin");

    }

    @And("^User fill password text box$")
    public void userFillPasswordTextbox() {

     orangeHRMPage.fillPassword(driver, "admin123");
    }

    @And("^The User clicks Login button$")
    public void theUserClicksLoginButton() {

     orangeHRMPage.clickLoginButton(driver);
    }
    @Then("^Verify the user is logged in$")
    public void verifyTheUserIsLoggedIn() {
    WebElement userBulletElm = orangeHRMPage.getuserBulletElm(driver);
     Assert.assertNotNull(userBulletElm,"El login fue incorrecto");
    }

    @When("^the user is logged in$")
    public void theUserIsLoggedIn() {
     JSONObject testData = orangeHRMPage.getRawTestData();

     String uuid= orangeHRMPage.getTestData("uuid");
     String today = orangeHRMPage.getTestData("today");
     orangeHRMPage.saveInTestData("test", "Hola");

     orangeHRMPage.login(driver, webDriverProperties.getMainUsername(), webDriverProperties.getMainUserPass() );
    }


    @Then("^The user go to system user list$")
    public void theUserClicksAdminButton() {
     orangeHRMPage.goToSystemAdminUsers(driver);
    }

    @When("^Verify (.*?) user is present in the list$")
    public void verifyAdminUserIsPresentInTheList(String userName) {
       orangeHRMPage.getSystemUserList(driver, userName);
    }

    @When("^the Admin user is Logged in$")
    public void theAdminUserIsLoggedIn() {

        orangeHRMPage.loginAdminUser(driver);
    }

 @Given("I am waiting for the first step pages to load")
 public void iAmWaitingForTheFirstStepPagesToLoad() {
     emergenciasPage.waitForFirstStepElements(driver);
 }

 @Then("^I am filling the following text boxes:$")
 public void iAmFillingTheFollowingTextBoxes(List<List<String>> table) {

     emergenciasPage.setFirstStepTextBoxes(driver, table);
 }

 @And("^click on (.*?) button$")
 public void clickOnCotizáButton(String tag) {
  emergenciasPage.clickOnButtons(driver, tag);
 }

 @And("^I wait for second step elements are loaded$")
 public void iWaitForSecondStepElementsAreLoaded() {
  emergenciasPage.waitForSecondStepElements(driver);
 }

 @Then("I fill following text boxes:")
 public void iFillFollowingTextBoxes(List<List<String>> table) {
  emergenciasPage.setFirstStepTextBoxes(driver, table);
 }

 @And("^I am waiting for the last step elements to load$")
 public void iWaitForLastStepElementsAreLoaded() {
  emergenciasPage.waitForLastStepElements(driver);
 }

 @Then("I am filling out the registration form with values:")
 public void iFillRegistrationFormWithValues(List<List<String>> table) {
  emergenciasPage.setLastStepTextBoxes(driver, table);
 }

 @And("I saved a screenshot")
 public void iSavedAScreenshot() throws IOException {
   takeScreenShot(driver, scenario);

 }
}
