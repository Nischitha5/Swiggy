package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
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
import utils.TestDataReader;

import java.util.HashMap;
import java.util.List;


public class StepDefinitions {

    private WebDriver driver;
    HomePage homePage;

    String url1;
    String url2;
    String url3;
    String url4;

    HashMap<String, String> data;
    Scenario scenario;


    public StepDefinitions(BrowserManager browserManager) {
        this.driver = browserManager.getDriver();
    }

    @Before(order = 1)
    public void before(Scenario scenario){
        this.scenario=scenario;
    }

    //background
    @Given("the user navigates to the swiggy landing page")
    public void the_user_navigates_to_the_swiggy_landing_page() {
        homePage = new HomePage(driver);
        url1 = EnvProps.getValue("locationurl");
        driver.get(url1);

    }

    @When("the user enters the delivery location")
    public void theUserEntersTheDeliveryLocation() {
        homePage.getDeliveryLocation().sendKeys("Bangalore, Karnataka, India");
        homePage.getDeliveryLocationList().click();
    }

    @Then("the user should be taken to swiggy home page")
    public void the_user_should_be_taken_to_swiggy_home_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains(url1));
    }

    //testcase1
    @When("the user clicks on the search icon")
    public void theUserClicksOnTheSearchIcon() {
        homePage.getSearchIcon().click();
    }

    @Then("the user should be taken to search page")
    public void theUserShouldBeTakenToSearchPage() {
        url2 = EnvProps.getValue("searchurl");
        Assert.assertTrue(driver.getCurrentUrl().contains(url2));
    }

    //testcase2
    @When("the user clicks on the search")
    public void theUserClicksOnTheSearch(){
        homePage.getSearchIcon().click();
        url3 = EnvProps.getValue("searchurl");
        Assert.assertTrue(driver.getCurrentUrl().contains(url3));
    }

    @Then("the search box should be displayed")
    public void theSearchBoxShouldBeDisplayed(){
        boolean box = homePage.getSearchBoxAlignment().isDisplayed();
        Assert.assertTrue(box,"true");
    }

    //testcase3
    @Then("the search box should be displayed with placeholder inside it")
    public void theSearchBoxShouldBeDisplayedWithPlaceholderInsideIt() {
        data = TestDataReader.getData(scenario.getName());
        String attribute = homePage.getSearchBox().getAttribute("placeholder");
        Assert.assertEquals(attribute,data.get("asserting"));
    }

    //testcase4
    @And("the user enters an item inside the search box")
    public void theUserEntersAnItemInsideTheSearchBox(){
        data = TestDataReader.getData(scenario.getName());
        WebElement enter = homePage.getSearchBox();
        enter.sendKeys(data.get("TypeValue"));
    }

    @Then("the user should be able to enter an item inside the search box")
    public void theUserShouldBeAbleToEnterAnItemInsideTheSearchBox() {
        WebElement enter = homePage.getSearchBox();
        String text = enter.getAttribute("value");
        Assert.assertEquals(text,data.get("TypeValue"));
    }

    //testcase5
    @And("the user enters a special character")
    public void theUserEntersASpecialCharacter(){
        data = TestDataReader.getData(scenario.getName());
        WebElement bar = homePage.getSearchBox();
        bar.sendKeys(data.get("TypeValue"));
    }

    @And("the user clicks on see for all results")
    public void theUserClicksOnSeeForAllResults(){
        homePage.getSearchSeeForAllResult().click();
    }

    @Then("the user should get an error message saying no match found")
    public void theUserShouldGetAnErrorMessageSayingNoMatchFound() {
        String special = homePage.getSearchErrorMessage().getText();
        Assert.assertEquals(special,data.get("asserting"));
    }

    //testcase6
    @And("the user enters an item")
    public void theUserEntersAnItem(){
        data = TestDataReader.getData(scenario.getName());
        WebElement enter = homePage.getSearchBox();
        enter.sendKeys(data.get("TypeValue"));
        enter.sendKeys(Keys.ENTER);
    }
    @And("the user clicks on the navigate back present inside the search box")
    public void theuserclicksonthenavigatebackpresentinsidethesearchbox() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(homePage.getSearchNavigateBack()));
        homePage.getSearchNavigateBack().click();
    }

    @Then("the user should be able to navigate back")
    public void theUserShouldBeAbleToNavigateBack(){
        url4 = EnvProps.getValue("searchurl");
        Assert.assertTrue(driver.getCurrentUrl().contains(url4));
        boolean maginifier = homePage.getSearchMagnifier().isDisplayed();
        Assert.assertEquals(maginifier,true);
    }

    //testcase7
    @And("the user enters an item in the search box")
    public void theUserEntersAnItemInTheSearchBox() {
        data = TestDataReader.getData(scenario.getName());
        WebElement enter = homePage.getSearchBox();
        enter.sendKeys(data.get("TypeValue"));
    }
    @And("the user clicks on the cross icon present inside the search box")
    public void theUserClicksOnTheCrossIconPresentInsideTheSearchBox() {
        homePage.getSearchClear().click();
    }

    @Then("the user should be able to clear the text")
    public void theUserShouldBeAbleToClearTheText() {
        boolean maginifier = homePage.getSearchMagnifier().isDisplayed();
        Assert.assertEquals(maginifier,true);
    }

    //testcase8
    @And("the user enters an item name inside the search box")
    public void theUserEntersAnItemNameInsideTheSearchBox() {
        data = TestDataReader.getData(scenario.getName());
        WebElement enter = homePage.getSearchBox();
        enter.sendKeys(data.get("TypeValue"));
    }

    @Then("the auto suggestion must be shown below the search box")
    public void theAutoSuggestionMustBeShownBelowTheSearchBox() throws InterruptedException {
        data = TestDataReader.getData(scenario.getName());
        WebDriverWait wait=new WebDriverWait(driver, 5);
        List<WebElement> suggestion_lists = wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getSearchAutoSuggestion()));
        System.out.println(suggestion_lists.size());
        Assert.assertTrue(suggestion_lists.size()==11);
        for(int i=1; i<11; i++){
            WebElement suggestion = suggestion_lists.get(i);
            System.out.println(suggestion.getText());
            Assert.assertTrue(suggestion.getText().contains(data.get("TypeValue")));
        }
    }

    //testcase9
    @And("the user enters an item name as {string} inside the search box")
    public void theUserEntersAnItemNameAsInsideTheSearchBox(String arg0) {
        WebElement items = homePage.getSearchBox();
        items.sendKeys(arg0);
        items.sendKeys(Keys.ENTER);
    }

    @Then("the user should get list of items matching the entered items {string} below the search box")
    public void theUserShouldGetListOfItemsMatchingTheEnteredItemsBelowTheSearchBox(String arg0) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> displayed_items = wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getItemsDisplayed()));
        int sizeOfList = displayed_items.size();
        System.out.println(sizeOfList);
        Assert.assertTrue(sizeOfList >= 1);
        for(int i=0; i<sizeOfList; i++){
            WebElement display = displayed_items.get(i);
            String text = display.getText().toLowerCase();
            System.out.println(text);
            String arg0StartingThreeLetters = arg0.substring(0, 3);
            System.out.println(arg0StartingThreeLetters);
            Assert.assertTrue(text.contains(arg0StartingThreeLetters.toLowerCase()));
        }
        }



    //testcase10
    @And("the user enters a single character inside the search box")
    public void theUserEntersASingleCharacterInsideTheSearchBox() {
        data = TestDataReader.getData(scenario.getName());
        homePage.getSearchBox().sendKeys(data.get("TypeValue"));
    }

    @Then("the user should get an auto suggestion matching the items based on the entered character")
    public void theUserShouldGetAnAutoSuggestionMatchingTheItemsBasedOnTheEnteredCharacter() {
        data = TestDataReader.getData(scenario.getName());
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> suggestion_lists = wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getSearchAutoSuggestion()));
        System.out.println(suggestion_lists.size());
        Assert.assertTrue(suggestion_lists.size() == 11);
        for (int i = 1; i < 11; i++) {
            WebElement suggestion = suggestion_lists.get(i);
            System.out.println(suggestion.getText());
            Assert.assertTrue(suggestion.getText().contains(data.get("TypeValue")));
        }
    }

}