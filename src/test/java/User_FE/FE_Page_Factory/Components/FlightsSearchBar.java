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

public class FlightsSearchBar extends CommonMethods {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"body-section\"]/section/div[2]/div/div[2]/ul/li[2]/a")
    private WebElement flightsBarButton;

    @FindBy(xpath = "//*[@id=\"flights\"]/form/div[9]/div[1]/div/div")
    private WebElement oneWayTripCheckbox;

    @FindBy(xpath = "//*[@id=\"flights\"]/form/div[9]/div[2]/div/div")
    private WebElement roundTripCheckbox;


    @FindBy(id = "s2id_autogen12")
    private WebElement departureLocationInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li[1]/div")
    private WebElement firstSearchResult;

    @FindBy(id = "s2id_autogen13")
    private WebElement arrivalLocationInput;


    @FindBy(xpath = "//*[@id=\"flights\"]/form/div[9]/div[3]/div/select")
    private WebElement cabinClassSelect;

    @FindBy(xpath = "//*[@id=\"flights\"]/form/div[3]")
    private WebElement departureCalendarPopup;

    @FindBy(xpath = "/html/body/div[13]/div[1]/table/thead/tr[1]/th[2]")
    private WebElement departureCalendarMonthYear;

    @FindBy(xpath = "/html/body/div[13]/div[2]/table/thead/tr/th[3]")
    private WebElement departureCalendarNextButton;

    @FindBy(xpath = "/html/body/div[13]/div[2]/table/thead/tr/th[2]")
    private WebElement departureCalendarYear;

    @FindBy(xpath = "/html/body/div[13]/div[2]/table/thead/tr/th[1]")
    private WebElement departureCalendarPreviousButton;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[13]/div[2]/table/tbody/tr/td/span"))
    private List<WebElement> departureMonths;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[13]/div[1]/table/tbody/tr/td[not(contains(@class,'day  old'))][not(contains(@class,'day disabled old'))]"))
    private List<WebElement> departureCalendarDays;

    @FindBy(xpath = "//*[@id=\"flights\"]/form/div[4]")
    private WebElement returnCalendarPopup;

    @FindBy(xpath = "/html/body/div[14]/div[1]/table/thead/tr[1]/th[2]")
    private WebElement returnCalendarMonthYear;

    @FindBy(xpath = "/html/body/div[14]/div[2]/table/thead/tr/th[2]")
    private WebElement returnCalendarYear;

    @FindBy(xpath = "/html/body/div[14]/div[2]/table/thead/tr/th[3]")
    private WebElement returnCalendarNextButton;

    @FindBy(xpath = "/html/body/div[14]/div[2]/table/thead/tr/th[1]")
    private WebElement returnCalendarPreviousButton;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[14]/div[2]/table/tbody/tr/td/span"))
    private List<WebElement> returnMonths;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[14]/div[1]/table/tbody/tr/td[not(contains(@class,'day  old'))][not(contains(@class,'day disabled old'))]"))
    private List<WebElement> returnCalendarDays;

    @FindBy(xpath = "//*[@id=\"flights\"]/form/div[5]/div/input")
    private WebElement peopleInputPopup;

    @FindBy(xpath = "//*[@id=\"manual_flightTravelers\"]/div/div/div[2]/section/div/div[1]/div[1]/select")
    private WebElement adultsSelect;

    @FindBy(xpath = "//*[@id=\"manual_flightTravelers\"]/div/div/div[2]/section/div/div[2]/div[1]/select")
    private WebElement childrenSelect;

    @FindBy(xpath = "//*[@id=\"manual_flightTravelers\"]/div/div/div[2]/section/div/div[3]/div[1]/select")
    private WebElement infantSelect;

    @FindBy( id = "manual_flightTravelers")
    private WebElement inputOverlay;

    @FindBy(id = "sumManualPassenger")
    private WebElement confirmPassengerslist;

    @FindBy(xpath = "//*[@id=\"flights\"]/form/div[6]/button")
    private WebElement searchButton;

    private final String searchResultsPageTitle = "Flights List";




private void navigateToFlihgtsBar(){
    flightsBarButton.click();
    WebDriverWait wait = new WebDriverWait(driver,10);
    wait.until(ExpectedConditions.visibilityOf(oneWayTripCheckbox));
}

private void enterDepartureLocation(String location){
    if(!(location.equals(""))){
departureLocationInput.sendKeys(location);
firstSearchResult.click();}
}

private void enterArrivalLocation(String location){
    if(!(location.equals(""))){
    arrivalLocationInput.sendKeys(location);
    firstSearchResult.click();}
}

private void selectCabinClass(String cabinClass){
   select(cabinClassSelect,cabinClass);
}

private void setTripType(String tripType){
    if(tripType.equals("One Way")){
        oneWayTripCheckbox.click();
    } else roundTripCheckbox.click();
}

private void seDates(String departureDate, String returnDate){


        chooseDate(departureCalendarPopup,departureCalendarMonthYear,departureCalendarNextButton,departureCalendarPreviousButton,departureCalendarYear,departureCalendarDays,departureMonths,departureDate);
        if(returnCalendarPopup.isEnabled()){
            chooseDate(returnCalendarPopup,returnCalendarMonthYear,returnCalendarNextButton,returnCalendarPreviousButton,returnCalendarYear,returnCalendarDays,returnMonths,returnDate);
        }


    }
    private void enterPeopleNumber(String adults, String children, String infant){


    peopleInputPopup.click();

        select(adultsSelect, adults);
        select(childrenSelect,children);
        select(infantSelect,infant);
    confirmPassengerslist.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOf(inputOverlay));

    }

private void clickSearchButton(){

    searchButton.click();
}

public void search(String tripType,String cabinClass, String departureLocation, String arrivalLocation, String departureDate,
                   String returnDate, String adults, String children, String infants){
    navigateToFlihgtsBar();
    setTripType(tripType);
    selectCabinClass(cabinClass);
    enterDepartureLocation(departureLocation);
    enterArrivalLocation(arrivalLocation);


    seDates(departureDate,returnDate);
    enterPeopleNumber(adults,children,infants);


    clickSearchButton();



    Assert.assertTrue(searchResultsPageTitle.equals(driver.getTitle()));
}


public FlightsSearchBar(WebDriver driver){
    PageFactory.initElements(driver,this);
    this.driver=driver;
}
}