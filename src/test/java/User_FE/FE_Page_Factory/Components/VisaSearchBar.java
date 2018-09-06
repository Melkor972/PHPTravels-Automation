package User_FE.FE_Page_Factory.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class VisaSearchBar {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"body-section\"]/section/div[2]/div/div[2]/ul/li[5]/a")
    private WebElement visaBarButton;

    @FindBy(id = "s2id_autogen4")
    private WebElement departureCountryField;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement countryInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li/div")
    private WebElement searchResult;

    @FindBy(id = "s2id_autogen6")
    private WebElement arrivalCountryField;

    @FindBy(xpath = "//*[@id=\"VISA\"]/form/div[3]/button")
    private WebElement searchButton;

    private final String searchResultsPageTitle = "Visa";


    private void navigateToVisaBar(){
        visaBarButton.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(departureCountryField));

    }

    private void enterDepartureCountry(String departureCountry){
        departureCountryField.click();
        countryInput.sendKeys(departureCountry);
       searchResult.click();

    }

    private void enterArrivalCountry(String arrivalCountry){
    arrivalCountryField.click();
    countryInput.sendKeys(arrivalCountry);
    searchResult.click();
    }

    private void clickSearchButton(){
       searchButton.click();
    }

    public void search(String departureCountry, String arrivalCountry){
        navigateToVisaBar();
        enterDepartureCountry(departureCountry);
        enterArrivalCountry(arrivalCountry);
        clickSearchButton();
        Assert.assertTrue(driver.getTitle().equals(searchResultsPageTitle));
    }

    public VisaSearchBar(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
}
