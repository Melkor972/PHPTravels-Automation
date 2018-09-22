package User_FE.Tests;

import User_FE.FE_Page_Factory.Pages.AccountPage;

import User_FE.FE_Page_Factory.Pages.LoginPage;
import Utils.ExcelUtils;
import com.sun.org.glassfish.gmbal.AMXMetadata;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AccountTests {
    private WebDriver driver;

    Properties prop = new Properties();

    @BeforeTest
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        File  file = new File("src\\test\\java\\Utils\\env.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        prop.load(fileInputStream);
        fileInputStream.close();
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
        driver.get(prop.getProperty("LOGINPAGE"));
    }

    @Test(dataProvider = "showInvoiceTestData")
    public void showInvoice(String bookingID){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = loginPage.successfulLogin(false);
       accountPage.showInvoice(bookingID);

    }

    @Test(dataProvider ="reviewTestData")
    public void addReview(String bookingID, String cleanMark ,String comfortMark, String locationMark, String facilityMark, String staffMark, String review){
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = loginPage.successfulLogin(false);
        accountPage.addComment(bookingID,cleanMark,comfortMark,locationMark,facilityMark,staffMark,review);
    }


    @DataProvider
    public Object[][] reviewTestData() throws Exception{
        Object[][] dataArray = ExcelUtils.getTableArray(prop.getProperty("ACCOUNTTESTSDATA"),"AddReview");
        return dataArray;
    }

    @DataProvider
    public Object[] showInvoiceTestData() throws Exception{
        Object[][] dataArray = ExcelUtils.getTableArray(prop.getProperty("ACCOUNTTESTSDATA"),"ShowInvoice");
        return dataArray;
    }
}
