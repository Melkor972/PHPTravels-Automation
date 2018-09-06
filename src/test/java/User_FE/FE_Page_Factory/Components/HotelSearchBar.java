package User_FE.FE_Page_Factory.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HotelSearchBar extends CommonMethods {
    WebDriver driver;
    @FindBy(id = "s2id_autogen8")
    private WebElement hotelOrCityNameField;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement HOCInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li/ul/li[1]")
    private WebElement firstHOCDropdownElement;

    @FindBy(xpath = "//*[@id=\"dpd1\"]/div")
    private WebElement checkInDatePopup;

    @FindBy(xpath = "//*[@id=\"dpd2\"]/div")
    private WebElement checkoutCalendarPopup;

    @FindBy(xpath = "/html/body/div[8]/div[1]/table/thead/tr[1]/th[3]")
    private WebElement checkIncalendarNextMonthButton;

    @FindBy(xpath = "/html/body/div[9]/div[1]/table/thead/tr[1]/th[3]")
    private WebElement checkOutCalendarNextMonthButton;

    @FindBy(xpath = "/html/body/div[8]/div[1]/table/thead/tr[1]/th[2]")
    private WebElement checkInCalendarcCurentMonth;

    @FindBy(xpath = "/html/body/div[9]/div[1]/table/thead/tr[1]/th[2]")
    private WebElement checkOutCalendarCurrentMonth;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[8]/div[1]/table/tbody/tr/td[not(contains(@class,'day  old'))]" +
            "[not(contains(@class,'day disabled old'))]" ))
    private List<WebElement> checkInCalendarDates;

    @FindAll(@FindBy(how = How.XPATH,using = "/html/body/div[9]/div[1]/table/tbody/tr/td[not(contains(@class,'day  old'))][not(contains(@class,'day disabled old'))]" ))
    private List<WebElement> checkOutCalendarDates;

    @FindBy(id = "travellersInput")
    private WebElement numberOfTravelersField;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusButton;

    @FindBy(id = "adultMinusBtn")
    private WebElement adultMinusButton;

    @FindBy(id = "adultInput")
    private WebElement numberOfAdults;

    @FindBy(id = "childMinusBtn")
    private WebElement childMinusButton;

    @FindBy(id = "childPlusBtn" )
    private WebElement childPlusButton;

    @FindBy(id = "childInput")
    private WebElement numberofChildren;

    @FindBy(xpath = "//*[@id=\"HOTELS\"]/form/div[5]/button")
    private WebElement searchButton;

    private final String searchResultsPageTitle = "Search Results";

    private void setDates(String checkInDate, String  checkOutDate){
        chooseDate(checkInDatePopup,checkInCalendarcCurentMonth,checkIncalendarNextMonthButton,checkInCalendarDates,checkInDate);
        chooseDate(checkoutCalendarPopup,checkOutCalendarCurrentMonth,checkOutCalendarNextMonthButton,checkOutCalendarDates,checkOutDate);

    }

    private void enterPeopleNumbers(int adults, int children){
        numberOfTravelersField.click();
        //getText()method applied to input elements returns "". getAttribute("value") returns only default value hence there is no way to obtain dynamic numbers
        int nOfAdults = Integer.parseInt(numberOfAdults.getAttribute("value")) ;
        while (!(adults==nOfAdults)){
            if(nOfAdults>adults){
                adultMinusButton.click();
                nOfAdults--;
            }else{
                adultPlusButton.click();
                nOfAdults++;
            }
        }
        int nOfChildrens = Integer.parseInt(numberofChildren.getAttribute("value"));
        while (!(children==nOfChildrens)){
            if (nOfChildrens>children){
                childMinusButton.click();
                nOfChildrens--;
            }else {
                childPlusButton.click();
                nOfChildrens++;
            }
        }


    }

    private void enterHotelName(String hoterOrCity){
        hotelOrCityNameField.click();

        HOCInput.sendKeys(hoterOrCity);
            firstHOCDropdownElement.click();
        }

    public void Search(String hotelOrCity, String checkInDate, String checkOutDate, String numberOfAdults, String  numberOfChildren){

        enterHotelName(hotelOrCity);
        setDates(checkInDate, checkOutDate);
        enterPeopleNumbers(Integer.parseInt(numberOfAdults),Integer.parseInt(numberOfChildren));
        searchButton.click();
        Assert.assertTrue(searchResultsPageTitle.equals(driver.getTitle()));
    }
    public HotelSearchBar(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;

    }
}
