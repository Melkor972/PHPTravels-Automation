package User_FE.FE_Page_Factory.Pages;

import User_FE.FE_Page_Factory.Components.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    private final NavigationBar navigationBar;
    private final HotelSearchBar hotelSearchBar;
    private final TourSearchBar tourSearchBar;
    private final FlightsSearchBar flightsSearchBar;
    private final CarsSearchBar carsSearchBar;
    private final VisaSearchBar visaSearchBar;


    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
        navigationBar = new NavigationBar(driver);
        hotelSearchBar = new HotelSearchBar(driver);
        tourSearchBar = new TourSearchBar(driver);
        flightsSearchBar = new FlightsSearchBar(driver);
        carsSearchBar = new CarsSearchBar(driver);
        visaSearchBar = new VisaSearchBar(driver);
    }

  

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    public HotelSearchBar getHotelSearchBar() {
        return hotelSearchBar;
    }

    public TourSearchBar getTourSearchBar() {
        return tourSearchBar;
    }

    public FlightsSearchBar getFlightsSearchBar() {
        return flightsSearchBar;
    }

    public CarsSearchBar getCarsSearchBar(){
        return carsSearchBar;
    }

    public VisaSearchBar getVisaSearchBar() {
        return visaSearchBar;
    }
}
