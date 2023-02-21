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
    //testcase2
    @When("the user clicks on the search")
    public void theUserClicksOnTheSearch(){
        homePage.getSearchIcon().click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.swiggy.com/search"));
    }

    @Then("the search box should be displayed")
    public void theSearchBoxShouldBeDisplayed(){
        boolean box = homePage.getSearchBoxAlignment().isDisplayed();
        Assert.assertTrue(box,"true");
    }

    //testcase3
    @Then("the search box should be displayed as per requirement for the dimension")
    public void theSearchBoxShouldBeDisplayedAsPerRequirementForTheDimension() {
        Dimension dimension = homePage.getSearchBoxAlignment().getSize();
        Assert.assertEquals(dimension.getHeight(),48);
        Assert.assertEquals(dimension.getWidth(),858);
    }


    //testcase4
    @Then("the search box should be displayed with placeholder inside it")
    public void theSearchBoxShouldBeDisplayedWithPlaceholderInsideIt() {
        String attribute = homePage.getSearchBox().getAttribute("placeholder");
        Assert.assertEquals(attribute,"Search for restaurants and food");
    }

    //testcase5
    @And("the user enters an item inside the search box")
    public void theUserEntersAnItemInsideTheSearchBox(){
        WebElement enter = homePage.getSearchBox();
        enter.sendKeys("Biryani");
    }

    @Then("the user should be able to enter an item inside the search box")
    public void theUserShouldBeAbleToEnterAnItemInsideTheSearchBox() {
        WebElement enter = homePage.getSearchBox();
        String text = enter.getAttribute("value");
        Assert.assertEquals(text,"Biryani");
        enter.clear();
    }

    //testcase6
    @And("the user enters a special character")
    public void theUserEntersASpecialCharacter(){
        WebElement bar = homePage.getSearchBox();
        bar.sendKeys("%@");
    }

    @And("the user clicks on see for all results")
    public void theUserClicksOnSeeForAllResults(){
        homePage.getSearchSeeForAllResult().click();
    }

    @Then("the user should get an error message saying no match found")
    public void theUserShouldGetAnErrorMessageSayingNoMatchFound() {
        String special = homePage.getSearchErrorMessage().getText();
        Assert.assertEquals(special,"No match found for \"%@\"");
    }

    //testcase7
    @And("the user enters an item")
    public void theUserEntersAnItem(){
        WebElement enter = homePage.getSearchBox();
        enter.sendKeys("Biryani");
        enter.sendKeys(Keys.ENTER);
    }
    @And("the user clicks on the navigate back present inside the search box")
    public void theuserclicksonthenavigatebackpresentinsidethesearchbox() throws InterruptedException {
        Thread.sleep(2000);
        homePage.getSearchNavigateBack().click();
    }

    @Then("the user should be able to navigate back")
    public void theUserShouldBeAbleToNavigateBack(){
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.swiggy.com/search"));
        boolean maginifier = homePage.getSearchMagnifier().isDisplayed();
        Assert.assertEquals(maginifier,true);
    }

    //testcase8
    @And("the user clicks on the cross icon present inside the search box")
    public void theUserClicksOnTheCrossIconPresentInsideTheSearchBox() {
        homePage.getSearchClear().click();
    }

    @Then("the user should be able to clear the text")
    public void theUserShouldBeAbleToClearTheText() {
        boolean maginifier = homePage.getSearchMagnifier().isDisplayed();
        Assert.assertEquals(maginifier,true);
    }

    //testcase9
    @And("the user enters an item name inside the search box")
    public void theUserEntersAnItemNameInsideTheSearchBox() {
        WebElement enter = homePage.getSearchBox();
        enter.sendKeys("Pizza");
    }

    @Then("the auto suggestion must be shown below the search box")
    public void theAutoSuggestionMustBeShownBelowTheSearchBox() throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver, 5);
        List<WebElement> suggestion_lists = wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getSearchAutoSuggestion()));
        System.out.println(suggestion_lists.size());
        Assert.assertTrue(suggestion_lists.size()==11);
        for (WebElement suggestion: suggestion_lists)
            System.out.println(suggestion.getText());
        Assert.assertNotNull(suggestion_lists,"auto suggestions are not present or not displayed");
    }

    //testcase10
    @And("the user enters an item name as {string} inside the search box")
    public void theUserEntersAnItemNameAsInsideTheSearchBox(String arg0) {
        WebElement items = homePage.getSearchBox();
        items.sendKeys(arg0);
        items.sendKeys(Keys.ENTER);
        //theUserShouldGetListOfItemsMatchingTheEnteredItemsBelowTheSearchBox(arg0);
    }

    @Then("the user should get list of items matching the entered items below the search box")
    public void theUserShouldGetListOfItemsMatchingTheEnteredItemsBelowTheSearchBox() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> displayed_items = wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getItemsDisplayed()));
        System.out.println(displayed_items.size());
        Assert.assertTrue(displayed_items.size() >= 1);
        for (WebElement display : displayed_items) {
            String text = display.getText().toLowerCase();
            System.out.println(text);
            Assert.assertNotNull(displayed_items,"auto suggestions are not present or not displayed");
            //Assert.assertTrue(text.contains(arg0.toLowerCase()));
        }
    }

    //testcase11
    @And("the user enters a single character inside the search box")
    public void theUserEntersASingleCharacterInsideTheSearchBox() {
        homePage.getSearchBox().sendKeys("P");
    }

    @Then("the user should get an auto suggestion matching the items based on the entered character")
    public void theUserShouldGetAnAutoSuggestionMatchingTheItemsBasedOnTheEnteredCharacter() {
        WebDriverWait wait=new WebDriverWait(driver, 5);
        List<WebElement> suggestion_lists = wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getSearchAutoSuggestion()));
        Assert.assertTrue(suggestion_lists.size()==11);
        for (WebElement suggestion: suggestion_lists)
            System.out.println(suggestion.getText());
        Assert.assertNotNull(suggestion_lists,"auto suggestions are not present or not displayed");
    }
}