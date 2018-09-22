package User_FE.FE_Page_Factory.Components;

import Utils.ExcelUtils;
import org.openqa.selenium.StaleElementReferenceException;
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
import java.util.NoSuchElementException;

public class CarsSearchBar extends CommonMethods {
    private WebDriver driver;

    @FindBy(xpath = "//a[@href='#CARS']")
    private WebElement carsBarButton;

    @FindBy(id = "s2id_carlocations")
    private WebElement pickUpLocationField;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement pickUpLocationInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li/div")
    private WebElement pickUpLocationFirstResult;

    @FindBy(id = "s2id_carlocations2")
    private WebElement dropOffLocationField;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement dropOffLocationInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li/div")
    private WebElement dropOffLocationSearchResult;

    @FindBy(xpath = "//*[@id=\"CARS\"]/form/div[3]")
    private WebElement departureCalendar;

    @FindBy(xpath = "/html/body/div[11]/div[1]/table/thead/tr[1]/th[2]")
    private WebElement departureCalendarMonthYear;

    @FindBy(xpath = "/html/body/div[11]/div[2]/table/thead/tr/th[2]")
    private WebElement departureCalenarYear;

    @FindBy(xpath = "/html/body/div[11]/div[2]/table/thead/tr/th[3]")
    private WebElement departureCalendarNextButton;

    @FindBy(xpath = "/html/body/div[11]/div[2]/table/thead/tr/th[1]")
    private WebElement departureCalendarPreviousButton;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[11]/div[2]/table/tbody/tr/td/span"))
    private List<WebElement> departureCalendarMonths;

    @FindBy(xpath = "//*[@id=\"CARS\"]/form/div[5]")
    private WebElement arrivalCalendar;

    @FindBy(xpath = "/html/body/div[12]/div[2]/table/thead/tr/th[3]")
    private WebElement arrivalCalenarNextButton;

    @FindBy(xpath = "/html/body/div[12]/div[1]/table/thead/tr[1]/th[2]")
    private WebElement arrivalCalenarMonthYear;

    @FindBy(xpath = "/html/body/div[12]/div[2]/table/thead/tr/th[1]")
    private WebElement arrivalCalenarPreviousButton;

    @FindBy(xpath = "/html/body/div[12]/div[2]/table/thead/tr/th[2]")
    private WebElement arrivalCalendarYear;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[12]/div[2]/table/tbody/tr/td/span"))
    private List<WebElement> arrivalMonths;

    @FindBy(xpath = "/html/body/div[12]/div[1]/table/thead/tr[1]/th[3]")
    private WebElement arrivalCalendarNextButton;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[12]/div[1]/table/tbody/tr/td[not(contains(@class,'day  old'))][not(contains(@class,'day disabled old'))]"))
    private List<WebElement> arrivalDays;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[11]/div[1]/table/tbody/tr/td[not(contains(@class,'day  old'))][not(contains(@class,'day disabled old'))]"))
    private List<WebElement> departureDays;

    @FindBy(xpath = "//*[@id=\"CARS\"]/form/div[4]/div/select")
    private WebElement departureTimeSelect;

    @FindBy(xpath = "//*[@id=\"CARS\"]/form/div[6]/div/select")
    private WebElement arrivalTimeSelect;

    @FindBy(xpath = "//*[@id=\"CARS\"]/form/div[7]/button")
    private WebElement searchButton;

    private final String searchResultsPageTitle = "Search Results";

    private void navigateToCarBar(){
        carsBarButton.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(pickUpLocationField));
    }


    private void enterPickUpLocation(String pickUpLocation){
        pickUpLocationField.click();
        pickUpLocationInput.sendKeys(pickUpLocation);
        pickUpLocationFirstResult.click();
    }

    private void enterDropOffLocation(String dropOffLocation){
        dropOffLocationField.click();

        try{
        dropOffLocationInput.sendKeys(dropOffLocation);
        dropOffLocationSearchResult.click();}catch (Exception e)
//Block catch(StaleElementReferenceException | NoSuchElementException) doesn't catch NoSuchElementException.
         {
            dropOffLocationField.click();
            dropOffLocationInput.sendKeys(dropOffLocation);
            dropOffLocationSearchResult.click();
        }

    }

   private void enterDates(String departureDate, String arrivalDate){
        chooseDate(departureCalendar,departureCalendarMonthYear,departureCalendarNextButton,departureCalendarPreviousButton,departureCalenarYear,departureDays,departureCalendarMonths,departureDate);
        chooseDate(arrivalCalendar,arrivalCalenarMonthYear,arrivalCalendarNextButton,arrivalCalenarPreviousButton,arrivalCalendarYear,arrivalDays,arrivalMonths,arrivalDate);

    }

    private void enterTime(String departureTime, String arrivalTime){
        select(departureTimeSelect,departureTime);
        select(arrivalTimeSelect,arrivalTime);
    }

    private void clickSearchButton(){
        searchButton.click();
    }

    public void search(String pickUpLocation, String dropOffLocation, String departureDate, String arrivalDate, String departureTime, String arrivalTime){
        navigateToCarBar();
        enterPickUpLocation(pickUpLocation);
        enterDropOffLocation(dropOffLocation);
        enterDates(departureDate,arrivalDate );
        enterTime(departureTime,arrivalTime);
        clickSearchButton();
        Assert.assertTrue(searchResultsPageTitle.equals(driver.getTitle()));
    }

    public CarsSearchBar(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }

}
