package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import javax.xml.xpath.XPath;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href=\"/search\"][1]//span[2]")
    WebElement searchIcon;

    @FindBy(id = "location")
    WebElement deliveryLocation;

    @FindBy(xpath = "//span[@class=\"_2W-T9\"][1]")
    WebElement deliveryLocationList;

    @FindBy(className = "_1QBzC")
    WebElement searchBoxAlignment;

    @FindBy(className = "_2FkHZ")
    WebElement searchBox;

    @FindBy(className = "Search_errorMessage__3SJFx")
    WebElement searchErrorMessage;

    @FindBy(className = "_1BoSg")
    WebElement searchSeeForAllResult;

    @FindBy(className = "_16EzP")
    WebElement searchNavigateBack;

    @FindBy(xpath = "//span[@class='jBKjW']//*[name()='svg']")
    WebElement searchClear;

    @FindBy(className = "_2p8XD")
    WebElement searchMagnifier;

    @FindBy(xpath = "//div/button[@class=\"_37IIF\"]")
    List<WebElement> searchAutoSuggestion;

    @FindBy(xpath = "//h3[@class=\"styles_itemNameText__3ZmZZ\"]")
    List<WebElement> itemsDisplayed;

    public WebElement getSearchBox() {
        return searchBox;
    }
    public WebElement getSearchIcon() {
        return searchIcon;
    }
    public WebElement getDeliveryLocation() {
        return deliveryLocation;
    }
    public WebElement getDeliveryLocationList() {
        return deliveryLocationList;
    }
    public WebElement getSearchBoxAlignment() {
        return searchBoxAlignment;
    }
    public WebElement getSearchErrorMessage() {
        return searchErrorMessage;
    }
    public WebElement getSearchSeeForAllResult() {
        return searchSeeForAllResult;
    }
    public WebElement getSearchNavigateBack() {
        return searchNavigateBack;
    }
    public WebElement getSearchClear() {
        return searchClear;
    }
    public WebElement getSearchMagnifier() {
        return searchMagnifier;
    }
    public List<WebElement> getSearchAutoSuggestion() {
        return searchAutoSuggestion;
    }
    public List<WebElement> getItemsDisplayed() {
        return itemsDisplayed;
    }
}
