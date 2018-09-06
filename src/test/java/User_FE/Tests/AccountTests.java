package User_FE.Tests;

import User_FE.FE_Page_Factory.Pages.AccountPage;
import User_FE.FE_Page_Factory.Pages.LoginPage;
import Utils.ExcelUtils;
import com.sun.org.glassfish.gmbal.AMXMetadata;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AccountTests {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void closeBrowser(){driver.close();}


    //have to reopen site for user logout purposes
    @BeforeMethod
    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://www.phptravels.net/login");
    }

    @Test(dataProvider = "showInvoiceTestData")
    public void login(String bookingID){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = loginPage.successfulLogin("user@phptravels.com","demouser","False");
        accountPage.showInvoice(bookingID);

    }
    @Test(dataProvider ="reviewTestData" )
    public void addReview(String bookingID, String cleanMark ,String comfortMark, String locationMark, String facilityMark, String staffMark, String review){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = loginPage.successfulLogin("user@phptravels.com","demouser","False");
        accountPage.addComment(bookingID,cleanMark,comfortMark,locationMark,facilityMark,staffMark,review);
    }
    @DataProvider
    public Object[][] reviewTestData() throws Exception{
        Object[][] dataArray = ExcelUtils.getTableArray("AccountTestsData.xlsx","AddReview");
        return dataArray;
    }

    @DataProvider
    public Object[] showInvoiceTestData() throws Exception{
        Object[][] dataArray = ExcelUtils.getTableArray("AccountTestsData.xlsx","ShowInvoice");
        return dataArray;
    }
}
