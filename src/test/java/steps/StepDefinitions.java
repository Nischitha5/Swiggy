package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import utils.BrowserManager;
import utils.EnvProps;
import java.util.List;

public class StepDefinitions {

    private WebDriver driver;
    HomePage homePage;

    String url;


    public StepDefinitions(BrowserManager browserManager) {
        this.driver = browserManager.getDriver();
    }


    //background
    @Given("the user navigates to the swiggy landing page")
    public void the_user_navigates_to_the_swiggy_landing_page() {
        homePage = new HomePage(driver);
        url = EnvProps.getValue("url");
        driver.get(url);

    }

    @When("the user enters the delivery location")
    public void theUserEntersTheDeliveryLocation() {
        //homePage.getDeliveryLocation().sendKeys(data.get("TypeValue"));
        homePage.getDeliveryLocation().sendKeys("Bangalore, Karnataka, India");
        homePage.getDeliveryLocationList().click();
    }

    @Then("the user should be taken to swiggy home page")
    public void the_user_should_be_taken_to_swiggy_home_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.swiggy.com"));
    }

    //testcase1
    @When("the user clicks on the search icon")
    public void theUserClicksOnTheSearchIcon() {
        homePage.getSearchIcon().click();
        System.out.println("hai");
    }

    @Then("the user should be taken to search page")
    public void theUserShouldBeTakenToSearchPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.swiggy.com/search"));
    }
}