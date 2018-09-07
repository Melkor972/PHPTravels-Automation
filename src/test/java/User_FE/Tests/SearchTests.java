package User_FE.Tests;

import User_FE.FE_Page_Factory.Pages.HomePage;

import Utils.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SearchTests {
    WebDriver driver;


    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void openHomePage() {
        driver.get("https://www.phptravels.net/");
    }

    @org.testng.annotations.Test(dataProvider = "CarSearch")
    public void searchCar(String pickUpLocation, String dropOffLocation, String departureDate, String arrivalDate,String departureTime, String arrivalTime){
        HomePage homePage = new HomePage(driver);
        homePage.getCarsSearchBar().search(pickUpLocation,dropOffLocation,departureDate,arrivalDate,departureTime,arrivalTime);
    }

    @Test(dataProvider = "HotelSearch")
    public void hotelSearch(String hotelOrCity, String checkInDate, String checkOutDate, String numberOfAdults, String  numberOfChildren) {
        HomePage homePage = new HomePage(driver);
        homePage.getHotelSearchBar().Search(hotelOrCity, checkInDate,checkOutDate,numberOfAdults,numberOfChildren);

    }

  @Test(dataProvider = "TourSearch")
   public void searchTour(String listingorCity, String date, String numberOfGuests, String tourType){
       HomePage homePage = new HomePage(driver);
       homePage.getTourSearchBar().search(listingorCity,date,numberOfGuests,tourType);
   }

  @Test(dataProvider = "FlightSearch")
    public void flights(String tripType, String cabinClass, String departureLocation, String arrivalLocation, String departureDate,
                        String returnDate, String adults, String children, String infants) {
        HomePage homePage = new HomePage(driver);
        homePage.getFlightsSearchBar().search(tripType, cabinClass, departureLocation, arrivalLocation, departureDate, returnDate, adults, children, infants);

    }


 @Test(dataProvider = "VisaSearch")
 public void searVisa(String departureCountry, String arrivalCountry) {
     HomePage homePage = new HomePage(driver);
     homePage.getVisaSearchBar().search(departureCountry,arrivalCountry);

     System.out.println(driver.getTitle());

 }


    @DataProvider
    public Object[][] HotelSearch() throws Exception {
        Object[][] dataArray = ExcelUtils.getTableArray("SearchTestsData.xlsx", "HotelsSearch");
        return dataArray;
    }

    @DataProvider
    public Object[][] TourSearch() throws Exception {
        Object[][] dataArray = ExcelUtils.getTableArray("SearchTestsData.xlsx", "ToursSearch");
        return dataArray;
    }

    @DataProvider
    public Object[][] FlightSearch() throws Exception {
        Object[][] dataArray = ExcelUtils.getTableArray("SearchTestsData.xlsx", "FlightsSearch");
        return dataArray;

    }

    @DataProvider
    public Object[][] VisaSearch() throws Exception{
       Object[][] dataArray = ExcelUtils.getTableArray("SearchTestsData.xlsx","VisaSearch");
       return dataArray;
    }

    @DataProvider
    public Object[][] CarSearch() throws Exception{
       Object[][] dataArray = ExcelUtils.getTableArray("SearchTestsData.xlsx","CarsSearch");
       return dataArray;
    }


}