package User_FE.FE_Page_Factory.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class TourSearchBar extends CommonMethods
{
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"body-section\"]/section/div[2]/div/div[2]/ul/li[3]/a")
    private WebElement tourBarButton;

    @FindBy(xpath = "//*[@id=\"TOURS\"]/form/div[5]/button")
    private WebElement searchButton;

    @FindBy(id = "s2id_autogen10")
    private WebElement listingOrCityField;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement listingOrCityInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li/ul/li/div")
    private WebElement firstSearchResult;

    @FindBy(xpath = "//*[@id=\"tchkin\"]/div/input")
    private WebElement calendarPopup;

    @FindBy(xpath = "/html/body/div[10]/div[1]/table/thead/tr[1]/th[2]")
    private WebElement calendarCurrentMonth;

    @FindBy(xpath = "/html/body/div[10]/div[1]/table/thead/tr[1]/th[3]")
    private WebElement nextMonthButton;

    @FindAll(@FindBy(how = How.XPATH,using="/html/body/div[10]/div[1]/table/tbody/tr/td[not(contains(@class,'day  old'))][not(contains(@class,'day disabled old'))]"))
    private List<WebElement> calendarDates;

    @FindBy(id = "adults")
    private WebElement selectNumberofPeople;

    @FindBy(id = "s2id_tourtype")
    private WebElement tourTypeDropdown;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement tourTypeInputField;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li[1]")
    private WebElement firstMatchedTourType;

    //in case there is more than one search result group have to define both of them
    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li[1]/div")
    private WebElement firstSearchResultGroup;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li[2]/div")
    List<WebElement> secondSearchResultGroup;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li[1]/ul/li/div")
    private WebElement multipleResultsfirstElement;

    private final String searchResultsPageTitle = "Search Results";

    private void enterNumberOfPeople(String number){
        select(selectNumberofPeople,number);
    }

    private String tourName;

    private void navigateToTourBar(){
        tourBarButton.click();
        WebDriverWait wait=new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(listingOrCityField));
    }

    private void enterCityOrListing(String value){


        if(!(value.equals(""))){


            listingOrCityField.click();
            listingOrCityInput.sendKeys(value);

            if(firstSearchResultGroup.isDisplayed()&& (!(secondSearchResultGroup.size()==0))){
            tourName=multipleResultsfirstElement.getText();
            multipleResultsfirstElement.click();} else{
                tourName=firstSearchResult.getText();
                firstSearchResult.click();}
            }
        }

        private void enterDate(String date){
        chooseDate(calendarPopup,calendarCurrentMonth,nextMonthButton,calendarDates,date);
        }

        private void enterTourType(String type){
            if(!(type.equals(""))){
                tourTypeDropdown.click();
                tourTypeInputField.sendKeys(type);
                firstMatchedTourType.click();
            }
        }

public TourSearchBar(WebDriver driver){
    PageFactory.initElements(driver,this);
    this.driver=driver;

}

private void clickSearchButton(){
    searchButton.click();
}

public void search(String listingOrCity, String date, String numberOfGuests, String tourType){
    navigateToTourBar();
    enterCityOrListing(listingOrCity);
    enterDate(date);
    enterNumberOfPeople(numberOfGuests);
    enterTourType(tourType);
    clickSearchButton();

    System.out.println(tourName);
    Assert.assertTrue(searchResultsPageTitle.equals(driver.getTitle())||driver.getTitle().equals(tourName));
}

}
