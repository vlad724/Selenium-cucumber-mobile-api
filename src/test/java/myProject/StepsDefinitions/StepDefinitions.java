package myProject.StepsDefinitions;

import config.web.WebDriverHelper;
import io.cucumber.java.en.*;

import myProject.web.Pages.OrangeHRMPage;
import myProject.web.PagesObjects.OrangeHRMPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class StepDefinitions {
   public WebDriver driver;

    public StepDefinitions(){driver = Hooks.driver;}

    OrangeHRMPage orangeHRMPage = new OrangeHRMPage();

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
     orangeHRMPage.login(driver, "Admin", "admin123" );
    }


    @Then("^The user go to system user list$")
    public void theUserClicksAdminButton() {
     orangeHRMPage.goToSystemAdminUsers(driver);
    }

    @When("^Verify (.*?) user is present in the list$")
    public void verifyAdminUserIsPresentInTheList(String userName) {
       orangeHRMPage.getSystemUserList(driver, userName);
    }
}
