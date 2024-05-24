package myProject.StepsDefinitions;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;

public class StepDefinitions {
   public WebDriver driver;

    public StepDefinitions(){driver = Hooks.driver;}

    @Given("an example scenario")
    public void anExampleScenario() {
        driver.getCurrentUrl();
        driver.getTitle();
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
    }

}
